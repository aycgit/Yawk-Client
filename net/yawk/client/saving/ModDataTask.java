package net.yawk.client.saving;

import net.yawk.client.Client;
import net.yawk.client.modmanager.Mod;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class ModDataTask implements DataTask{

	@Override
	public String getFileName() {
		return "mods";
	}

	@Override
	public void read(Client client, JsonObject obj) {

		JsonArray arr = obj.get("mods").getAsJsonArray();

		for(JsonElement el : arr){

			JsonObject save = el.getAsJsonObject();

			JsonElement modElement = save.get("name");

			if(!modElement.isJsonNull()){
				
				Mod mod = Client.getClient().getModManager().getModByName(modElement.getAsString());
				
				if(mod != null){

					if(save.get("enabled").getAsBoolean()){
						Client.getClient().getModManager().toggle(mod);
					}

					mod.setKeybind(save.get("keybind").getAsInt());
				}
			}
		}
	}

	@Override
	public void write(Client client, JsonObject obj) {
		
		JsonArray arr = new JsonArray();
		
		for(Mod mod : Client.getClient().getModManager().mods){
			JsonObject save = new JsonObject();
			save.addProperty("name", mod.getName());
			save.addProperty("enabled", mod.isEnabled()? "true":"false");
			save.addProperty("keybind", mod.getKeybind());
			arr.add(save);
		}
		
		obj.add("mods", arr);
	}
	
}
