package net.yawk.client.gui.themes;

import net.yawk.client.Client;

public class ThemeFontRenderer {
	
	public void drawString(String text, int x, int y, int colour, boolean shadow){
		Client.getClient().getFontRenderer().drawStringWithShadow(text, x, y, colour, shadow);
	}
	
	public int getStringWidth(String text){
		return Client.getClient().getFontRenderer().getStringWidth(text);
	}
	
	public int getStringHeight(){
		return Client.getClient().getFontRenderer().FONT_HEIGHT;
	}
}
