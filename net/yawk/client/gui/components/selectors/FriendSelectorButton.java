package net.yawk.client.gui.components.selectors;

import java.util.Arrays;

import net.yawk.client.Client;
import net.yawk.client.friends.FriendType;
import net.yawk.client.gui.components.buttons.FriendChangerButton;

public class FriendSelectorButton extends SelectorButton{

	private FriendChangerButton changerButton;
	
	public FriendSelectorButton(String text, SelectorSystem system, FriendChangerButton changerButton) {
		super(text, system);
		this.changerButton = changerButton;
	}
	
	@Override
	public void toggle() {
		
		super.toggle();
		
		FriendType type = Client.getClient().getFriendManager().getFriendType(text);
		
		if(type == null){
			changerButton.setIndex(0);
		}else{
			changerButton.setIndex(Arrays.asList(FriendType.values()).indexOf(type));
		}
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
