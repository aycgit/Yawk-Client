package net.yawk.client.saving;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;

import net.yawk.client.Client;
import net.yawk.client.api.DependancyNotFoundException;
import net.yawk.client.api.DependancyNotInstalledException;
import net.yawk.client.api.PluginData;
import net.yawk.client.utils.FileUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class PluginDataTask implements DataTask{

	@Override
	public String getFileName() {
		return "plugins";
	}

	@Override
	public void read(Client client, JsonObject obj) {
		
		//The list of the data saved on the previous shutdown. This tells us if we need an update or which plugins were previously enabled.
		ArrayList<PluginData> lastInstalledData = new ArrayList<PluginData>();
		
		JsonArray arr = obj.get("plugins").getAsJsonArray();
		
		for(JsonElement el : arr){
			JsonObject json = el.getAsJsonObject();
			lastInstalledData.add(new PluginData(json.get("name").getAsString(), json.get("description").getAsString(), json.get("file").getAsString(), json.get("filename").getAsString(), json.get("version").getAsInt(), json.get("enabled").getAsBoolean(), json.get("privatePlugin").getAsBoolean()));
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
					} catch (DependancyNotInstalledException e) {
						e.printStackTrace();
					} catch (DependancyNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	@Override
	public void write(Client client, JsonObject obj) {
		
		JsonArray arr = new JsonArray();
		
		for(PluginData data : Client.getClient().getPluginManager().pluginData){
			
			JsonObject json = new JsonObject();
			
			json.addProperty("name", data.getName());
			json.addProperty("description", data.getDescription());
			json.addProperty("file", data.getFilePath());
			json.addProperty("filename", data.getFileName());
			json.addProperty("version", data.getVersion());
			json.addProperty("enabled", Client.getClient().getPluginManager().pluginWindows.containsKey(data));
			json.addProperty("privatePlugin", data.isPrivatePlugin());
			
			arr.add(json);
		}
		
		obj.add("plugins", arr);
	}

}
