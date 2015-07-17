package net.yawk.client.modmanager;

import net.yawk.client.api.PluginData;

public class PluginMod extends Mod{

	private PluginData pluginData;
	
	public PluginMod(int keybind, PluginData pluginData) {
		super(keybind);
		this.pluginData = pluginData;
	}
	
	public PluginData getPluginData() {
		return pluginData;
	}
	
	public void setPluginData(PluginData pluginData) {
		this.pluginData = pluginData;
	}
}
