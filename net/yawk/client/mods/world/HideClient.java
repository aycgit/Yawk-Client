package net.yawk.client.mods.world;

import net.yawk.client.Client;
import net.yawk.client.events.EventTick;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.RegisterMod;

import com.darkmagician6.eventapi.EventTarget;

@RegisterMod(name = "HideClient", desc = "Hide the client's watermark", type = Mod.Type.WORLD)
public class HideClient extends Mod{
	
	public HideClient(){
		//TODO: Make a meta/utility window for hacks like this
	}
		
	@Override
	public void onEnable() {
		
	}
	
	@Override
	public void onDisable() {
		
	}
}
