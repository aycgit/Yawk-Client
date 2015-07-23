package net.yawk.client.gui.components;

import net.yawk.client.gui.ColourType;
import net.yawk.client.gui.GuiClickable;
import net.yawk.client.gui.Window;
import net.yawk.client.utils.Colours;
import net.yawk.client.utils.GuiUtils;

public class ColourPicker extends Button{
	
	private ColourType col;
	private GuiClickable gui;
	
	public ColourPicker(Window win, ColourType col, GuiClickable gui) {
		super(win);
		this.col = col;
		this.gui = gui;
	}
	
	@Override
	public void draw(int x, int y, int cx, int cy) {
		super.draw(x, y, cx, cy);
		GuiUtils.drawBorderedRect(cx + win.getWidth() - 11, cy + 1, cx + win.getWidth() - 1, cy + 11, 1, Colours.GRAY, col.getColour());
	}
	
	@Override
	public boolean isCentered() {
		return false;
	}
	
	@Override
	public boolean isEnabled() {
		return false;
	}
	
	@Override
	public void toggle() {
		col.cycleIndex();
	}
	
	@Override
	public String getText() {
		return col.getName();
	}
}
