package net.yawk.client.gui.hub;

public class Slate {
	
	private SquareCell[] cells;
	private String name;
	
	public Slate(String name, SquareCell[] cells) {
		super();
		this.cells = cells;
		this.name = name;
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
