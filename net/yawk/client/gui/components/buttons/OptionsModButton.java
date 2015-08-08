package net.yawk.client.gui.components.buttons;

import net.yawk.client.Client;
import net.yawk.client.gui.Window;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.values.Value;

public class OptionsModButton extends ModButton{
	
	private boolean extended;
	
	public OptionsModButton(Window win, Mod mod) {
		super(win, mod);
	}
	
	@Override
	public void draw(int x, int y, int cx, int cy) {
		
		super.draw(x, y, cx, cy);
		
		int line = 1;
		
		System.out.println(mod.getName());
		
		for(Value val : mod.getOptions()){
			Client.getClient().getFontRenderer().drawStringWithShadow(val.getName(), cx, cy + line++*12, 0xFFFFFFFF, true);
		}
	}

	@Override
	public void mouseClicked(int x, int y, int cx, int cy) {
		// TODO Auto-generated method stub
		super.mouseClicked(x, y, cx, cy);
	}

	@Override
	public int getHeight() {
		
		if(extended){
			return super.getHeight() + mod.getOptions().length * 12;
		}else{
			return super.getHeight();
		}
	}
}