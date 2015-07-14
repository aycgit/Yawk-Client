package net.yawk.client.gui.components;

import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.network.play.server.S02PacketChat;
import net.yawk.client.Client;
import net.yawk.client.events.EventRecievePacket;
import net.yawk.client.gui.Window;
import net.yawk.client.utils.HysteriaTimer;

import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.EventTarget;

public class AdminExecuteButton extends Button{

	private SelectorSystem<SelectorButton> system;
	private ArrayBox<String> arrayBox;
	private HashMap<String, String> commands;
	
	public AdminExecuteButton(Window win, ArrayBox<String> arrayBox, SelectorSystem<SelectorButton> system, HashMap<String,String> commands) {
		super(win);
		this.arrayBox = arrayBox;
		this.system = system;
		this.commands = commands;
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
	public void draw(int x, int y, int cx, int cy) {
		
		super.draw(x, y, cx, cy);
	}
	
	@Override
	public void toggle() {
		if(system.selectedButton != null){
			Client.getClient().getPlayer().sendChatMessage(getCommand(fixOption(arrayBox.getSelectedOption())).replace("%user", system.selectedButton.getStaticText()));
		}
	}
	
	private String getCommand(String first){
		return commands.get(first);
	}
	
	private String fixOption(String option){
		return option.replace("< ", "").replace(" >", "");
	}
	
	@Override
	public String getText() {
		return "Change";
	}
}
