package net.yawk.client.saving;

import net.yawk.client.Client;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.JsonObject;

public class ClientDataTask implements DataTask{

	@Override
	public String getFileName() {
		return "client";
	}

	@Override
	public void read(JsonObject obj) {
		Client.getClient().getGui().opened = obj.get("opened-gui").getAsBoolean();
	}

	@Override
	public void write(JsonObject obj) {
		obj.addProperty("opened-gui", Client.getClient().getGui().opened);
	}
}
