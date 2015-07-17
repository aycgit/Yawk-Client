package net.yawk.client.saving;

import net.yawk.client.Client;
import net.yawk.client.modmanager.Mod;

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
				
				if(save.get("enabled").equals("true")){
					Client.getClient().getModManager().toggle(mod);
				}
				
				mod.setKeybind(((Long) save.get("keybind")).intValue());
			}
		}
	}

	@Override
	public void write(JSONObject obj) {
		JSONArray arr = new JSONArray();
		
		for(Mod mod : Client.getClient().getModManager().mods){
			JSONObject save = new JSONObject();
			save.put("name", Client.getClient().getModManager().getModName(mod));
			save.put("enabled", mod.isEnabled()? "true":"false");
			save.put("keybind", mod.getKeybind());
			arr.add(save);
		}
		
		obj.put("mods", arr);
	}
	
}
