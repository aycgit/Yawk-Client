package net.yawk.client.gui.components;

import net.yawk.client.Client;
import net.yawk.client.gui.ColourType;
import net.yawk.client.gui.AbstractComponent;
import net.yawk.client.gui.Window;
import net.yawk.client.modmanager.Mod;

public class EnabledModsDisplay extends AbstractComponent {
	
	private int height;
	
	public EnabledModsDisplay(){
		
	}
	
	@Override
	public int getHeight() {
		return height * 12;
	}
	
	@Override
	public void draw(int x, int y) {
		
		height = 0;
		
		for(Mod m : Client.getClient().getModManager().mods){
			if(m.isEnabled()){
				Client.getClient().getFontRenderer().drawStringWithShadow(m.getName(), getX() + 3, getY() + height*12 + 2, ColourType.TEXT.getColour(), true);
				height++;
			}
		}
	}
}
