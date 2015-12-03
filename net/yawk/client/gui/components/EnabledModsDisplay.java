package net.yawk.client.gui.components;

import net.yawk.client.Client;
import net.yawk.client.gui.ColourType;
import net.yawk.client.gui.AbstractComponent;
import net.yawk.client.gui.Window;
import net.yawk.client.modmanager.Mod;

public class EnabledModsDisplay extends AbstractComponent {
	
	@Override
	public int getHeight() {
		
		int enabledMods = 0;
		
		for(Mod m : Client.getClient().getModManager().mods){
			if(m.isEnabled()){
				enabledMods++;
			}
		}
		
		return enabledMods * 12;
	}
	
	@Override
	public void draw(int x, int y) {
		
		int enabledMods = 0;
		
		for(Mod m : Client.getClient().getModManager().mods){
			if(m.isEnabled()){
				Client.getClient().getFontRenderer().drawStringWithShadow(m.getName(), getX() + 3, getY() + enabledMods*12 + 2, ColourType.TEXT.getColour(), true);
				enabledMods++;
			}
		}
	}

	@Override
	public void onModManagerChange() {
		rect.updateSize();
	}
}
