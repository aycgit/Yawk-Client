package net.yawk.client.gui.themes.huzuni;

import java.awt.Color;
import java.awt.Font;

import net.yawk.client.Client;
import net.yawk.client.gui.themes.ThemeFontRenderer;

public class HuzuniFontRendererWrapper extends ThemeFontRenderer{
	
	public HuzuniFontRendererWrapper(){
		
	}
	
	public final static CFontRenderer font = new CFontRenderer(new Font("Tahoma", Font.TRUETYPE_FONT, 18), true, 8);
	
	public void drawString(String text, int x, int y, int colour, boolean shadow){
		font.drawString(text, x, y, colour);
	}
	
	@Override
	public int getStringHeight() {
		return font.getHeight();
	}
	
	@Override
	public int getStringWidth(String text) {
		return font.getStringWidth(text);
	}
}
