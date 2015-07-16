package net.yawk.client.mods.building;

import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C09PacketHeldItemChange;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.yawk.client.Client;
import net.yawk.client.events.EventSendPacket;
import net.yawk.client.events.EventTick;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.ModType;
import net.yawk.client.utils.ClientUtils;

import com.darkmagician6.eventapi.EventTarget;

public class FastPlace implements Mod{
	
	public FastPlace(){
		
	}
	
	@Override
	public String getName() {
		return "FastPlace";
	}
	
	@Override
	public String getDescription() {
		return "Place blocks faster";
	}
	
	@EventTarget
	public void onSendPacket(EventSendPacket e){
		if(e.packet instanceof C08PacketPlayerBlockPlacement){
			Client.getClient().getMinecraft().rightClickDelayTimer = 2;
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
