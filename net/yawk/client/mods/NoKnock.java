package net.yawk.client.mods;

import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.network.play.server.S42PacketCombatEvent;
import net.yawk.client.Client;
import net.yawk.client.events.EventRecievePacket;
import net.yawk.client.events.EventSetHealth;
import net.yawk.client.events.EventTick;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.ModType;

import com.darkmagician6.eventapi.EventTarget;

public class NoKnock implements Mod{
	
	public NoKnock(){
		
	}
	
	@Override
	public String getName() {
		return "NoKnock";
	}
	
	@Override
	public String getDescription() {
		return "Don't get knocked back";
	}
	
	@EventTarget
	public void onRecievePacket(EventRecievePacket e){
		if(e.packet instanceof S12PacketEntityVelocity){
			e.setCancelled(true);
		}
	}
		
	@Override
	public ModType getType() {
		return ModType.COMBAT;
	}

	@Override
	public void onEnable() {
		
	}

	@Override
	public void onDisable() {
		
	}
}
