package me.riverhouse.candy.plugin;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class PluginManager {

	public ArrayList<CandyPlugin> plugins = new ArrayList<CandyPlugin>();

	public void loadPlugins(File dir){
		try {
			//PluginDownloader.downloadPlugin(dir, "plugin");
		} catch (Throwable e1) {
			e1.printStackTrace();
		}
		
		ArrayList<JarFile> jars = new ArrayList<JarFile>();

		for(File fileEntry : dir.listFiles()){
			String extension = "";

			int i = fileEntry.getName().lastIndexOf('.');

			if(i > 0){
				extension = fileEntry.getName().substring(i + 1);
			}

			if(extension.equalsIgnoreCase("jar")){
				try{
					//Chimera.getChimera().log("Loading plugin, " + fileEntry.getName());
					loadPlugin(new JarFile(fileEntry.getAbsoluteFile()), fileEntry);
				}catch(IOException e){
					e.printStackTrace();
				}
			}

		}
	}

	public void loadPlugin(JarFile jarFile, File file){
		String pluginMain;
		try{
			pluginMain = getMain(jarFile).replaceAll(" ", "");

			if(pluginMain.equalsIgnoreCase(""))
				return;

			File authorizedJarFile = file;
			ClassLoader authorizedLoader = URLClassLoader.newInstance(new URL[]{authorizedJarFile.toURL() });
			CandyPlugin plugin = (CandyPlugin) authorizedLoader.loadClass(pluginMain).newInstance();
			plugins.add(plugin);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void enablePlugins(){
		for(CandyPlugin plugin : plugins){
			plugin.onEnable();
		}
	}
	
	public void disPlugins(){
		for(CandyPlugin plugin : plugins){
			plugin.onDisable();
		}
	}

	private String getMain(JarFile jarFile) throws IOException{
		JarEntry entry = jarFile.getJarEntry("plugin.txt");

		if(entry == null)
			return "";

		InputStream input = jarFile.getInputStream(entry);

		InputStreamReader isr = new InputStreamReader(input);
		BufferedReader reader = new BufferedReader(isr);
		String line;
		while ((line = reader.readLine()) != null){
			String[] lineParts = line.split(":");

			if(lineParts[0].equalsIgnoreCase("main")){
				return lineParts[1];
			}

		}
		reader.close();
		return "";
	}
	
	public void reload(){
		this.disPlugins();
		//Chimera.getChimera().getModuleManager().reloadPluginMods();
		this.plugins.clear();
		
		//this.loadPlugins(Chimera.getChimera().settingsManager.chimeraPluginsFolder);
		this.enablePlugins();
	}

}
