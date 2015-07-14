package net.yawk.client.mods;

import net.yawk.client.Client;
import net.yawk.client.events.EventJump;
import net.yawk.client.events.EventMoveEntity;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.ModType;

import com.darkmagician6.eventapi.EventTarget;
import com.darkmagician6.eventapi.types.EventType;

public class HighJump implements Mod{
	
	public HighJump(){
		
	}
	
	@Override
	public String getName() {
		return "HighJump";
	}
	
	@Override
	public String getDescription() {
		return "Jump higher";
	}
	
	@EventTarget
	public void onJump(EventJump e){
		if(e.type == EventType.POST){
			Client.getClient().getPlayer().motionY = 1.5f;
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
