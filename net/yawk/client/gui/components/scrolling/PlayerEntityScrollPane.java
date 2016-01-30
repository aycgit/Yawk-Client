package net.yawk.client.gui.components.scrolling;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.entity.player.EntityPlayer;
import net.yawk.client.Client;
import net.yawk.client.gui.Window;
import net.yawk.client.gui.components.selectors.EntitySelectorButton;
import net.yawk.client.gui.components.selectors.SelectorButton;
import net.yawk.client.gui.components.selectors.SelectorSystem;

public class PlayerEntityScrollPane extends ScrollPane{
	
	private SelectorSystem<SelectorButton> system;
	private Minecraft mc;
	
	public PlayerEntityScrollPane(int height, SelectorSystem<SelectorButton> system) {
		super(height);
		this.system = system;
		this.mc = Minecraft.getMinecraft();
	}
	
	@Override
	public void draw(int x, int y) {
		
		List<EntityPlayer> players = (List<EntityPlayer>) mc.theWorld.playerEntities;
		
		Iterator<SelectorButton> it = system.buttons.iterator();
		
		while(it.hasNext()){
			if(entityNotFound(it.next().getStaticText(), players)){
				it.remove();
			}
		}
		
		for(EntityPlayer player : players){
			if(playerNotFound(player.getName())){
				addComponent(system.add(new EntitySelectorButton(player, system)));
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
	
	private boolean entityNotFound(String playerName, List<EntityPlayer> players){
		
		for(EntityPlayer player : players){
			
			if(player.getName().equalsIgnoreCase(playerName)){
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
