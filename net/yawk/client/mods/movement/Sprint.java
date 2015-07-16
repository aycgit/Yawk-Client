package net.yawk.client.mods.movement;

import net.minecraft.network.play.client.C03PacketPlayer;
import net.yawk.client.Client;
import net.yawk.client.events.EventMoveEntity;
import net.yawk.client.events.EventTick;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.ModType;
import net.yawk.client.utils.ClientUtils;

import com.darkmagician6.eventapi.EventTarget;
import com.darkmagician6.eventapi.types.EventType;

public class Sprint implements Mod{
	
	@Override
	public String getName() {
		return "Sprint";
	}
	
	@Override
	public String getDescription() {
		return "Sprint automatically";
	}
	
	@EventTarget
	public void onTick(EventTick e){
		Client.getClient().getPlayer().setSprinting(Client.getClient().getPlayer().moveForward > 0);
	}
	
	@Override
	public ModType getType() {
		return ModType.MOVEMENT;
	}
	
	public void onEnable(){
		
	}
	
	public void onDisable(){
		
	}
}
