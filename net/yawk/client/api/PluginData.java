package net.yawk.client.api;

public class PluginData {
	
	private String name;
	private String filePath;
	private String fileName;
	private int version;
	private boolean wasEnabled;
	
	public PluginData(String name, String filePath, String fileName, int version, boolean wasEnabled) {
		super();
		this.name = name;
		this.filePath = filePath;
		this.fileName = fileName;
		this.version = version;
		this.wasEnabled = wasEnabled;
	}
	
	public String getName() {
		return name;
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
}
