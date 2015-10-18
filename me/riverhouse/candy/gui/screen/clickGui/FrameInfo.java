package me.riverhouse.candy.gui.screen.clickGui;

import java.util.ArrayList;

public class FrameInfo {

	private String name;
	private int x,y;
	private boolean pinned, maximized;
	
	public FrameInfo(String name, int x, int y, boolean pinned, boolean maximized) {
		this.name = name;
		this.x = x;
		this.y = y;
		this.pinned = pinned;
		this.maximized = maximized;
	}

	public String getName() {
		return name;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isPinned() {
		return pinned;
	}

	public boolean isMaximized() {
		return maximized;
	}
	
}
