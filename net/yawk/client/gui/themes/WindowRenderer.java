package net.yawk.client.gui.themes;

import net.yawk.client.Client;
import net.yawk.client.gui.ColourType;
import net.yawk.client.gui.GuiClickable;
import net.yawk.client.gui.Window;
import net.yawk.client.gui.components.Component;
import net.yawk.client.utils.GuiUtils;

public class WindowRenderer {
	
	public void renderWindow(Window w, int x, int y){
		
		if(w.dragging){
			w.posX = x+w.mouseXOffset;
			w.posY = y+w.mouseYOffset;
		}
		
		drawHeader(w, w.posX, w.posY, w.posX+w.width, w.posY+w.height);
		
		GuiClickable.theme.getFontRenderer().drawString(w.title,
				w.posX + 3,
				w.posY + w.height/2 - Client.getClient().getFontRenderer().FONT_HEIGHT/2,
				ColourType.TITLE_TEXT.getColour(),
				true);
		
		int h = 0;
		
		for(Component c : w.components){
			h += c.getHeight();
		}
		
		if(w.extended){
			drawBodyRect(w, w.posX, w.posY+w.height+w.TITLE_COMPONENT_SPACE, w.posX+w.width, w.posY+w.height+w.TITLE_COMPONENT_SPACE+h);
		}
		
		//Toggle extension
		if(w.hasExtensionButton()){
			drawButton(w, w.posX+w.width-10, w.posY+2, w.posX+w.width-2, w.posY+w.height-2, w.extended);
		}
		
		//Toggle pinned
		if(w.hasPinnedButton()){
			drawButton(w, w.posX+w.width-22, w.posY+2, w.posX+w.width-14, w.posY+w.height-2, w.pinned);
		}
		
		if(w.extended){
			
			h = 0;
			
			for(Component c : w.components){
				c.draw(x, y, w.posX, w.posY+w.height+w.TITLE_COMPONENT_SPACE+h);
				h += c.getHeight();
			}
		}		
	}
	
	public void mouseClicked(Window w, int x, int y){
		
		if(w.hasExtensionButton() && w.mouseOverToggleExtension(x, y)){
			w.onExtensionToggle();
		}else if(w.hasPinnedButton() && w.mouseOverTogglePinned(x, y)){
			w.onPinnedToggle();
		}else if(w.mouseOverTitle(x, y)){
			w.dragging = true;
			
			w.mouseXOffset = w.posX - x;
			w.mouseYOffset = w.posY - y;
			
			Client.getClient().gui.setDragging(w);
		}
		
		if(w.extended){
			
			int h = 0;
			
			for(Component c : w.components){
				c.mouseClicked(x, y, w.posX, w.posY+w.height+h);
				h += c.getHeight();
			}
		}
	}
	
	protected void drawButton(Window w, int x, int y, int x1, int y1, boolean enabled){
		GuiUtils.drawBorderedRect(x, y, x1, y1, 1, 0x5FFFFFFF, enabled? 0x99707070:0x99000000);
	}
	
	protected void drawHeader(Window w, int x, int y, int x1, int y1){
		GuiUtils.drawTopNodusRect(w.posX, w.posY, w.posX+w.width, w.posY+w.height);
	}
	
	protected void drawBodyRect(Window w, int x, int y, int x1, int y1){
		GuiUtils.drawBottomNodusRect(x, y, x1, y1);
	}
	
	public boolean mouseOverToggleExtension(Window w, int x, int y) {
		return x >= w.posX+w.width-10 && x <= w.posX+w.width-2 && y >= w.posY+2 && y <= w.posY+w.height-2;
	}
	
	public boolean mouseOverTogglePinned(Window w, int x, int y) {
		return x >= w.posX+w.width-22 && x <= w.posX+w.width-14 && y >= w.posY+2 && y <= w.posY+w.height-2;
	}
}
