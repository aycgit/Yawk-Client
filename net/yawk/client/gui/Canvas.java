package net.yawk.client.gui;

import java.util.ArrayList;

import net.yawk.client.gui.components.Component;

public class Canvas implements IPanel{
	
	private int posX, posY, width, height;
	public ArrayList<Component> components = new ArrayList<Component>();
	
	public Canvas(int posX, int posY, int width, int height) {
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;
	}
	
	public void draw(int x, int y){
		
		int h = 0;
		
		for(Component comp : components){
			comp.draw(x, y, posX, posY+h);
			h += comp.getHeight();
		}
	}
	
	public void mouseClicked(int x, int y) {
		
		int h = 0;
		
		for(Component comp : components){
			comp.mouseClicked(x, y, posX, posY+h);
			h += comp.getHeight();
		}
	}
	
	public void keyPress(char c, int key) {
		
		int h = 0;
		
		for(Component comp : components){
			comp.keyPress(key, c);
			h += comp.getHeight();
		}
	}
	
	public void mouseReleased(int x, int y, int state) {
		
		int h = 0;
		
		for(Component comp : components){
			comp.mouseReleased(x, y, state);
			h += comp.getHeight();
		}
	}
	
	@Override
	public int getWidth() {
		return width;
	}
	
	@Override
	public int getHeight() {
		return height;
	}
}
