package net.yawk.client.gui;

import net.yawk.client.Client;
import net.yawk.client.modmanager.ModManager;

public class WindowClosable extends Window{

	public WindowClosable(String title, ModManager modManager) {
		super(title, modManager);
		this.extended = true;
	}
	
	public WindowClosable(String title, ModManager modManager, int width){
		super(title, modManager, width);
		this.extended = true;
	}
	
	@Override
	protected boolean hasPinnedButton() {
		return false;
	}

	@Override
	protected void onExtensionToggle() {
		Client.getClient().getGui().windows.remove(this);
	}
}
