package net.yawk.client.mods.world;

import net.yawk.client.Client;
import net.yawk.client.events.EventSendPacket;
import net.yawk.client.events.EventTick;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.RegisterMod;

import com.darkmagician6.eventapi.EventTarget;

@RegisterMod(name = "HorseNameLag", desc = "Lag people with horse nametags", type = Mod.Type.WORLD)
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
