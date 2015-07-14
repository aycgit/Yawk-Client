package net.yawk.client.gui.components;

import net.yawk.client.Client;
import net.yawk.client.gui.Window;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.ModData;

import org.lwjgl.input.Keyboard;

public class KeybindButton extends SelectorButton implements ISelector{
	
	private Mod mod;
	
	public KeybindButton(Window win, Mod mod, SelectorSystem system) {
		super(win, mod.getName(), system);
		this.mod = mod;
	}
	
	@Override
	public String getText() {
		return mod.getName()+": "+(isSelected? "...":Client.getClient().getModManager().dataMap.get(mod).getKeyName());
	}
		
	@Override
	public void keyPress(int key, char c) {
		if(isSelected){
			Client.getClient().getModManager().dataMap.get(mod).setKeybind(key);
			isSelected = false;
		}
	}
}
