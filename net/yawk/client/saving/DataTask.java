package net.yawk.client.saving;

import org.json.simple.JSONObject;

public interface DataTask {
	
	public String getFileName();
	public void read(JSONObject obj);
	public void write(JSONObject obj);
}
