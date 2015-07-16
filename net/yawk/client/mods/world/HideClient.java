package net.yawk.client.mods.world;

import net.yawk.client.Client;
import net.yawk.client.events.EventTick;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.ModType;

import com.darkmagician6.eventapi.EventTarget;

public class HideClient implements Mod{
	
	public HideClient(){
		//TODO: Make a meta/utility window for hacks like this
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
