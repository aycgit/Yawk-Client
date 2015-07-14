package net.yawk.client.modmanager;

import net.yawk.client.api.PluginData;

import org.lwjgl.input.Keyboard;

public class ModData {
	
	private int keybind;
	
	public boolean isEnabled;
	
	private PluginData plugin;
	
	public ModData(int keybind) {
		super();
		this.keybind = keybind;
	}
	
	public ModData(int keybind, PluginData plugin) {
		super();
		this.keybind = keybind;
		this.plugin = plugin;
	}
	
	public String getKeyName(){
		return keybind == -1? "None":Keyboard.getKeyName(keybind);
	}
	
	public int getKeybind() {
		return keybind;
	}
	
	public void setKeybind(int keybind){
		this.keybind = keybind;
	}
	
	public PluginData getPlugin() {
		return plugin;
	}
}
