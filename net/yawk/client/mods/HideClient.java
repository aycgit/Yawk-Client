package net.yawk.client.mods;

import net.yawk.client.Client;
import net.yawk.client.events.EventTick;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.ModType;

import com.darkmagician6.eventapi.EventTarget;

public class HideClient implements Mod{
	
	public HideClient(){
		
	}
	
	@Override
	public String getName() {
		return "HideClient";
	}
	
	@Override
	public String getDescription() {
		return "Hides the client name";
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
