package net.yawk.client.gui;

import java.awt.Color;
import java.awt.Font;

import net.yawk.client.Client;

//TODO: Do something with this
public class HuzuniFontRendererWrapper{
	
	public HuzuniFontRendererWrapper(){
		
	}
	
	public final static CFontRenderer font = new CFontRenderer(new Font("Tahoma", Font.TRUETYPE_FONT, 18), true, 8);
	
	public void drawString(String text, int x, int y, int colour, boolean shadow){
		font.drawString(text, x, y, colour);
	}
	
	public int getStringHeight() {
		return font.getHeight();
	}
	
	public int getStringWidth(String text) {
		return font.getStringWidth(text);
	}
}
