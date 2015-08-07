package net.yawk.client.gui.components.buttons;

import net.yawk.client.gui.IPanel;

public abstract class TextButton extends Button{

	private String text;
	private boolean centered;
	
	public TextButton(IPanel win, String text, boolean centered) {
		super(win);
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
