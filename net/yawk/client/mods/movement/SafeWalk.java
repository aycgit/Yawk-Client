package net.yawk.client.mods.movement;

import net.yawk.client.Client;
import net.yawk.client.events.EventMoveEntity;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.RegisterMod;

import com.darkmagician6.eventapi.EventTarget;
import com.darkmagician6.eventapi.types.EventType;

@RegisterMod(name = "SafeWalk", desc = "Stops you falling off edges", type = Mod.Type.MOVEMENT)
public class SafeWalk extends Mod{
	
	private boolean wasSneaking;
	
	public SafeWalk(){
		
	}
	
	@EventTarget
	public void onMove(EventMoveEntity e){
		if(Client.getClient().getPlayer().onGround){
			if(e.type == EventType.PRE){
				wasSneaking = Client.getClient().getPlayer().movementInput.sneak;
				Client.getClient().getPlayer().movementInput.sneak = true;
			}else if(e.type == EventType.POST){
				Client.getClient().getPlayer().movementInput.sneak = wasSneaking;
			}
		}
	}
	
	@Override
	public void onEnable() {
		
	}
	
	@Override
	public void onDisable() {
		
	}
}
