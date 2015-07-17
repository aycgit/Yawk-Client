package net.yawk.client.modmanager;

import org.lwjgl.input.Keyboard;

public class Mod {
	
	private int keybind;
	
	private boolean enabled;
	
	public Mod() {
		super();
		this.keybind = -1;
	}
	
	public Mod(int keybind) {
		super();
		this.keybind = keybind;
	}
	
	public final String getKeyName(){
		return keybind == -1? "None":Keyboard.getKeyName(keybind);
	}
	
	public final int getKeybind() {
		return keybind;
	}
	
	public final void setKeybind(int keybind){
		this.keybind = keybind;
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public void onEnable(){
		
	}
	
	public void onDisable(){
		
	}
}
