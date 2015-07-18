package net.yawk.client.saving;

import com.google.gson.JsonObject;

public interface DataTask {
		
	public String getFileName();
	
	public void read(JsonObject obj);
	
	public void write(JsonObject obj);
}
