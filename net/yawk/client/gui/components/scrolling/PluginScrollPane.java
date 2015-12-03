package net.yawk.client.gui.components.scrolling;

import java.util.ArrayList;

import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.yawk.client.Client;
import net.yawk.client.api.PluginData;
import net.yawk.client.gui.IRectangle;
import net.yawk.client.gui.Window;
import net.yawk.client.gui.components.selectors.LargePluginSelectorButton;
import net.yawk.client.gui.components.selectors.PluginSelectorButton;
import net.yawk.client.gui.components.selectors.SelectorButton;
import net.yawk.client.gui.components.selectors.SelectorSystem;

public class PluginScrollPane extends FilterableScrollPane{
	
	private SelectorSystem<SelectorButton> system;
	private boolean description;
	
	public PluginScrollPane(int height, SelectorSystem<SelectorButton> system, boolean description) {
		super(height);
		this.system = system;
		this.description = description;
	}
	
	private boolean hasFoundPlugins;
	
	@Override
	public void draw(int x, int y) {
		
		if(!hasFoundPlugins){
			if(Client.getClient().getPluginManager().pluginData.size() > 0){
				for(PluginData plugin : Client.getClient().getPluginManager().pluginData){
					
					if(description){
						addFilterableComponent(system.add(new LargePluginSelectorButton(plugin, system)));
					}else{
						addFilterableComponent(system.add(new PluginSelectorButton(plugin.getName(), system, plugin)));
					}
					
					hasFoundPlugins = true;
				}
			}
		}
		
		super.draw(x, y);
	}
}
