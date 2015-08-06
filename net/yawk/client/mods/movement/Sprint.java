package net.yawk.client.mods.movement;

import net.minecraft.network.play.client.C03PacketPlayer;
import net.yawk.client.Client;
import net.yawk.client.events.EventMoveEntity;
import net.yawk.client.events.EventTick;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.RegisterMod;
import net.yawk.client.utils.ClientUtils;

import com.darkmagician6.eventapi.EventTarget;
import com.darkmagician6.eventapi.types.EventType;

@RegisterMod(name = "Sprint", desc = "Sprint automatically", type = Mod.Type.MOVEMENT)
public class Sprint extends Mod{
	
	@EventTarget
	public void onTick(EventTick e){
		Client.getClient().getPlayer().setSprinting(Client.getClient().getPlayer().moveForward > 0
				&& Client.getClient().getPlayer().getFoodStats().getFoodLevel() > 4);
	}
	
	public void onEnable(){
		
	}
	
	public void onDisable(){
		
	}
}
