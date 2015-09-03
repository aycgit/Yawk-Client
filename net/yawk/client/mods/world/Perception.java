package net.yawk.client.mods.world;

import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.entity.Entity;
import net.yawk.client.Client;
import net.yawk.client.events.EventTick;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.RegisterMod;

import com.darkmagician6.eventapi.EventTarget;

@RegisterMod(name = "Perception", desc = "See when people spawn near you", type = Mod.Type.WORLD)
public class Perception extends Mod{
	
	public Perception(){
		
	}
	
	private String lastPlayer;
	
	//Called by RenderGlobalHook
	public void onEntityAdded(Entity e){
		
		if(isEnabled() && e instanceof EntityOtherPlayerMP && Client.getClient().getPlayer().ticksExisted > 5 && lastPlayer != e.getName()){
			Client.getClient().addChat("Player spawned nearby: "+e.getName());
			lastPlayer = e.getName();
		}
	}
}
