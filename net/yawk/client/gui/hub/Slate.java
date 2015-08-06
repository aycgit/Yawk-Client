package net.yawk.client.gui.hub;

import net.yawk.client.Client;
import net.yawk.client.utils.GuiUtils;

public class Slate {
	
	private SquareCell[] cells;
	private String name;
	protected GuiHub hub;
	
	public Slate(String name, SquareCell[] cells, GuiHub hub) {
		super();
		this.cells = cells;
		this.name = name;
		this.hub = hub;
	}
	
	public void renderSlate(int x, int y, int xOffset){

		int alignXMiddle = hub.width/2 - (5*hub.cellSize)/2;

		int lineNum = cells.length / 5;
		int alignYMiddle = hub.height/2 - ((lineNum+2)*hub.cellSize)/2;

		int num = 0;

		int w = hub.cellSize - hub.cellPadding;
		int h = hub.cellSize - hub.cellPadding;

		for(SquareCell s : cells){
			
			int column = num % 5;
			int row = num / 5;
			
			int xPos = xOffset + column*hub.cellSize + alignXMiddle + hub.cellPadding;
			int yPos = alignYMiddle + row*hub.cellSize + hub.cellPadding;
			
			s.renderCell(s, hub.colourModifier, x, y, xPos, yPos, w, h);
			
			num++;
		}
	}
	
	public SquareCell[] getCells() {
		return cells;
	}
	
	public void setCells(SquareCell[] cells) {
		this.cells = cells;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
