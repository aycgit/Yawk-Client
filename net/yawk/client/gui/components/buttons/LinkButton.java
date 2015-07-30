package net.yawk.client.gui.components.buttons;

import net.yawk.client.gui.Window;
import net.yawk.client.utils.ClientUtils;

public class LinkButton extends Button{

	private String text;
	private String link;
	
	public LinkButton(Window win, String text, String link) {
		super(win);
		this.text = text;
		this.link = link;
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
		ClientUtils.openBrowserWindow(link);
	}

	@Override
	public String getText() {
		return text;
	}

}
