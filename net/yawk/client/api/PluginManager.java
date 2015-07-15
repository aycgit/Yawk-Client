package net.yawk.client.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import net.yawk.client.Client;
import net.yawk.client.gui.Window;
import net.yawk.client.gui.components.Component;
import net.yawk.client.gui.components.ModButton;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.ModData;
import net.yawk.client.utils.ClientUtils;
import net.yawk.client.utils.FileUtils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.darkmagician6.eventapi.EventManager;
/**
 * Handles all plugin data and plugin loading
 * @author RandomAmazingGuy
 */
public class PluginManager {
	
	private File plugins = new File(Client.getFullDir(), "plugins");
	private File config = new File(plugins, "settings.json");
	
	public ArrayList<PluginData> pluginData = new ArrayList<PluginData>();
	private ConcurrentLinkedQueue<PluginData> downloadQueue = new ConcurrentLinkedQueue<PluginData>();
	private HashMap<PluginData,Window> pluginWindows = new HashMap<PluginData,Window>();
	
	private Thread downloadThread;
	
	/**
	 * Loads the plugins in the /plugins/ folder at startup
	 * @throws ClassNotFoundException
	 * @throws MalformedURLException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public void load() throws ClassNotFoundException, MalformedURLException, InstantiationException, IllegalAccessException{
		
		//Create the plugins directory
		if(!plugins.exists()){
			plugins.mkdir();
		}
		
		//The list of the data saved on the previous shutdown. This tells us if we need an update or which plugins were previously enabled.
		ArrayList<PluginData> lastInstalledData = new ArrayList<PluginData>();
		
		if(!config.exists()){
			
			try {
				config.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else{
			//TODO: Move this to the file manager
			
			JSONArray arr1 = (JSONArray) JSONValue.parse(FileUtils.readFileFull(config));
			
			System.out.println("FILES: "+arr1.toJSONString());
			
			for(Object obj : arr1){
				JSONObject json = (JSONObject) obj;
				lastInstalledData.add(new PluginData(json.get("name").toString(), json.get("file").toString(), json.get("filename").toString(), Integer.parseInt(json.get("version").toString()), Boolean.parseBoolean(json.get("enabled").toString())));
			}
		}
		
		//Get plugins from the website
		try {
			
			Document doc = Jsoup.connect("http://www.yawk.net/mods/list.php").userAgent(ClientUtils.USER_AGENT).get();
			
			JSONArray arr = (JSONArray) JSONValue.parse(doc.text());
			
			for(Object obj : arr){
				
				JSONObject json = (JSONObject) obj;
				pluginData.add(new PluginData(json.get("name").toString(), json.get("file").toString(), json.get("filename").toString(), Integer.parseInt(json.get("version").toString()), false));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Downloads any plugins we don't have downloaded yet
		for(PluginData available : pluginData){
			
			File pluginFile = new File(plugins, available.getFileName());
			
			if(!pluginFile.exists()){
				this.downloadPlugin(available);
			}
		}
		
		//Check if a new update is available
		for(PluginData last : lastInstalledData){
			
			PluginData newData = getPluginDataByName(last.getName());
			
			if(newData != null){
				if(last.getVersion() < newData.getVersion()){
					//GET NEW VERSION DOWNLOADED
					System.out.println("DOWNLOADING NEW VERSION: "+newData.getName()+" ("+newData.getVersion()+")");
					new File(plugins, last.getName()).delete();
					downloadPlugin(newData);
				}
			}
		}
		
		//Load the previously enabled plugins
		for(PluginData last : lastInstalledData){
			if(last.getWasEnabled()){
				
				PluginData newData = getPluginDataByName(last.getName());
				
				if(newData != null){
					addPlugin(newData);
				}
			}
		}
	}
	
	/**
	 * Finds the Plugin object with a particular name 
	 * @param name
	 * @return
	 */
	private PluginData getPluginDataByName(String name){
		
		for(PluginData data : pluginData){
			if(data.getName().equals(name)){
				return data;
			}
		}
		
		return null;
	}
	
	/**
	 * Finds the Plugin object which is for a certiain file
	 * @param jar
	 * @return
	 */
	private PluginData getPluginDataFromFile(File jar){
		
		String name = ClientUtils.stripExtension(jar.getName());
		
		//System.out.println("CHECK FOR: "+name);
		
		for(PluginData data : pluginData){
			System.out.println("NAME: "+data.getName());
			if(data.getName().equals(name)){
				return data;
			}
		}
		
		return null;
	}
	
	/**
	 * Checks if a plugin is enabled
	 * @param plugin
	 * @return
	 */
	public boolean pluginEnabled(PluginData plugin){
		return pluginWindows.containsKey(plugin);
	}
	
