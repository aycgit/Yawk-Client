package net.yawk.client.gui.hub;

import net.yawk.client.Client;
import net.yawk.client.gui.IPanel;
import net.yawk.client.gui.components.buttons.Button;
import net.yawk.client.gui.components.selectors.KeybindButton;
import net.yawk.client.gui.components.selectors.SelectorSystem;
import net.yawk.client.modmanager.Mod;

public class KeybindSetButton extends Button{
	
	private boolean focus;
	private SelectorSystem<KeybindButton> system;
	
	public KeybindSetButton(IPanel win, SelectorSystem<KeybindButton> system) {
		super(win);
		this.system = system;
	}

	@Override
	public boolean isCentered() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}

	@Override
	public void toggle() {
		focus = true;
	}

	@Override
	public String getText() {
		
		if(system.selectedButton != null){
			return focus? "Press any Key":"Set Keybind";
		}else{
			return "";
		}
	}

	@Override
	public void mouseClicked(int x, int y, int cx, int cy) {
		
		if(mouseOverButton(x, y, cx, cy)){
			Client.getClient().getMinecraft().thePlayer.playSound("random.click", 1, 1);
			toggle();
		}else{
			focus = false;
		}
	}

	@Override
	public void keyPress(int key, char c) {
		
		if(system.selectedButton != null && focus){
			system.selectedButton.getMod().setKeybind(key);
			focus = false;
		}
	}
}
