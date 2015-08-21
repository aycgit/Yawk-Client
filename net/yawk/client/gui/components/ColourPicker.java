package net.yawk.client.gui.components;

import net.yawk.client.gui.ColourType;
import net.yawk.client.gui.GuiClickable;
import net.yawk.client.gui.Window;
import net.yawk.client.gui.components.buttons.Button;
import net.yawk.client.utils.Colours;
import net.yawk.client.utils.GuiUtils;

public class ColourPicker extends Button{
	
	private ColourType col;
	private GuiClickable gui;
	
	public ColourPicker(ColourType col, GuiClickable gui) {
		super();
		this.col = col;
		this.gui = gui;
	}
	
	@Override
	public void draw(int x, int y) {
		super.draw(x, y);
		GuiUtils.drawRect(getX() + rect.getWidth() - 10, getY() + 2, getX() + rect.getWidth() - 2, getY() + 10, col.getColour());
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
