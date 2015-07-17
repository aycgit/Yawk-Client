package net.yawk.client.gui.components;

import net.yawk.client.Client;
import net.yawk.client.gui.Window;
import net.yawk.client.modmanager.Mod;

import org.lwjgl.input.Keyboard;

public class KeybindButton extends SelectorButton implements ISelector{
	
	private Mod mod;
	
	public KeybindButton(Window win, Mod mod, SelectorSystem system) {
		super(win, win.getModManager().getModName(mod), system);
		this.mod = mod;
	}
	
	@Override
	public String getText() {
		return Client.getClient().getModManager().getModName(mod)+": "+(isSelected? "...":mod.getKeyName());
	}
	
	@Override
	public void keyPress(int key, char c) {
		if(isSelected){
			mod.setKeybind(key);
			isSelected = false;
		}
	}
}
