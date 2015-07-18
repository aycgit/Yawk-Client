package net.yawk.client.saving;

import net.yawk.client.Client;
import net.yawk.client.modmanager.Mod;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class ModDataTask implements DataTask{

	@Override
	public String getFileName() {
		return "mods";
	}

	@Override
	public void read(JsonObject obj) {
		
		JsonArray arr = (JsonArray) obj.get("mods");
		
		for(JsonElement el : arr){
			
			JsonObject save = (JsonObject) el;
			
			Mod mod = Client.getClient().getModManager().getModByName(save.get("name").getAsString());
			
			if(mod != null){
				
				if(save.get("enabled").getAsBoolean()){
					Client.getClient().getModManager().toggle(mod);
				}
				
				mod.setKeybind(save.get("keybind").getAsInt());
			}
		}
	}

	@Override
	public void write(JsonObject obj) {
		
		JsonArray arr = new JsonArray();
		
		for(Mod mod : Client.getClient().getModManager().mods){
			JsonObject save = new JsonObject();
			save.addProperty("name", Client.getClient().getModManager().getModName(mod));
			save.addProperty("enabled", mod.isEnabled()? "true":"false");
			save.addProperty("keybind", mod.getKeybind());
			arr.add(save);
		}
		
		obj.addProperty("mods", arr.toString());
	}
	
}
