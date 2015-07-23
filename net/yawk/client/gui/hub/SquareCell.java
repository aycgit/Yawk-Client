package net.yawk.client.gui.hub;

public class SquareCell {
	
	private String title;
	private int colour;
	private String contents;
	
	public SquareCell(String title, int colour, String contents) {
		super();
		this.title = title;
		this.colour = colour;
		this.contents = contents;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getColour() {
		return colour;
	}

	public void setColour(int colour) {
		this.colour = colour;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}
}
