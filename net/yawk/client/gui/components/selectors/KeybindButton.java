package net.yawk.client.gui.components.selectors;

import net.minecraft.util.EnumChatFormatting;
import net.yawk.client.Client;
import net.yawk.client.api.PluginData;
import net.yawk.client.gui.ColourType;
import net.yawk.client.gui.IPanel;
import net.yawk.client.gui.Window;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.PluginMod;
import net.yawk.client.utils.GuiUtils;

public class KeybindButton extends SelectorButton{
	
	private Mod mod;
	private String provider;
	
	public KeybindButton(IPanel win, Mod data, SelectorSystem system) {
		super(win, data.getName(), system);
		this.mod = data;
		this.provider = getProvider(data);
	}
	
	@Override
	public void draw(int x, int y, int cx, int cy) {
		
		boolean mouseover = mouseOverButton(x, y, cx, cy);
		
		if(mouseover){
			GuiUtils.drawRect(cx, cy, cx+win.getWidth(), cy+getHeight(), 0x2FFFFFFF);
		}
		
		if(isEnabled()){
			Client.getClient().getFontRenderer().drawStringWithShadow(getText(), cx+3, cy+2, mouseover? ColourType.HIGHLIGHT.getModifiedColour():ColourType.HIGHLIGHT.getColour(), true);
			Client.getClient().getFontRenderer().drawStringWithShadow(mod.getDescription(), cx+3, cy+14, 0xFFCFCFCF, true);
			Client.getClient().getFontRenderer().drawStringWithShadow(provider, cx+3, cy+26, 0xFFCFCFCF, true);
		}else{
			Client.getClient().getFontRenderer().drawStringWithShadow(getText(), cx+3, cy+2, mouseover? ColourType.TEXT.getModifiedColour():ColourType.TEXT.getColour(), true);
			Client.getClient().getFontRenderer().drawStringWithShadow(mod.getDescription(), cx+3, cy+14, 0xFFCFCFCF, true);
			Client.getClient().getFontRenderer().drawStringWithShadow(provider, cx+3, cy+26, 0xFFCFCFCF, true);
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
		return 38;
	}
	
	public Mod getMod() {
		return mod;
	}
}
