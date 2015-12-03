package net.yawk.client.mods.combat;

import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.network.play.server.S42PacketCombatEvent;
import net.yawk.client.Client;
import net.yawk.client.events.EventRecievePacket;
import net.yawk.client.events.EventSetHealth;
import net.yawk.client.events.EventTick;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.RegisterMod;

import com.darkmagician6.eventapi.EventTarget;

@RegisterMod(name = "NoKnock", desc = "Reduces your knockback", type = Mod.Type.COMBAT)
public class NoKnock extends Mod{
	
	public NoKnock(){
		
	}
		
	@EventTarget
	public void onRecievePacket(EventRecievePacket e){
		if(e.packet instanceof S12PacketEntityVelocity){
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
