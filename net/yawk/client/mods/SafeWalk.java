package net.yawk.client.mods;

import net.yawk.client.Client;
import net.yawk.client.events.EventMoveEntity;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.ModType;

import com.darkmagician6.eventapi.EventTarget;
import com.darkmagician6.eventapi.types.EventType;

public class SafeWalk implements Mod{
	
	private boolean wasSneaking;
	
	public SafeWalk(){
		
	}
	
	@Override
	public String getName() {
		return "SafeWalk";
	}
	
	@Override
	public String getDescription() {
		return "Stops you falling off edges";
	}
	
	@EventTarget
	public void onMove(EventMoveEntity e){
		if(e.type == EventType.PRE){
			wasSneaking = Client.getClient().getPlayer().movementInput.sneak;
			Client.getClient().getPlayer().movementInput.sneak = true;
		}else if(e.type == EventType.POST){
			Client.getClient().getPlayer().movementInput.sneak = wasSneaking;
		}
	}
	
	@Override
	public ModType getType() {
		return ModType.MOVEMENT;
	}
	
	@Override
	public void onEnable() {
		
	}
	
	@Override
	public void onDisable() {
		
	}
}
