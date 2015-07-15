package net.yawk.client.saving;

import net.yawk.client.Client;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.ModData;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ModDataTask implements DataTask{

	@Override
	public String getFileName() {
		return "mods";
	}

	@Override
	public void read(JSONObject obj) {
		JSONArray arr = (JSONArray) obj.get("mods");
		
		for(Object o : arr){
			
			JSONObject save = (JSONObject) o;
			Mod mod = Client.getClient().getModManager().getModByName((String) save.get("name"));
			
			if(mod != null){
				ModData data = Client.getClient().getModManager().dataMap.get(mod);
				
				if(save.get("enabled").equals("true")){
					Client.getClient().getModManager().toggle(mod);
				}
				
				data.setKeybind(((Long) save.get("keybind")).intValue());
			}
		}
	}

	@Override
	public void write(JSONObject obj) {
		JSONArray arr = new JSONArray();
		
		for(Mod mod : Client.getClient().getModManager().mods){
			ModData data = Client.getClient().getModManager().dataMap.get(mod);
			JSONObject save = new JSONObject();
			save.put("name", mod.getName());
			save.put("enabled", data.isEnabled? "true":"false");
			save.put("keybind", data.getKeybind());
			arr.add(save);
		}
		
		obj.put("mods", arr);
	}
	
}
