package net.yawk.client.gui.components.selectors;

import net.minecraft.util.EnumChatFormatting;
import net.yawk.client.Client;
import net.yawk.client.api.PluginData;
import net.yawk.client.gui.ColourType;
import net.yawk.client.gui.IRectangle;
import net.yawk.client.gui.Window;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.PluginMod;
import net.yawk.client.utils.GuiUtils;

public class KeybindButton extends SelectorButton{
	
	private Mod mod;
	private String provider;
	
	public KeybindButton(Mod data, SelectorSystem system) {
		super(data.getName(), system);
		this.mod = data;
		this.provider = getProvider(data);
	}
	
	@Override
	public void draw(int x, int y) {
		
		boolean mouseover = mouseOverButton(x, y, getX(), getY());
		
		if(mouseover){
			GuiUtils.drawRect(getX(), getY(), getX()+rect.getWidth(), getY()+getHeight(), 0x2FFFFFFF);
		}
		
		if(isEnabled()){
			Client.getClient().getFontRenderer().drawStringWithShadow(getText(), getX()+3, getY()+2, mouseover? ColourType.HIGHLIGHT.getModifiedColour():ColourType.HIGHLIGHT.getColour(), true);
			Client.getClient().getFontRenderer().drawStringWithShadow(mod.getDescription(), getX()+3, getY()+14, 0xFFCFCFCF, true);
			Client.getClient().getFontRenderer().drawStringWithShadow(provider, getX()+3, getY()+26, 0xFFCFCFCF, true);
		}else{
			Client.getClient().getFontRenderer().drawStringWithShadow(getText(), getX()+3, getY()+2, mouseover? ColourType.TEXT.getModifiedColour():ColourType.TEXT.getColour(), true);
			Client.getClient().getFontRenderer().drawStringWithShadow(mod.getDescription(), getX()+3, getY()+14, 0xFFCFCFCF, true);
			Client.getClient().getFontRenderer().drawStringWithShadow(provider, getX()+3, getY()+26, 0xFF8F8F8F, true);
		}
		
	}
	
	private String getProvider(Mod m) {
		
		if(m instanceof PluginMod){
			PluginData plugin = ((PluginMod)m).getPluginData();
			return "Plugin: " + plugin.getName() + " Version " + plugin.getVersion();
		}else{
			return "Default";
		}
	}
	
	@Override
	public String getText() {
		
		if(mod.getKeybind() != -1){
			return super.getText() + EnumChatFormatting.GREEN + " (" + mod.getKeyName() + ")";
		}else{
			return super.getText();
		}
	}

	@Override
	public int getHeight() {
		return 36;
	}
	
	public Mod getMod() {
		return mod;
	}
}
