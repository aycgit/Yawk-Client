package net.yawk.client.mods.movement;

import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.yawk.client.Client;
import net.yawk.client.events.EventSendPacket;
import net.yawk.client.events.EventTick;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.RegisterMod;

import com.darkmagician6.eventapi.EventTarget;

@RegisterMod(name = "NoPacketMove", desc = "Don't send packets when not moving", type = Mod.Type.MOVEMENT)
public class NoPacketMove extends Mod{
	
	private Minecraft mc;
	
	public NoPacketMove(){
		mc = Minecraft.getMinecraft();
	}
	
	@EventTarget
	public void onSend(EventSendPacket e){
		if(e.packet instanceof C03PacketPlayer){
			e.setCancelled(!isMoving());
		}
	}
	
	private boolean isMoving(){
		return mc.thePlayer.motionX != 0 
				|| mc.thePlayer.motionY != 0 
				|| mc.thePlayer.motionZ != 0;
	}

}
