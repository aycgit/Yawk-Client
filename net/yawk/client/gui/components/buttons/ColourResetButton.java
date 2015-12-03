package net.yawk.client.gui.components.buttons;

import net.yawk.client.gui.ColourType;
import net.yawk.client.gui.Window;
import net.yawk.client.utils.ClientUtils;

public class ColourResetButton extends Button{
	
	public ColourResetButton() {
		super();
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
		for(ColourType col : ColourType.values()){
			col.setIndex(col.getDefaultIndex());
		}
	}

	@Override
	public String getText() {
		return "Reset Colours";
	}

}
