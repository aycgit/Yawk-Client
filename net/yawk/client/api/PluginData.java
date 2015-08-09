package net.yawk.client.api;

public class PluginData {
	
	private String name;
	private String description;
	private String filePath;
	private String fileName;
	private int version;
	private boolean wasEnabled;
	
	public PluginData(String name, String description, String filePath, String fileName, int version, boolean wasEnabled) {
		this.name = name;
		this.description = description;
		this.filePath = filePath;
		this.fileName = fileName;
		this.version = version;
		this.wasEnabled = wasEnabled;
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
	
	public String getPluginIdentifier(){
		return name + "/" + version;
	}
}
