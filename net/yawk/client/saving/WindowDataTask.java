package net.yawk.client.saving;

import net.yawk.client.Client;
import net.yawk.client.gui.Window;
import net.yawk.client.modmanager.Mod;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class WindowDataTask implements DataTask{

	@Override
	public String getFileName() {
		return "windows";
	}

	@Override
	public void read(JsonObject obj) {
		JsonArray arr = (JsonArray) obj.get("windows");
		
		for(Object o : arr){
			
			JsonObject save = (JsonObject) o;
			Window w = Client.getClient().getGui().getWindowByName(save.get("title").getAsString());
			
			if(w != null){
				w.posX = save.get("posX").getAsInt();
				w.posY = save.get("posY").getAsInt();
				w.pinned = save.get("pinned").getAsBoolean();
				w.extended = save.get("extended").getAsBoolean();
			}
		}
	}

	@Override
	public void write(JsonObject obj) {
		JsonArray arr = new JsonArray();
		
		for(Window w : Client.getClient().getGui().windows){
			
			JsonObject save = new JsonObject();
			
			save.addProperty("title", w.title);
			save.addProperty("posX", w.posX);
			save.addProperty("posY", w.posY);
			save.addProperty("pinned", w.pinned);
			save.addProperty("extended", w.extended);
			
			arr.add(save);
		}
		
		obj.addProperty("windows", arr.toString());
	}

}
