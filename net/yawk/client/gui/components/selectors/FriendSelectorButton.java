package net.yawk.client.gui.components.selectors;

import net.yawk.client.Client;
import net.yawk.client.friends.FriendType;
import net.yawk.client.gui.components.buttons.FriendChangerButton;

public class FriendSelectorButton extends SelectorButton{

	public FriendSelectorButton(String text, SelectorSystem system) {
		super(text, system);
	}
	
	@Override
	public String getText() {
		
		FriendType type = Client.getClient().getFriendManager().getFriendType(text);
		
		if(type != null){
			return text + "(" + type.toString() + ")";
		}else{
			return text;
		}
	}
}
