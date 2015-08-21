package net.yawk.client.gui.components.buttons;

import net.yawk.client.gui.IRectangle;

public abstract class TextButton extends Button{

	private String text;
	private boolean centered;
	
	public TextButton(String text, boolean centered) {
		super();
		this.text = text;
		this.centered = centered;
	}

	@Override
	public boolean isCentered() {
		return centered;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}
	
	@Override
	public String getText() {
		return text;
	}
	
}
