package net.yawk.client.saving;

import net.yawk.client.Client;

import com.google.gson.JsonObject;

public class ClientDataTask implements DataTask{
	
	@Override
	public String getFileName() {
		return "client";
	}

	@Override
	public void read(Client client, JsonObject obj) {
		Client.getClient().getGui().opened = obj.get("opened-gui").getAsBoolean();
	}

	@Override
	public void write(Client client, JsonObject obj) {
		obj.addProperty("opened-gui", Client.getClient().getGui().opened);
	}
}
