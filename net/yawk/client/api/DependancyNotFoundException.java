package net.yawk.client.api;

public class DependancyNotFoundException extends Exception{
	
	private PluginDependancy dependancy;
	
	public DependancyNotFoundException(PluginDependancy dependancy) {
		super();
		this.dependancy = dependancy;
	}
	
	public PluginDependancy getDependancy() {
		return dependancy;
	}
}
