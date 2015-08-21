package net.yawk.client.gui;

import java.util.ArrayList;
import java.util.List;

import net.yawk.client.Client;
import net.yawk.client.utils.GuiUtils;

public class WindowSubPanel implements IRectangle{

	private List<AbstractComponent> components;
	private IRectangle win;
	private int posX, posY, height;
	
	public WindowSubPanel(IRectangle win, int posX, int posY){
		this.win = win;
		this.posX = posX;
		this.posY = posY;
		components = new ArrayList<AbstractComponent>();
	}
	
	public void draw(int x, int y){
		
		GuiUtils.drawRect(posX+2, posY, posX+win.getWidth()-2, posY+height, 0x3FFFFFFF);
		
		for(AbstractComponent c : components){
			c.draw(x, y);
		}
	}
	
	public void addComponent(AbstractComponent c){
		components.add(c);
		c.setRectangle(this);
		updateHeight();
	}
	
	public void updateHeight(){
		
		height = 0;
		
		for(AbstractComponent component : components){
			height += component.getHeight();
			component.setY(height);
		}
	}
	
	public void mouseClicked(int x, int y) {
				
		for(AbstractComponent c : components){
			c.mouseClicked(x, y);
		}
	}
	
	@Override
	public int getWidth() {
		return win.getWidth()-4;
	}

	@Override
	public int getHeight() {
		
		int h = 0;
		
		for(AbstractComponent c : components){
			h += c.getHeight();
		}
		
		return h;
	}

	public void mouseReleased(int mouseX, int mouseY, int state) {
		
		for(AbstractComponent c : components){
			c.mouseReleased(mouseX, mouseY, state);
		}
	}

	@Override
	public int getRectX() {
		return win.getRectX()+posX;
	}

	@Override
	public int getRectY() {
		return win.getRectY()+posY;
	}
	
}
