package net.yawk.client.gui.components;

import net.yawk.client.Client;
import net.yawk.client.api.PluginData;
import net.yawk.client.gui.Component;

public class PluginDisplay extends Component{

	private PluginData plugin;
	
	public PluginDisplay(){
		this(null);
	}
	
	public PluginDisplay(PluginData plugin) {
		super();
		this.plugin = plugin;
	}

	@Override
	public int getHeight() {
		return 36;
	}

	@Override
	public void draw(int x, int y, int cx, int cy) {
		
		Client.getClient().getFontRenderer().drawStringWithShadow("Name: " + plugin.getName(), cx + 3, cy + 2, 0xFFFFFFFF, true);
		Client.getClient().getFontRenderer().drawStringWithShadow("Description: " + plugin.getDescription(), cx + 3, cy + 14, 0xFFFFFFFF, true);
		Client.getClient().getFontRenderer().drawStringWithShadow("Download link: " + plugin.getFilePath(), cx + 3, cy + 26, 0xFFFFFFFF, true);
		
	}
	
	public PluginData getPlugin() {
		return plugin;
	}

	public void setPlugin(PluginData plugin) {
		this.plugin = plugin;
	}
}
