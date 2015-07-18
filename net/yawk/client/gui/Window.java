package net.yawk.client.gui;

import java.util.ArrayList;

import net.yawk.client.Client;
import net.yawk.client.gui.components.Button;
import net.yawk.client.gui.components.Component;
import net.yawk.client.gui.components.ScrollPane;
import net.yawk.client.modmanager.ModManager;
import net.yawk.client.utils.Colours;
import net.yawk.client.utils.GuiUtils;

import org.lwjgl.opengl.GL11;

public class Window {
	
	public String title;
	public int posX, posY, mouseXOffset, mouseYOffset, colour = 0x99000000, borderColour = 0x20FFFFFF;
	public boolean dragging, extended, pinned;
	public ArrayList<Component> components = new ArrayList<Component>();
	private ModManager modManager;
	
	public static int TITLE_COMPONENT_SPACE = 2;
	
	private int width, height;
	
	public Window(String title, ModManager modManager){
		this(title, modManager, 85, 12);
		this.modManager = modManager;
	}
	
	public Window(String title, ModManager modManager, int width){
		this.title = title;
		this.width = width;
		this.height = 12;
		this.modManager = modManager;
	}
	
	public Window(String title, ModManager modManager, int width, int height){
		this.title = title;
		this.width = width;
		this.height = height;
		this.modManager = modManager;
	}
	
	public void draw(int x, int y){
		
		if(dragging){
			posX = x+mouseXOffset;
			posY = y+mouseYOffset;
		}
		
		GuiUtils.drawTopNodusRect(posX, posY, posX+width, posY+height);
		
		Client.getClient().getFontRenderer().drawStringWithShadow(title,
				posX + 3,
				posY + height/2 - Client.getClient().getFontRenderer().FONT_HEIGHT/2,
				Colours.BRIGHT_TEXT,
				true);
		
		int h = 0;
		
		for(Component c : components){
			h += c.getHeight();
		}
		
		if(extended){
			GuiUtils.drawBottomNodusRect(posX, posY+height+TITLE_COMPONENT_SPACE, posX+width, posY+height+TITLE_COMPONENT_SPACE+h);
		}
		
		//Toggle extension
		if(hasExtensionButton()){
			if(extended){
				GuiUtils.drawBorderedRect(posX+width-10, posY+2, posX+width-2, posY+height-2, 1, 0x5FFFFFFF, 0x99707070);
			}else{
				GuiUtils.drawBorderedRect(posX+width-10, posY+2, posX+width-2, posY+height-2, 1, 0x5FFFFFFF, 0x99000000);
			}
		}
		
		//Toggle pinned
		if(hasPinnedButton()){
			if(pinned){
				GuiUtils.drawBorderedRect(posX+width-22, posY+2, posX+width-14, posY+height-2, 1, 0x5FFFFFFF, 0x99707070);
			}else{
				GuiUtils.drawBorderedRect(posX+width-22, posY+2, posX+width-14, posY+height-2, 1, 0x5FFFFFFF, 0x99000000);
			}
		}
		
		if(extended){
			h = 0;
			
			for(Component c : components){
				c.draw(x, y, posX, posY+height+TITLE_COMPONENT_SPACE+h);
				h += c.getHeight();
			}
		}
	}
	
	public void onGuiClosed(){
		this.dragging = false;
	}
	
	public void keyPress(char c, int key) {
		for(Component comp : components){
			comp.keyPress(key, c);
		}
	}
	
	public void mouseClicked(int x, int y){
		
		if(hasExtensionButton() && this.mouseOverToggleExtension(x, y)){
			onExtensionToggle();
		}else if(hasPinnedButton() && this.mouseOverTogglePinned(x, y)){
			onPinnedToggle();
		}else if(this.mouseOverTitle(x, y)){
			dragging = true;
			
			mouseXOffset = posX - x;
			mouseYOffset = posY - y;
			
			Client.getClient().gui.setDragging(this);
		}
		
		if(extended){
			int h = 0;
			
			for(Component c : components){
				c.mouseClicked(x, y, posX, posY+height+h);
				h += c.getHeight();
			}
		}
	}
	
	protected boolean hasExtensionButton(){
		return true;
	}
	
	protected boolean hasPinnedButton(){
		return true;
	}
	
	protected void onExtensionToggle(){
		extended = !extended;
		doClickSound();
	}
	
	protected void onPinnedToggle(){
		pinned = !pinned;
		doClickSound();
	}
	
	protected void doClickSound(){
		Client.getClient().getMinecraft().thePlayer.playSound("random.click", 1, 1);
	}
	
	protected void mouseReleased(int mouseX, int mouseY, int state) {
		dragging = false;
		if(extended){
			for(Component c : components){
				c.mouseReleased(mouseX, mouseY, state);
			}
		}
	}
	
	public boolean mouseOverTitle(int x, int y){
		return x >= posX && x <= posX+width && y >= posY && y <= posY+height;
	}
	
	public boolean mouseOverToggleExtension(int x, int y){
		return x >= posX+width-10 && x <= posX+width-2 && y >= posY+2 && y <= posY+height-2;
	}
	
	public boolean mouseOverTogglePinned(int x, int y){
		return x >= posX+width-22 && x <= posX+width-14 && y >= posY+2 && y <= posY+height-2;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public ModManager getModManager() {
		return modManager;
	}

	public void setModManager(ModManager modManager) {
		this.modManager = modManager;
	}
}
