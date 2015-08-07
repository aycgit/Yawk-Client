package net.yawk.client.saving;

import net.yawk.client.Client;
import net.yawk.client.gui.ColourType;
import net.yawk.client.modmanager.Mod;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class ColourDataTask implements DataTask{
		
	@Override
	public String getFileName() {
		return "colours";
	}

	@Override
	public void read(Client client, JsonObject obj) {
		
		JsonArray arr = obj.get("colours").getAsJsonArray();
		
		for(JsonElement el : arr){
			
			JsonObject save = el.getAsJsonObject();
			
			ColourType type = ColourType.getTypeByName(save.get("name").getAsString());
			
			if(type != null){
				type.setIndex(save.get("index").getAsInt());
			}
		}
	}

	@Override
	public void write(Client client, JsonObject obj) {
		
		JsonArray arr = new JsonArray();
		
		for(ColourType col : ColourType.values()){
			JsonObject save = new JsonObject();
			save.addProperty("name", col.getName());
			save.addProperty("index", col.getIndex());
			arr.add(save);
		}
		
		obj.add("colours", arr);
	}
}
