package net.yawk.client.saving;

import net.yawk.client.Client;

import com.google.gson.JsonObject;

public interface DataTask {
		
	public String getFileName();
	
	public void read(Client client, JsonObject obj);
	
	public void write(Client client, JsonObject obj);
}
