package net.yawk.client.gui;

import net.yawk.client.Client;
import net.yawk.client.modmanager.ModManager;

public class WindowClosable extends Window{

	public WindowClosable(String title, ModManager modManager) {
		super(title, modManager, 85, 12);
		this.extended = true;
	}
	
	public WindowClosable(String title, ModManager modManager, int width){
		super(title, modManager, width, 12);
		this.extended = true;
	}
	
	@Override
	public boolean hasPinnedButton() {
		return false;
	}

	@Override
	public void onExtensionToggle() {
		Client.getClient().getGui().windows.remove(this);
	}
}
