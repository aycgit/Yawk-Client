package net.yawk.client.gui.components;

import net.yawk.client.Client;
import net.yawk.client.gui.AbstractComponent;

public class TextDisplay extends AbstractComponent{

	private String text;
	
	public TextDisplay(String text) {
		super();
		this.text = text;
	}

	@Override
	public int getHeight() {
		return 12;
	}

	@Override
	public void draw(int x, int y) {
		Client.getClient().getFontRenderer().drawStringWithShadow(text, getX() + 3, getY() + 2, 0xFFFFFFFF, true);
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
