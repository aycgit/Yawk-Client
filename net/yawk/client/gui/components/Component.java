package net.yawk.client.gui.components;

public abstract class Component {

	public void draw(int x, int y, int cx, int cy){
		
	}
	
	public void keyPress(int key, char c){
		
	}
	
	public void mouseClicked(int x, int y, int cx, int cy){
		
	}
	
	public void mouseReleased(int mouseX, int mouseY, int state) {
		
	}
	
	public abstract int getHeight();
	
}
