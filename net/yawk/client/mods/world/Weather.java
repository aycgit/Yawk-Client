package net.yawk.client.mods.world;

import net.yawk.client.Client;
import net.yawk.client.events.EventTick;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.ModType;

import com.darkmagician6.eventapi.EventTarget;

public class Weather implements Mod{
	
	public Weather(){
		
	}
	
	@Override
	public String getName() {
		return "Weather";
	}
	
	@Override
	public String getDescription() {
		return "Blocks rain";
	}
	
	@EventTarget
	public void onTick(EventTick e){
		Client.getClient().getMinecraft().theWorld.setRainStrength(0);
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
