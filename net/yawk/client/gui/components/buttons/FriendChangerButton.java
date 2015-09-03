package net.yawk.client.gui.components.buttons;

import net.yawk.client.Client;
import net.yawk.client.friends.FriendType;
import net.yawk.client.gui.components.ArrayBox;
import net.yawk.client.gui.components.selectors.SelectorButton;
import net.yawk.client.gui.components.selectors.SelectorSystem;

public class FriendChangerButton extends ArrayBox<FriendType>{

	private SelectorSystem<SelectorButton> system;
	
	public FriendChangerButton(SelectorSystem<SelectorButton> system) {
		super(FriendType.values());
		this.system = system;
	}

	@Override
	public void mouseClicked(int x, int y) {
		
		super.mouseClicked(x, y);
		
		if(system.selectedButton != null){
			
			Client.getClient().getFriendManager().setFriendType(system.selectedButton.getStaticText(), getSelectedOption());
		}
	}
	
}
