package net.yawk.client.gui.components.buttons;

import net.yawk.client.gui.components.ArrayBox;
import net.yawk.client.gui.components.selectors.SelectorButton;
import net.yawk.client.gui.components.selectors.SelectorSystem;

public class FriendChangerButton extends ArrayBox{

	private SelectorSystem<SelectorButton> system;
	
	public FriendChangerButton(SelectorSystem<SelectorButton> system) {
		super(new String[]{ "Neutral", "Friend", "Enemy" });
		this.system = system;
	}

	@Override
	public void mouseClicked(int x, int y) {
		
		super.mouseClicked(x, y);
		
		//TODO: set player status
	}
	
}
