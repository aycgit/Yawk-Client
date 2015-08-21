package net.yawk.client.gui.components.scrolling;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.yawk.client.Client;
import net.yawk.client.gui.Window;
import net.yawk.client.gui.components.selectors.SelectorButton;
import net.yawk.client.gui.components.selectors.SelectorSystem;

public class PlayerScrollPane extends ScrollPane{
	
	private SelectorSystem<SelectorButton> system;
	
	public PlayerScrollPane(int height, SelectorSystem<SelectorButton> system) {
		super(height);
		this.system = system;
	}
	
	@Override
	public void draw(int x, int y) {
		
		ArrayList<String> players = getPlayers();
		ArrayList<SelectorButton> offline = new ArrayList<SelectorButton>();
		ArrayList<String> newPlayers = new ArrayList<String>();
		
		for(SelectorButton b : system.buttons){
			if(!players.contains(b.getStaticText())){
				offline.add(b);
			}
		}
		
		for(String p : players){
			if(playerNotFound(p)){
				newPlayers.add(p);
			}
		}
		
		for(SelectorButton b : offline){
			components.remove(b);
			system.buttons.remove(b);
		}
		
		for(String newPlayer : newPlayers){
			components.add(system.add(new SelectorButton(newPlayer, system)));
		}
		
		super.draw(x, y);
	}
	
	private boolean playerNotFound(String p){
		
		for(SelectorButton b : system.buttons){
			
			if(b.getStaticText().equalsIgnoreCase(p)){
				return false;
			}
		}
		
		return true;
	}
	
	private ArrayList<String> getPlayers(){
		
		ArrayList<String> list = new ArrayList<String>();
		
		NetHandlerPlayClient net = Client.getClient().getPlayer().sendQueue;
		
		for(Object obj : net.playerInfoMap.values()){
			//func_178845_a() is the GameProfile of the player
			//getName() is the username of the player
			list.add(((NetworkPlayerInfo)obj).func_178845_a().getName());
		}
		
		return list;
	}
}
