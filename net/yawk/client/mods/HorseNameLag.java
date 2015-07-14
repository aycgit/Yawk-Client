package net.yawk.client.mods;

import net.yawk.client.Client;
import net.yawk.client.events.EventSendPacket;
import net.yawk.client.events.EventTick;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.ModType;

import com.darkmagician6.eventapi.EventTarget;


public class HorseNameLag implements Mod{
	
	public HorseNameLag(){
		
	}
	
	@Override
	public String getName() {
		return "HorseNameLag";
	}
	
	@Override
	public String getDescription() {
		return "Lag people by naming a horse";
	}
	
	@EventTarget
	public void onTick(EventSendPacket e){
		System.out.println(e.packet.getClass());
	}
	
	@Override
	public ModType getType() {
		return ModType.WORLD;
	}
	
	@Override
	public void onEnable() {
		
	}
	
	@Override
	public void onDisable() {
		
	}
}
