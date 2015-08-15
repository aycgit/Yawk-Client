package net.yawk.client.api;

public class PluginData {
	
	private String name, description, filePath, fileName;
	private int version;
	private boolean wasEnabled, privatePlugin;
	
	public PluginData(String name, String description, String filePath, String fileName, int version, boolean wasEnabled, boolean privatePlugin) {
		this.name = name;
		this.description = description;
		this.filePath = filePath;
		this.fileName = fileName;
		this.version = version;
		this.wasEnabled = wasEnabled;
		this.privatePlugin = privatePlugin;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getFilePath() {
		return filePath;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public int getVersion() {
		return version;
	}
	
	public boolean getWasEnabled() {
		return wasEnabled;
	}
	
	public boolean isPrivatePlugin() {
		return privatePlugin;
	}
	
	public String getPluginIdentifier(){
		return name + "/" + version;
	}
}
