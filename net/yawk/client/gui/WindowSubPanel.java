package net.yawk.client.gui;

import java.util.ArrayList;
import java.util.List;

import net.yawk.client.Client;
import net.yawk.client.utils.GuiUtils;

public class WindowSubPanel implements IRectangle{

	private List<AbstractComponent> components;
	private IRectangle win;
	private int height, yOffset;
	private AbstractComponent component;
	
	public WindowSubPanel(IRectangle win, AbstractComponent component, int yOffset){
		this.win = win;
		this.component = component;
		this.yOffset = yOffset;
		components = new ArrayList<AbstractComponent>();
	}
	
	public void draw(int x, int y){
		
		GuiUtils.drawRect(component.getX()+2, component.getY()+yOffset, component.getX()+2+getWidth(), component.getY()+yOffset+height, 0x3FFFFFFF);
		
		for(AbstractComponent c : components){
			c.draw(x, y);
		}
	}
	
	public void addComponent(AbstractComponent c){
		components.add(c);
		c.setRectangle(this);
		c.init();
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
		return height;
	}

	public void mouseReleased(int mouseX, int mouseY, int state) {
		
		for(AbstractComponent c : components){
			c.mouseReleased(mouseX, mouseY, state);
		}
	}

	@Override
	public int getRectX() {
		return component.getX();
	}

	@Override
	public int getRectY() {
		return component.getY();
	}
	
}
