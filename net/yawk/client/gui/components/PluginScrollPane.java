package net.yawk.client.gui.components;

import java.util.ArrayList;

import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.yawk.client.Client;
import net.yawk.client.api.PluginData;
import net.yawk.client.gui.Window;
import net.yawk.client.gui.components.selectors.PluginSelectorButton;
import net.yawk.client.gui.components.selectors.SelectorButton;
import net.yawk.client.gui.components.selectors.SelectorSystem;

public class PluginScrollPane extends ScrollPane{
	
	private SelectorSystem<SelectorButton> system;
	
	public PluginScrollPane(Window win, int height, SelectorSystem<SelectorButton> system) {
		super(win, height);
		this.system = system;
	}
	
	private boolean hasFoundPlugins;
	
	@Override
	public void draw(int x, int y, int cx, int cy) {
		
		if(!hasFoundPlugins){
			if(Client.getClient().getPluginManager().pluginData.size() > 0){
				for(PluginData plugin : Client.getClient().getPluginManager().pluginData){
					components.add(system.add(new PluginSelectorButton(this, plugin.getName(), system, plugin)));
					hasFoundPlugins = true;
				}
			}
		}
		
		super.draw(x, y, cx, cy);
	}
}
