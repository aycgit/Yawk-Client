package me.riverhouse.candy.gui.mint.parts;

import me.riverhouse.candy.utils.gui.Dimension;

public class MintPart {

	private int x,y,z;
	private Dimension size;
	
	public MintPart(int x, int y, int z, Dimension size) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.size = size;
	}
	
	public void renderPart(){}
	
}
