package net.yawk.client.gui.components;

import net.minecraft.client.gui.FontRenderer;
import net.yawk.client.Client;
import net.yawk.client.api.PluginData;
import net.yawk.client.gui.Component;

public class PluginDisplay extends Component{

	private PluginData plugin;
	private FontRenderer fontRenderer;
	
	public PluginDisplay(){
		this(null);
	}
	
	public PluginDisplay(PluginData plugin) {
		super();
		this.plugin = plugin;
		this.fontRenderer = Client.getClient().getFontRenderer();
	}

	@Override
	public int getHeight() {
		return 36;
	}

	@Override
	public void draw(int x, int y, int cx, int cy) {
		
		if(plugin == null){
			fontRenderer.drawStringWithShadow("No Plugin selected", cx + 3, cy + 2, 0xFFFFFFFF, true);
		}else{
			fontRenderer.drawStringWithShadow("Name: " + plugin.getName(), cx + 3, cy + 2, 0xFFFFFFFF, true);
			fontRenderer.drawStringWithShadow("Description: " + plugin.getDescription(), cx + 3, cy + 14, 0xFFFFFFFF, true);
			fontRenderer.drawStringWithShadow("Download link: " + plugin.getFilePath(), cx + 3, cy + 26, 0xFFFFFFFF, true);
		}
		
	}
	
	public PluginData getPlugin() {
		return plugin;
	}

	public void setPlugin(PluginData plugin) {
		this.plugin = plugin;
	}
}
