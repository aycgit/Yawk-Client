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
import net.yawk.client.modmanager.ModDetails;
import net.yawk.client.utils.ClientUtils;

import com.darkmagician6.eventapi.EventTarget;
@ModDetails(name = "FastPlace", defaultKey = 0, desc = "Place blocks faster", type = Mod.Type.BUILDING)
public class FastPlace extends Mod{
	
	public FastPlace(){
		
	}
		
	@EventTarget
	public void onSendPacket(EventSendPacket e){
		if(e.packet instanceof C08PacketPlayerBlockPlacement){
			Client.getClient().getMinecraft().rightClickDelayTimer = 2;
		}
	}
	
	@Override
	public void onEnable() {
		
	}
	
	@Override
	public void onDisable() {
		
	}
}
