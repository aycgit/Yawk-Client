package net.yawk.client.gui.components.selectors;

import net.yawk.client.Client;
import net.yawk.client.api.PluginData;
import net.yawk.client.gui.ColourType;
import net.yawk.client.gui.IPanel;
import net.yawk.client.gui.Window;

public class LargePluginSelectorButton extends SelectorButton{
	
	private PluginData data;
	
	public LargePluginSelectorButton(IPanel win, PluginData data, SelectorSystem system) {
		super(win, data.getName(), system);
		this.data = data;
	}
	
	@Override
	public void draw(int x, int y, int cx, int cy) {
		
		if(isEnabled()){
			Client.getClient().getFontRenderer().drawStringWithShadow(getText(), cx+3, cy+2, mouseOverButton(x, y, cx, cy)? ColourType.HIGHLIGHT.getModifiedColour():ColourType.HIGHLIGHT.getColour(), true);
			Client.getClient().getFontRenderer().drawStringWithShadow(data.getDescription(), cx+3, cy+14, 0xFFCFCFCF, true);
		}else{
			Client.getClient().getFontRenderer().drawStringWithShadow(getText(), cx+3, cy+2, mouseOverButton(x, y, cx, cy)? ColourType.TEXT.getModifiedColour():ColourType.TEXT.getColour(), true);
			Client.getClient().getFontRenderer().drawStringWithShadow(data.getDescription(), cx+3, cy+14, 0xFFCFCFCF, true);
		}
		
	}
	
	@Override
	public String getText() {
		
		if(Client.getClient().getPluginManager().pluginEnabled(data)){
			return super.getText() + " (On)";
		}else{
			return super.getText();
		}
	}

	@Override
	public int getHeight() {
		return 26;
	}
}
