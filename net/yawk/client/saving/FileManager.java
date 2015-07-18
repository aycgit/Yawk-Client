package net.yawk.client.saving;

import java.io.File;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.yawk.client.Client;
import net.yawk.client.utils.FileUtils;

public class FileManager {
	
	public DataTask[] tasks;
	
	public FileManager(){
		tasks = new DataTask[]{
				new ModDataTask(),
				new WindowDataTask(),
				new PluginDataTask(),
				new ClientDataTask(),
		};
	}
	
	public void save(){
		for(DataTask task : tasks){
			
			File file = getFile(task);
			
			if(file.exists()){
				JsonObject obj = new JsonObject();
				task.write(obj);
				FileUtils.writeFile(file, obj.toString());
			}else{
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void load(){
		for(DataTask task : tasks){
			
			File file = getFile(task);
			
			if(file.exists()){
				task.read((JsonObject) new JsonParser().parse(FileUtils.readFileFull(file)));
			}else{
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private File getFile(DataTask task){
		return new File(Client.getFullDir(), task.getFileName() + ".json");
	}
}
