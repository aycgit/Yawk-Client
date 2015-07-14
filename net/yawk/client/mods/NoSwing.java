package net.yawk.client.mods;

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
import net.yawk.client.modmanager.ModType;
import net.yawk.client.utils.ClientUtils;

import com.darkmagician6.eventapi.EventTarget;

public class NoSwing implements Mod{
	
	public NoSwing(){
		
	}
	
	@Override
	public String getName() {
		return "NoSwing";
	}
	
	@Override
	public String getDescription() {
		return "Don't swing your arm";
	}
	
	@EventTarget
	public void onPacketSend(EventSendPacket e){
		if(e.packet instanceof C0APacketAnimation){
			e.setCancelled(true);
		}
	}
	
	@Override
	public ModType getType() {
		return ModType.BUILDING;
	}
	
	@Override
	public void onEnable() {
		
	}
	
	@Override
	public void onDisable() {
		
	}
}
