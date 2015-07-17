package net.yawk.client.saving;

import org.json.simple.JSONObject;

public interface DataTask {
	
	//TODO: Maybe pass a json array to the data tasks to cut out some unneeded stuff
	
	public String getFileName();
	
	public void read(JSONObject obj);
	
	public void write(JSONObject obj);
}
