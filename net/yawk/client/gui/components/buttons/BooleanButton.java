package net.yawk.client.gui.components.buttons;

import net.yawk.client.gui.IRectangle;
import net.yawk.client.modmanager.values.BooleanValue;

public class BooleanButton extends Button{

	private BooleanValue val;
	
	public BooleanButton(BooleanValue val) {
		super();
		this.val = val;
	}

	@Override
	public boolean isCentered() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return val.getValue();
	}

	@Override
	public void toggle() {
		val.setValue(!val.getValue());
	}

	@Override
	public String getText() {
		return val.getName();
	}

}