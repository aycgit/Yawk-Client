package net.yawk.client.gui.components;

import net.yawk.client.Client;
import net.yawk.client.api.PluginData;
import net.yawk.client.gui.IPanel;
import net.yawk.client.gui.Window;

public class PluginSelectorButton extends SelectorButton{
	
	private PluginData data;
	
	public PluginSelectorButton(IPanel win, String mod, SelectorSystem system, PluginData data) {
		super(win, mod, system);
		this.data = data;
	}
	
	@Override
	public String getText() {
		
		if(Client.getClient().getPluginManager().pluginEnabled(data)){
			return super.getText() + " (On)";
		}else{
			return super.getText();
		}
	}
}
