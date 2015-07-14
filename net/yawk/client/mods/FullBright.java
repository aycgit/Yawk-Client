package net.yawk.client.mods;

import net.yawk.client.Client;
import net.yawk.client.events.EventTick;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.ModType;

import com.darkmagician6.eventapi.EventTarget;

public class FullBright implements Mod{
	
	private float prevGamma;
	
	public FullBright(){
		
	}
	
	@Override
	public String getName() {
		return "FullBright";
	}
	
	@Override
	public String getDescription() {
		return "Makes the world bright";
	}
	
	@Override
	public ModType getType() {
		return ModType.WORLD;
	}
	
	@Override
	public void onEnable() {
		prevGamma = Client.getClient().getMinecraft().gameSettings.gammaSetting;
		Client.getClient().getMinecraft().gameSettings.gammaSetting = 10;
	}
	
	@Override
	public void onDisable() {
		Client.getClient().getMinecraft().gameSettings.gammaSetting = prevGamma;
	}
}
