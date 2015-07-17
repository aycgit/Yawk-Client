package net.yawk.client.saving;

import net.yawk.client.Client;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ClientDataTask implements DataTask{

	@Override
	public String getFileName() {
		return "client";
	}

	@Override
	public void read(JSONObject obj) {
		Client.getClient().getGui().opened = (Boolean) obj.get("opened-gui");
	}

	@Override
	public void write(JSONObject obj) {
		obj.put("opened-gui", Client.getClient().getGui().opened);
	}
}
