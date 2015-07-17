package net.yawk.client.gui.components;

import net.yawk.client.Client;
import net.yawk.client.gui.Window;
import net.yawk.client.modmanager.Mod;

public class ModButton extends Button{

	private Mod mod;
	
	public ModButton(Window win, Mod mod) {
		super(win);
		this.mod = mod;
	}

	@Override
	public boolean isEnabled() {
		return mod.isEnabled();
	}
	
	@Override
	public void toggle() {
		Client.getClient().getModManager().toggle(mod);
	}
	
	@Override
	public String getText() {
		return Client.getClient().getModManager().getModName(mod);
	}
	
	@Override
	public boolean isCentered() {
		return false;
	}
	
	public Mod getMod() {
		return mod;
	}
}
