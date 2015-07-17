package net.yawk.client.mods.movement;

import net.minecraft.network.play.client.C03PacketPlayer;
import net.yawk.client.Client;
import net.yawk.client.events.EventMoveEntity;
import net.yawk.client.events.EventTick;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.ModDetails;
import net.yawk.client.utils.ClientUtils;

import com.darkmagician6.eventapi.EventTarget;
import com.darkmagician6.eventapi.types.EventType;

@ModDetails(name = "Sprint", desc = "Sprint automatically", type = Mod.Type.MOVEMENT)
public class Sprint extends Mod{
	
	@EventTarget
	public void onTick(EventTick e){
		Client.getClient().getPlayer().setSprinting(Client.getClient().getPlayer().moveForward > 0);
	}
		
	public void onEnable(){
		
	}
	
	public void onDisable(){
		
	}
}
