package net.yawk.client.gui.hub;

public class Slate {
	
	private SquareCell[] cells;
	
	public Slate(SquareCell[] cells) {
		super();
		this.cells = cells;
	}
	
	public SquareCell[] getCells() {
		return cells;
	}
	
	public void setCells(SquareCell[] cells) {
		this.cells = cells;
	}
}