	/**
	 * Initialises a plugin from it's file and then adds the plugin to the mod system and window system
	 * @param plugin
	 * @throws MalformedURLException
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public void addPlugin(PluginData plugin) throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
		
		File jar = new File(plugins, plugin.getFileName());
		
		System.out.println("ADDING PLUGIN: "+jar.getPath());
		
		if(jar.isDirectory()){
			return;
		}
		
		if(isValid(jar)){
			
			System.out.println("LOADING VALID JAR: "+jar.getName());
			
			URI uri = jar.toURI();
			URL url = uri.toURL();
			URL[] urls = new URL[]{url};
			
			ClassLoader cl = new URLClassLoader(urls);
			//TODO: Change how we find the path of the plugin class. Add a JSON file in the plugin jar to say where it is located.
			Class register = Class.forName("net.yawk.mods.Plugin", true, cl);
			
			PluginRegister reg = (PluginRegister) register.newInstance();
			cl.clearAssertionStatus();
			
			ArrayList<Mod> mods = new ArrayList<Mod>();
			
			//This allows the plugin to add hacks
			reg.init(Client.getClient(), mods);
			
			//This adds the window that will contain the plugin gui
			Window w;
			pluginWindows.put(plugin, w = new Window(plugin.getName(), reg.getWidth()));
			Client.getClient().getGui().windows.add(w);
			
			//This allows the plugin to add it's own components
			reg.addElements(Client.getClient(), w);
			
			//This puts the plugin hacks into the module system and plugin window gui
			for(Mod m : mods){
				Client.getClient().getModManager().addMod(m, plugin);
				w.components.add(new ModButton(w, m));
			}
			
		}else{
			System.out.println("INVALID JAR: "+jar.getName());
		}
	}
	
	/**
	 * Downloads a plugin from the website
	 * @param plugin
	 */
	public void downloadPlugin(PluginData plugin){
		
		System.out.println("DOWNLOADING: "+plugin.getFileName());
		
		if(downloadThread != null && downloadThread.isAlive()){
			
			downloadQueue.add(plugin);
			
		}else{
			
			downloadQueue.add(plugin);
			
			downloadThread = new Thread(){
				
				public void run(){
					
					while(!downloadQueue.isEmpty()){
						
						PluginData data = downloadQueue.poll();
						
						File pluginFile = new File(plugins, data.getFileName());
						
				        try {
				        	
				            URL website = new URL(data.getFilePath());
				            URL url = new URL(data.getFilePath());
				            URLConnection hc = url.openConnection();
				            hc.setRequestProperty("User-Agent", ClientUtils.USER_AGENT);
				            ReadableByteChannel rbc;
				            rbc = Channels.newChannel(hc.getInputStream());
				            FileOutputStream fos = new FileOutputStream(pluginFile);
				            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
				            fos.close();
				            rbc.close();
				            
				    		//System.out.println("DOWNLOADED: "+pluginFile.getName());
				    		
				        } catch (IOException e) {
				            e.printStackTrace();
				        }
					}
				}
				
			};
			
			downloadThread.start();
		}
	}
	
	/**
	 * Removes a plugin from the mod system and the window system
	 * @param plugin
	 */
	public void removePlugin(PluginData plugin) {
		
		System.out.println("REMOVING: "+plugin.getFileName());
		
		ModButton modButton = null;
		Mod pluginMod = null;
		
		for(Mod mod : Client.getClient().getModManager().dataMap.keySet()){
			
			ModData data = Client.getClient().getModManager().dataMap.get(mod);
			
			if(data.getPlugin() == plugin){
				pluginMod = mod;
			}
		}
		
		Client.getClient().getModManager().mods.remove(pluginMod);
		Client.getClient().getModManager().dataMap.remove(pluginMod);
		EventManager.unregister(pluginMod);
		
		Client.getClient().getGui().windows.remove(this.pluginWindows.get(plugin));
		
		pluginWindows.remove(plugin);
	}
	
	/**
	 * The method run by the ShutdownHook when the client quits, to save the data about our current plugins
	 */
	//TODO: Move this to the file manager
	public void onMinecraftClose(){
		
		if(!config.exists()){
			try {
				config.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		
		JSONArray arr = new JSONArray();
		
		for(PluginData data : pluginData){
			
			JSONObject json = new JSONObject();
			
			json.put("name", data.getName());
			json.put("file", data.getFilePath());
			json.put("filename", data.getFileName());
			json.put("version", data.getVersion());
			json.put("enabled", pluginWindows.containsKey(data));
			
			arr.add(json);
		}
		
		FileUtils.writeFile(config, arr.toJSONString());
	}
	
	private boolean developerMode = true;
	
	/**
	 * This method is designed to stop people adding their own plugins which aren't approved by the website yawk.net
	 * In the future, plugins which aren't approved may be allowed, but this checking will stay enabled to protect client users from viruses
	 * 
	 * The hash check uses the SHA-1 algorithm to get the checksum of the plugin file we're trying to load
	 * It checks whether the file hash matches the hash of the file on the website (which is approved)
	 * 
	 * This should prevent any usage of modified plugin files
	 * Because any modified files will have a different checksum
	 * 
	 * The URL for hash checking is
	 * http://www.yawk.net/mods/valid.php
	 * 
	 * The parameters for hash checking on the website are:
	 * name = the name of the plugin file
	 * hash = the checksum of the plugin file
	 * 
	 * @param jar
	 * @return
	 */
	private boolean isValid(File jar) {
				
		if(developerMode){
			return true;
		}
		
		try {
			
			String hash = getHash(jar);
			System.out.println("HASH: "+hash);
			System.out.println("NAME: "+ClientUtils.stripExtension(jar.getName()));
			Document doc = Jsoup.connect("http://www.yawk.net/mods/valid.php?&name="+ClientUtils.stripExtension(jar.getName())+"&hash="+hash).userAgent(ClientUtils.USER_AGENT).get();
			System.out.println("RESPONSE: "+doc.text());
			
			return doc.text().equalsIgnoreCase("true");
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * Gets an SHA-1 hash from the file given. This is used to check the hash against the website's saved hash
	 * @param jar
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 */
	private String getHash(File jar) throws NoSuchAlgorithmException, IOException{
		
		MessageDigest md = MessageDigest.getInstance("SHA1");
		FileInputStream fis = new FileInputStream(jar);
		byte[] dataBytes = new byte[1024];
		
		int nread = 0; 
		
		while ((nread = fis.read(dataBytes)) != -1) {
			md.update(dataBytes, 0, nread);
		};
		
		fis.close();
		
		byte[] mdbytes = md.digest();
		
		//convert the byte to hex format
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < mdbytes.length; i++) {
			sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
		}
		
		return sb.toString().toUpperCase();
	}
}
