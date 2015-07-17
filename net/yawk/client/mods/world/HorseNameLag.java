package net.yawk.client.mods.world;

import net.yawk.client.Client;
import net.yawk.client.events.EventSendPacket;
import net.yawk.client.events.EventTick;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.ModDetails;
import net.yawk.client.modmanager.ModType;

import com.darkmagician6.eventapi.EventTarget;

@ModDetails(name = "HorseNameLag", defaultKey = 0, desc = "Lag people with horse nametags", type = ModType.WORLD)
public class HorseNameLag extends Mod{
	
	public HorseNameLag(){
		//TODO: Fix this mod and move it to the exploit pack plugin
	}
	
	@EventTarget
	public void onTick(EventSendPacket e){
		System.out.println(e.packet.getClass());
	}
		
	@Override
	public void onEnable() {
		
	}
	
	@Override
	public void onDisable() {
		
	}
}
