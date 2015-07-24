package net.yawk.client.gui.themes.huzuni;

import net.yawk.client.Client;
import net.yawk.client.gui.ColourType;
import net.yawk.client.gui.GuiClickable;
import net.yawk.client.gui.IPanel;
import net.yawk.client.gui.Window;
import net.yawk.client.gui.components.Button;
import net.yawk.client.gui.themes.ButtonRenderer;
import net.yawk.client.utils.GuiUtils;

public class HuzuniButtonRenderer extends ButtonRenderer{
	
	public void renderButton(IPanel w, Button b, int x, int y, int cx, int cy){
		
		GuiUtils.drawRect(cx + 1, cy + 1, cx+w.getWidth()-1, cy+getThemeHeight()-1, b.isEnabled()? 0xFF0963BB:0xFF3B3B3D);
		
		GuiClickable.theme.getFontRenderer().drawString(b.getText(),
				cx + w.getWidth()/2 - GuiClickable.theme.getFontRenderer().getStringWidth(b.getText())/2, //Force centering
				cy + b.getHeight()/2 - GuiClickable.theme.getFontRenderer().getStringHeight()/2,
				0xFFFFFFFF,
				true);
	}
	
	public int getThemeHeight(){
		return 15;
	}
}
