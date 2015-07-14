package net.yawk.client.gui.components;

import net.yawk.client.Client;
import net.yawk.client.gui.Window;
import net.yawk.client.modmanager.Mod;

public class EnabledModsDisplay extends Component {
	
	private int height;
	protected Window win;
	
	public EnabledModsDisplay(Window win){
		this.win = win;
	}
	
	@Override
	public int getHeight() {
		return height * 12;
	}
	
	@Override
	public void draw(int x, int y, int cx, int cy) {
		
		height = 0;
		
		for(Mod m : Client.getClient().getModManager().mods){
			if(Client.getClient().getModManager().dataMap.get(m).isEnabled){
				Client.getClient().getFontRenderer().drawStringWithShadow(m.getName(), cx + 3, cy + height*12 + 2, 0xFFFFFFFF, true);
				height++;
			}
		}
	}
}
