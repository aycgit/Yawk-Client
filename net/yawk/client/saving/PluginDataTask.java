package net.yawk.client.saving;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;

import net.yawk.client.Client;
import net.yawk.client.api.PluginData;
import net.yawk.client.utils.FileUtils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class PluginDataTask implements DataTask{

	@Override
	public String getFileName() {
		return "plugins";
	}

	@Override
	public void read(JSONObject obj) {
		
		//The list of the data saved on the previous shutdown. This tells us if we need an update or which plugins were previously enabled.
		ArrayList<PluginData> lastInstalledData = new ArrayList<PluginData>();
		
		JSONArray arr = (JSONArray) obj.get("plugins");
		
		for(Object o : arr){
			JSONObject json = (JSONObject) o;
			lastInstalledData.add(new PluginData(json.get("name").toString(), json.get("file").toString(), json.get("filename").toString(), Integer.parseInt(json.get("version").toString()), Boolean.parseBoolean(json.get("enabled").toString())));
		}
		
		//Check if a new update is available
		for(PluginData last : lastInstalledData){
			
			PluginData newData = Client.getClient().getPluginManager().getPluginDataByName(last.getName());
			
			if(newData != null){
				if(last.getVersion() < newData.getVersion()){
					//GET NEW VERSION DOWNLOADED
					System.out.println("DOWNLOADING NEW PLUGIN VERSION: "+newData.getName()+" ("+newData.getVersion()+")");
					new File(new File(Client.getFullDir(), "plugins"), last.getName()).delete();
					Client.getClient().getPluginManager().downloadPlugin(newData);
				}
			}
		}
		
		//Load the previously enabled plugins
		for(PluginData last : lastInstalledData){
			if(last.getWasEnabled()){
				
				PluginData newData = Client.getClient().getPluginManager().getPluginDataByName(last.getName());
				
				if(newData != null){
					try {
						Client.getClient().getPluginManager().addPlugin(newData);
					} catch (MalformedURLException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	@Override
	public void write(JSONObject obj) {
		
		JSONArray arr = new JSONArray();
		
		for(PluginData data : Client.getClient().getPluginManager().pluginData){
			
			JSONObject json = new JSONObject();
			
			json.put("name", data.getName());
			json.put("file", data.getFilePath());
			json.put("filename", data.getFileName());
			json.put("version", data.getVersion());
			json.put("enabled", Client.getClient().getPluginManager().pluginWindows.containsKey(data));
			
			arr.add(json);
		}
		
		obj.put("plugins", arr);
	}

}
