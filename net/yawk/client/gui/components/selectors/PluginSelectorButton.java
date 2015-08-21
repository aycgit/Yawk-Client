package net.yawk.client.gui.components.selectors;

import net.yawk.client.Client;
import net.yawk.client.api.PluginData;
import net.yawk.client.gui.IRectangle;
import net.yawk.client.gui.Window;

public class PluginSelectorButton extends SelectorButton{
	
	private PluginData data;
	
	public PluginSelectorButton(String mod, SelectorSystem system, PluginData data) {
		super(mod, system);
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
