package net.yawk.client.gui.themes.huzuni;

import net.yawk.client.Client;
import net.yawk.client.gui.ColourType;
import net.yawk.client.gui.GuiClickable;
import net.yawk.client.gui.Window;
import net.yawk.client.gui.components.Component;
import net.yawk.client.gui.themes.WindowRenderer;
import net.yawk.client.utils.GuiUtils;

public class HuzuniWindowRenderer extends WindowRenderer{
	
	public void renderWindow(Window w, int x, int y){
		
		if(w.dragging){
			w.posX = x+w.mouseXOffset;
			w.posY = y+w.mouseYOffset;
		}
		
		drawHeader(w, w.posX, w.posY, w.posX+w.width, w.posY+w.height);
		
		GuiClickable.theme.getFontRenderer().drawString(w.title,
				w.posX + 1,
				w.posY + 2,
				0xFFFFFFFF,
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
			drawButton(w, w.posX+w.width-11, w.posY+1, w.posX+w.width-1, w.posY+w.height-1, w.extended);
		}
		
		//Toggle pinned
		if(w.hasPinnedButton()){
			drawButton(w, w.posX+w.width-23, w.posY+1, w.posX+w.width-13, w.posY+w.height-1, w.pinned);
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
		
		if(w.hasExtensionButton() && mouseOverToggleExtension(w, x, y)){
			w.onExtensionToggle();
		}else if(w.hasPinnedButton() && mouseOverTogglePinned(w, x, y)){
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
		GuiUtils.drawRect(x, y, x1, y1, enabled? 0xFF0963BB:0xFF3f3f3f);
	}
	
	protected void drawHeader(Window w, int x, int y, int x1, int y1){
		GuiUtils.drawRect(w.posX, w.posY, w.posX+w.width, w.posY+w.height, 0xFF000000);
	}
	
	protected void drawBodyRect(Window w, int x, int y, int x1, int y1){
		GuiUtils.drawRect(x, y-Window.TITLE_COMPONENT_SPACE, x1, y1, 0xFF000000);
	}
	
	public boolean mouseOverToggleExtension(Window w, int x, int y) {
		return x >= w.posX+w.width-11 && x <= w.posX+w.width-1 && y >= w.posY+1 && y <= w.posY+w.height-1;
	}
	
	public boolean mouseOverTogglePinned(Window w, int x, int y) {
		return x >= w.posX+w.width-23 && x <= w.posX+w.width-13 && y >= w.posY+1 && y <= w.posY+w.height-1;
	}
}
