package net.yawk.client.mods.world;

import net.minecraft.network.play.server.S03PacketTimeUpdate;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.yawk.client.Client;
import net.yawk.client.events.EventRecievePacket;
import net.yawk.client.events.EventTick;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.RegisterMod;

import com.darkmagician6.eventapi.EventTarget;

@RegisterMod(name = "Weather", desc = "Hide rain and snow", type = Mod.Type.WORLD)
public class Weather extends Mod{
	
	private boolean raining;
	
	public Weather(){
		
	}
	
	@EventTarget
	public void onTick(EventTick e){
		Client.getClient().getMinecraft().theWorld.getWorldInfo().setRaining(false);
	}
	
	/**
	 * Made using information from http://wiki.vg/Protocol#Change_Game_State
	 * The state called "End raining" is 1
	 * The state called "Begin raining" is 2
	 * @param e the event for recieving a packet
	 */
	@EventTarget
	public void onRecievePacket(EventRecievePacket e){
		
		if(e.packet instanceof S2BPacketChangeGameState){
			
			S2BPacketChangeGameState packet = (S2BPacketChangeGameState) e.packet;
			
			if(packet.func_149138_c() == 2){
				e.setCancelled(true);
				raining = true;
			}else if(packet.func_149138_c() == 1){
				raining = false;
			}
		}
	}

	@Override
	public void onEnable() {
		raining = Client.getClient().getMinecraft().theWorld.getWorldInfo().isRaining();
	}

	@Override
	public void onDisable() {
		Client.getClient().getMinecraft().theWorld.getWorldInfo().setRaining(raining);
	}
}
