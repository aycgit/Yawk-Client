package me.riverhouse.candy;

import java.util.ArrayList;

public class Client {

	private String name;
	private String commandsPath;
	private ArrayList<String> modulePaths = new ArrayList<String>();
	
	public Client(String name) {
		this.name = name;
	}

	public String getName(){
		return this.name;
	}
	
	public void onClientLaunch(){
		
	}

	public String getCommandsPath() {
		return commandsPath;
	}

	public ArrayList<String> getModulePaths() {
		return modulePaths;
	}

	public void setCommandsPath(String commandsPath) {
		this.commandsPath = commandsPath;
	}

	public void addModulePath(String modulePath) {
		this.modulePaths.add(modulePath);
	}
	
}
