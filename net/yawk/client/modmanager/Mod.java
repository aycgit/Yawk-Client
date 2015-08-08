package net.yawk.client.modmanager;

import net.yawk.client.modmanager.values.Value;

import org.lwjgl.input.Keyboard;

public class Mod implements Toggleable{
	
	protected int keybind;
	protected boolean enabled;
	protected Type type;
	protected String name;
	private Value[] options;
	
	public Mod() {
		super();
		this.keybind = -1;
	}
	
	public Mod(Value[] options) {
		super();
		this.options = options;
		this.keybind = -1;
	}
	
	public boolean hasOptions(){
		return options != null;
	}
	
	public Value[] getOptions() {
		return options;
	}

	public void setOptions(Value[] options) {
		this.options = options;
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
	
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public enum Type {
		
		COMBAT("Combat"), MOVEMENT("Movement"), WORLD("World"), BUILDING("Building"), PLUGIN("Plugin"), NONE("None");
		
	    private final String name;
	    
		private Type(String name) {
	        this.name = name;
	    }
		
	    public String getName() {
			return name;
		}
	}
}

