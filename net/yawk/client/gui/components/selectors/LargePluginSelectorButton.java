package net.yawk.client.gui.components.selectors;

import net.yawk.client.Client;
import net.yawk.client.api.PluginData;
import net.yawk.client.gui.ColourType;
import net.yawk.client.gui.IRectangle;
import net.yawk.client.gui.Window;
import net.yawk.client.utils.GuiUtils;

public class LargePluginSelectorButton extends SelectorButton{
	
	private PluginData data;
	
	public LargePluginSelectorButton(PluginData data, SelectorSystem system) {
		super(data.getName(), system);
		this.data = data;
	}
	
	@Override
	public void draw(int x, int y) {
		
		boolean mouseover = mouseOverButton(x, y, getX(), getY());
		
		if(mouseover){
			GuiUtils.drawRect(getX(), getY(), getX()+rect.getWidth(), getY()+getHeight(), 0x2FFFFFFF);
		}
		
		if(isEnabled()){
			Client.getClient().getFontRenderer().drawStringWithShadow(getText(), getX()+3, getY()+2, mouseover? ColourType.HIGHLIGHT.getModifiedColour():ColourType.HIGHLIGHT.getColour(), true);
			Client.getClient().getFontRenderer().drawStringWithShadow(data.getDescription(), getX()+3, getY()+14, 0xFFCFCFCF, true);
		}else{
			Client.getClient().getFontRenderer().drawStringWithShadow(getText(), getX()+3, getY()+2, mouseover? ColourType.TEXT.getModifiedColour():ColourType.TEXT.getColour(), true);
			Client.getClient().getFontRenderer().drawStringWithShadow(data.getDescription(), getX()+3, getY()+14, 0xFFCFCFCF, true);
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
