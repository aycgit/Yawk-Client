package net.yawk.client.mods.movement;

import net.yawk.client.Client;
import net.yawk.client.events.EventTick;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.ModDetails;

import com.darkmagician6.eventapi.EventTarget;

@ModDetails(name = "Climb", desc = "Climb up walls", type = Mod.Type.MOVEMENT)
public class Climb extends Mod{
	
	public Climb(){
		
	}
	
	@EventTarget
	public void onTick(EventTick e){
		if(Client.getClient().getPlayer().isCollidedHorizontally){
			Client.getClient().getPlayer().motionY = 0.25f;
		}
	}

	@Override
	public void onEnable() {
		
	}

	@Override
	public void onDisable() {
		
	}
}
