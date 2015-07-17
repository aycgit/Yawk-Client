package net.yawk.client.mods.building;

import net.minecraft.network.play.client.C09PacketHeldItemChange;
import net.minecraft.network.play.client.C0APacketAnimation;
import net.minecraft.network.play.client.C0BPacketEntityAction;
import net.minecraft.network.play.client.C0DPacketCloseWindow;
import net.minecraft.network.play.client.C12PacketUpdateSign;
import net.minecraft.network.play.client.C16PacketClientStatus;
import net.yawk.client.Client;
import net.yawk.client.events.EventSendPacket;
import net.yawk.client.events.EventTick;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.ModDetails;
import net.yawk.client.utils.ClientUtils;

import com.darkmagician6.eventapi.EventTarget;

@ModDetails(name = "NoSwing", desc = "Don't swing your arm", type = Mod.Type.BUILDING)
public class NoSwing extends Mod{
	
	public NoSwing(){
		
	}
	
	@EventTarget
	public void onPacketSend(EventSendPacket e){
		if(e.packet instanceof C0APacketAnimation){
			e.setCancelled(true);
		}
	}
	
	@Override
	public void onEnable() {
		
	}
	
	@Override
	public void onDisable() {
		
	}
}
