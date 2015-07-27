package net.yawk.client.gui.hub;

import net.yawk.client.utils.ClientUtils;

public class SquareCell {
	
	private String title;
	private int colour;
	private String contents;
	
	public SquareCell(String title, int colour, String contents) {
		super();
		this.title = title;
		this.colour = colours[colour];
		this.contents = contents;
	}
	
	private static int[] colours = new int[]{
		0x5FFF0000,
		0x5F00FF00,
		0x5F0000FF,
		0x5F404000,
		0x5F004040,
		0x5F400040,
		0x5F202020,
		0x5F305010
	};
	
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
