package net.yawk.client.gui.components.buttons;

import net.yawk.client.gui.Window;
import net.yawk.client.gui.components.FriendScrollPane;

public class FriendsExecuteButton extends Button{
	
	private FriendScrollPane pane;
	
	public FriendsExecuteButton(Window win, FriendScrollPane pane) {
		super(win);
		this.pane = pane;
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
		
	}

	@Override
	public String getText() {
		return "";
	}

}