package net.yawk.client.gui.themes;

import net.yawk.client.Client;
import net.yawk.client.gui.ColourType;
import net.yawk.client.gui.GuiClickable;
import net.yawk.client.gui.IPanel;
import net.yawk.client.gui.Window;
import net.yawk.client.gui.components.Button;

public class ButtonRenderer {
	
	public void renderButton(IPanel w, Button b, int x, int y, int cx, int cy){
		
		//GuiUtils.drawGradientBorderedRect(cx, cy, cx+win.getWidth(), cy+getHeight(), 1, 0xFF000000, 0xFF0095FF, 0xFF0C53E8);
		
		if(b.isEnabled()){
			GuiClickable.theme.getFontRenderer().drawString(b.getText(),
					cx + (b.isCentered() ? (w.getWidth()/2 - Client.getClient().getFontRenderer().getStringWidth(b.getText())/2):3),
					cy + b.getHeight()/2 - Client.getClient().getFontRenderer().FONT_HEIGHT/2,
					b.mouseOverButton(x, y, cx, cy)? ColourType.HIGHLIGHT.getModifiedColour():ColourType.HIGHLIGHT.getColour(),
							true);
		}else{
			GuiClickable.theme.getFontRenderer().drawString(b.getText(),
					cx + (b.isCentered() ? (w.getWidth()/2 - Client.getClient().getFontRenderer().getStringWidth(b.getText())/2):3),
					cy + b.getHeight()/2 - Client.getClient().getFontRenderer().FONT_HEIGHT/2,
					b.mouseOverButton(x, y, cx, cy)? ColourType.TEXT.getModifiedColour():ColourType.TEXT.getColour(),
							true);
		}
	}
	
	public int getThemeHeight(){
		return 12;
	}
}
