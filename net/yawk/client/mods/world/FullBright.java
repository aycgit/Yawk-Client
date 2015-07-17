package net.yawk.client.mods.world;

import net.yawk.client.Client;
import net.yawk.client.events.EventTick;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.ModDetails;

import com.darkmagician6.eventapi.EventTarget;

@ModDetails(name = "FullBright", desc = "Make the world brighter", type = Mod.Type.WORLD)
public class FullBright extends Mod{
	
	private float prevGamma;
	
	public FullBright(){
		
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
