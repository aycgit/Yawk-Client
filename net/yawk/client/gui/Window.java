package net.yawk.client.gui;

import java.util.ArrayList;
import java.util.List;

import net.yawk.client.Client;
import net.yawk.client.gui.components.buttons.Button;
import net.yawk.client.gui.components.scrolling.ScrollPane;
import net.yawk.client.modmanager.ModManager;
import net.yawk.client.utils.Colours;
import net.yawk.client.utils.GuiUtils;

import org.lwjgl.opengl.GL11;

public class Window implements IRectangle{
	
	public String title;
	public int posX, posY, mouseXOffset, mouseYOffset;
	public boolean dragging, extended, pinned;
	protected List<AbstractComponent> components = new ArrayList<AbstractComponent>();
	private ModManager modManager;
	
	public static int TITLE_COMPONENT_SPACE = 2;
	public static int TITLE_SIZE = 12;
	
	public int minWidth;
	public int width;
	public int height;
	
	public Window(String title, ModManager modManager, int width){
		this.title = title;
		this.width = width;
		this.minWidth = width;
		this.modManager = modManager;
	}
	
	public void renderWindow(int x, int y, boolean titleVisible) {
		
		if(dragging){
			posX = x+mouseXOffset;
			posY = y+mouseYOffset;
		}
		
		if(titleVisible){
			
			drawHeader(posX, posY, posX+width, posY+TITLE_SIZE);
			
			Client.getClient().getFontRenderer().drawStringWithShadow(title,
					posX + 3,
					posY + TITLE_SIZE/2 - Client.getClient().getFontRenderer().FONT_HEIGHT/2,
					ColourType.TITLE_TEXT.getColour(),
					true);
			
			//Toggle extension
			if(hasExtensionButton()){
				drawButton(posX+width-10, posY+2, posX+width-2, posY+TITLE_SIZE-2, extended);
			}
			
			//Toggle pinned
			if(hasPinnedButton()){
				drawButton(posX+width-22, posY+2, posX+width-14, posY+TITLE_SIZE-2, pinned);
			}
		}
		
		if(extended){
			
			drawBodyRect(posX, posY+TITLE_SIZE+TITLE_COMPONENT_SPACE, posX+width, posY+TITLE_SIZE+TITLE_COMPONENT_SPACE+height, titleVisible);
			
			for(AbstractComponent c : components){
				c.draw(x, y);
			}
		}
	}
	
	protected void drawButton(int x, int y, int x1, int y1, boolean enabled){
		GuiUtils.drawRect(x, y, x1, y1, enabled? 0x9FFFFFFF:0x4FFFFFFF);
	}
	
	protected void drawHeader(int x, int y, int x1, int y1){
		GuiUtils.drawTopNodusRect(posX, posY, posX+width, posY+TITLE_SIZE);
	}
	
	protected void drawBodyRect(int x, int y, int x1, int y1, boolean titleVisible){
		GuiUtils.drawBottomNodusRect(x, y, x1, y1, titleVisible);
	}
	
	public void mouseClicked(int x, int y) {
		
		if(hasExtensionButton() && mouseOverToggleExtension(x, y)){
			onExtensionToggle();
		}else if(hasPinnedButton() && mouseOverTogglePinned(x, y)){
			onPinnedToggle();
		}else if(mouseOverTitle(x, y)){
			
			dragging = true;
			
			mouseXOffset = posX - x;
			mouseYOffset = posY - y;
			
			Client.getClient().gui.setDragging(this);
		}
		
		if(extended){
						
			for(AbstractComponent c : components){
				c.mouseClicked(x, y);
			}
		}
	}
	
	public void onGuiClosed(){
		this.dragging = false;
	}
	
	public void keyPress(char c, int key) {
		for(AbstractComponent comp : components){
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
			for(AbstractComponent c : components){
				c.mouseReleased(mouseX, mouseY, state);
			}
		}
	}
	
	public boolean mouseOverTitle(int x, int y){
		return x >= posX && x <= posX+width && y >= posY && y <= posY+TITLE_SIZE+TITLE_COMPONENT_SPACE;
	}
	
	public boolean mouseOverToggleExtension(int x, int y){
		return x >= posX+width-10 && x <= posX+width-2 && y >= posY+2 && y <= posY+TITLE_SIZE-2;
	}
	
	public boolean mouseOverTogglePinned(int x, int y){
		return x >= posX+width-22 && x <= posX+width-14 && y >= posY+2 && y <= posY+TITLE_SIZE-2;
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
	
	public void addComponent(AbstractComponent c){
		this.components.add(c);
		c.setRectangle(this);
		c.init();
		updateSize();
	}
	
	@Override
	public void updateSize(){
		
		this.height = 0;
		this.width = minWidth;
		
		for(AbstractComponent component : components){
			
			component.setY(height+TITLE_SIZE);
			height += component.getHeight();
			
			if(component.getWidth() > this.width){
				this.width = component.getWidth();
			}
		}
	}
	
	@Override
	public int getRectX() {
		return posX;
	}

	@Override
	public int getRectY() {
		return posY+TITLE_COMPONENT_SPACE;
	}
	
}
