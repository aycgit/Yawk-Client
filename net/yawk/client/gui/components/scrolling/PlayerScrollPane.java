package net.yawk.client.gui.components.scrolling;

import java.util.ArrayList;
import java.util.Iterator;
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
		
		Iterator<SelectorButton> it = system.buttons.iterator();
		
		while(it.hasNext()){
			if(!players.contains(it.next().getStaticText())){
				it.remove();
			}
		}
		
		for(String p : players){
			if(playerNotFound(p)){
				addComponent(system.add(new SelectorButton(p, system)));
			}
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
