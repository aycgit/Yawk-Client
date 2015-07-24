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

public class Window implements IPanel{
	
	public String title;
	public int posX, posY, mouseXOffset, mouseYOffset, colour = 0x99000000, borderColour = 0x20FFFFFF;
	public boolean dragging, extended, pinned;
	public ArrayList<Component> components = new ArrayList<Component>();
	private ModManager modManager;
	
	public static int TITLE_COMPONENT_SPACE = 2;
	
	public int width;
	public int height;
	
	public Window(String title, ModManager modManager, int width, int height){
		this.title = title;
		this.width = width;
		this.height = height;
		this.modManager = modManager;
	}
		
	public void onGuiClosed(){
		this.dragging = false;
	}
	
	public void keyPress(char c, int key) {
		for(Component comp : components){
			comp.keyPress(key, c);
		}
	}
	
	public boolean hasExtensionButton(){
		return true;
	}
	
	public boolean hasPinnedButton(){
		return true;
	}
	
	public void onExtensionToggle(){
		extended = !extended;
		doClickSound();
	}
	
	public void onPinnedToggle(){
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
