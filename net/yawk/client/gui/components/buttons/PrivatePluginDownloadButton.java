package net.yawk.client.gui.components.buttons;

import net.yawk.client.gui.IPanel;
import net.yawk.client.gui.components.PluginDisplay;

public class PrivatePluginDownloadButton extends Button{

	private PluginDisplay pluginDisplay;
	
	public PrivatePluginDownloadButton(IPanel win, PluginDisplay pluginDisplay) {
		super(win);
		this.pluginDisplay = pluginDisplay;
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
		
	}

	@Override
	public String getText() {
		return null;
	}

}
