package net.yawk.client.mods.movement;

import net.yawk.client.Client;
import net.yawk.client.events.EventJump;
import net.yawk.client.events.EventMoveEntity;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.ModDetails;
import net.yawk.client.modmanager.ModType;

import com.darkmagician6.eventapi.EventTarget;
import com.darkmagician6.eventapi.types.EventType;

@ModDetails(name = "HighJump", defaultKey = 0, desc = "Jump higher", type = ModType.MOVEMENT)
public class HighJump extends Mod{
	
	public HighJump(){
		
	}
		
	@EventTarget
	public void onJump(EventJump e){
		if(e.type == EventType.POST){
			Client.getClient().getPlayer().motionY = 1.5f;
		}
	}
		
	@Override
	public void onEnable() {
		
	}

	@Override
	public void onDisable() {
		
	}
}
