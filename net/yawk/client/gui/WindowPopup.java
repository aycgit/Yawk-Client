package net.yawk.client.gui;

import net.yawk.client.gui.components.TextDisplay;
import net.yawk.client.modmanager.ModManager;

public class WindowPopup extends WindowClosable{
	
	public WindowPopup(String title, String message, ModManager modManager, int width, int screenWidth, int screenHeight) {
		super(title, modManager, width);
		this.addComponent(new TextDisplay(message));
		this.posX = screenWidth/2 - width/2;
		this.posY = screenHeight/2;
	}
	
}
