package me.riverhouse.candy.gui.screen.clickGui;

import java.awt.Dimension;

public class Interactable {

	private int xPos, yPos, yBase;
	private Dimension area;

	public Interactable(int xPos, int yPos, int width, int height){
		this.xPos = xPos;
		this.yPos = yPos;
		this.area = new Dimension(width, height);
	}

	public void onMousePress(int x, int y, int button){
	}

	public void onMouseRelease(int x, int y, int button){
	}

	public void onMouseDrag(int x, int y){
	}

	public void onMouseScroll(int amt){
	}

	public void onKeyPress(int key){
	}

	public void onKeyRelease(int key){
	}

	public boolean isMouseOver(int x, int y){
		return(x >= xPos && y >= yPos && x <= xPos + area.width && y <= yPos + area.height);
	}

	public int getX(){
		return xPos;
	}

	public void setX(int xPos){
		this.xPos = xPos;
	}

	public int getY(){
		return yPos;
	}

	public void setY(int yPos){
		this.yPos = yPos;
	}

	public void setBaseY(int yBase){
		this.yBase = yBase;
	}
	
	public int getBaseY(){
		return this.yBase;
	}
	
	public Dimension getArea(){
		return area;
	}

	public void setArea(Dimension area){
		this.area = area;
	}

}
