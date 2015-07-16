package net.yawk.client.mods.movement;

import net.yawk.client.Client;
import net.yawk.client.events.EventTick;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.ModType;

import com.darkmagician6.eventapi.EventTarget;

public class Climb implements Mod{
	
	public Climb(){
		
	}
	
	@Override
	public String getName() {
		return "Climb";
	}
	
	@Override
	public String getDescription() {
		return "Climb up walls";
	}
	
	@EventTarget
	public void onTick(EventTick e){
		if(Client.getClient().getPlayer().isCollidedHorizontally){
			Client.getClient().getPlayer().motionY = 0.25f;
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
