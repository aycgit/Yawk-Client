package net.yawk.client.saving;

import net.yawk.client.Client;
import net.yawk.client.gui.Window;
import net.yawk.client.modmanager.Mod;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class WindowDataTask implements DataTask{

	@Override
	public String getFileName() {
		return "windows";
	}

	@Override
	public void read(JSONObject obj) {
		JSONArray arr = (JSONArray) obj.get("windows");
		
		for(Object o : arr){
			
			JSONObject save = (JSONObject) o;
			Window w = Client.getClient().getGui().getWindowByName((String) save.get("title"));
			
			if(w != null){
				w.posX = ((Long) save.get("posX")).intValue();
				w.posY = ((Long) save.get("posY")).intValue();
				w.pinned = (Boolean) save.get("pinned");
				w.extended = (Boolean) save.get("extended");
			}
		}
	}

	@Override
	public void write(JSONObject obj) {
		JSONArray arr = new JSONArray();
		
		for(Window w : Client.getClient().getGui().windows){
			JSONObject save = new JSONObject();
			
			save.put("title", w.title);
			save.put("posX", w.posX);
			save.put("posY", w.posY);
			save.put("pinned", w.pinned);
			save.put("extended", w.extended);
			
			arr.add(save);
		}
		
		obj.put("windows", arr);
	}

}
