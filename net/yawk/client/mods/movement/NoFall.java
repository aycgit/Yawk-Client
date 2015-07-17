package net.yawk.client.mods.movement;

import net.minecraft.network.play.client.C03PacketPlayer;
import net.yawk.client.Client;
import net.yawk.client.events.EventSendPacket;
import net.yawk.client.events.EventTick;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.ModDetails;
import net.yawk.client.modmanager.ModType;

import com.darkmagician6.eventapi.EventTarget;

@ModDetails(name = "NoFall", defaultKey = 0, desc = "Don't take fall damage", type = ModType.MOVEMENT)
public class NoFall extends Mod{
	
	public NoFall(){

	}

	@EventTarget
	public void onSendPacket(EventSendPacket e){
		if(e.packet instanceof C03PacketPlayer){
			((C03PacketPlayer)e.packet).field_149474_g = true;
		}
	}

	@Override
	public void onEnable() {

	}

	@Override
	public void onDisable() {

	}
}
