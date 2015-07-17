package net.yawk.client.mods.world;

import net.yawk.client.Client;
import net.yawk.client.events.EventTick;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.ModDetails;

import com.darkmagician6.eventapi.EventTarget;

@ModDetails(name = "Weather", defaultKey = 0, desc = "Hide rain and snow", type = Mod.Type.WORLD)
public class Weather extends Mod{
	
	public Weather(){
		
	}
	
	@EventTarget
	public void onTick(EventTick e){
		Client.getClient().getMinecraft().theWorld.setRainStrength(0);
	}
		
	@Override
	public void onEnable() {
		
	}
	
	@Override
	public void onDisable() {
		
	}
}
