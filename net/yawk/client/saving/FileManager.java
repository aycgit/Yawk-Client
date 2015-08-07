package net.yawk.client.saving;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.yawk.client.Client;
import net.yawk.client.utils.FileUtils;
import net.yawk.client.utils.ReflectionUtils;

public class FileManager {
	
	public List<DataTask> tasks;
	private DataTask settingsTask;
	private Client client;
	
	public FileManager(Client client){
		this.client = client;
		tasks = initializeClassesFromPackage("net.yawk.client.saving");
		settingsTask = getTaskByName("settings");
	}
	
	public List<DataTask> initializeClassesFromPackage(String packageName){
		
		List<DataTask> objects = new ArrayList<DataTask>();
		
		for (Class<?> clazz : ReflectionUtils.getClasses(packageName)) {
			try {
				if(Arrays.asList(clazz.getInterfaces()).contains(DataTask.class)){ //isAssignableFrom didn't work
					Object obj = clazz.newInstance();
					objects.add((DataTask) obj);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return objects;
	}
    
	public void save(){
		for(DataTask task : tasks){
			
			File file = getFile(task);
			
			if(file.exists()){
				JsonObject obj = new JsonObject();
				task.write(client, obj);
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
	
	/**
	 * Loads simple single and optional values
	 */
	public void loadClientSettings(){
		handleTaskLoading(settingsTask);
	}
	
	/**
	 * Loads complex values
	 */
	public void loadSecondarySettings(){
		for(DataTask task : tasks){
			if(task != settingsTask){
				handleTaskLoading(task);
			}
		}
	}
	
	private void handleTaskLoading(DataTask task){
		
		File file = getFile(task);
		
		if(file.exists()){
			System.out.println("LOADING TASK: "+task.getFileName());
			task.read(client, (JsonObject) new JsonParser().parse(FileUtils.readFileFull(file)));
		}else{
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private File getFile(DataTask task){
		return new File(Client.getFullDir(), task.getFileName() + ".json");
	}
	
	private DataTask getTaskByName(String name){
		
		for(DataTask task : tasks){
			if(task.getFileName().equalsIgnoreCase(name)){
				return task;
			}
		}
		
		return null;
	}
}
