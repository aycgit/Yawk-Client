package net.yawk.client.api;

import java.io.IOException;

import net.yawk.client.utils.ClientUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class PrivatePluginDownloadThread implements Runnable{

	private PluginData plugin;
	private JsonObject response;
	private boolean successful, running;
	private String name, password;
	
	public PrivatePluginDownloadThread(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}

	@Override
	public void run() {
		
		try {
			
			running = true;
			
			Document doc = Jsoup.connect("http://www.yawk.net/mods/getPrivate.php?name="+name+"&password="+password).userAgent(ClientUtils.USER_AGENT).get();
			
			response = new JsonParser().parse(doc.text()).getAsJsonObject();
			
			successful = response.has("status") && response.get("status").getAsBoolean();
			
			if(successful){
				plugin = new PluginData(response.get("name").getAsString(), response.get("description").getAsString(), response.get("file").getAsString(), response.get("filename").getAsString(), response.get("version").getAsInt(), false);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		running = false;
	}

	public PluginData getPlugin() {
		return plugin;
	}

	public JsonObject getResponse() {
		return response;
	}
	
	public boolean isSuccessful(){
		return successful;
	}
	
	public boolean isRunning() {
		return running;
	}

}
