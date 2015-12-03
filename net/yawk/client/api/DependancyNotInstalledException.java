package net.yawk.client.api;

public class DependancyNotInstalledException extends Exception{
	
	private PluginData plugin;
	private PluginDependancy dependancy;
	
	public DependancyNotInstalledException(PluginData plugin, PluginDependancy dependancy) {
		super();
		this.plugin = plugin;
		this.dependancy = dependancy;
	}
	
	public PluginData getPlugin() {
		return plugin;
	}
	
	public PluginDependancy getDependancy() {
		return dependancy;
	}
}
