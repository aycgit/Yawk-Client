package net.yawk.client.mods.building;

import net.yawk.client.Client;
import net.yawk.client.events.*;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.RegisterMod;

import com.darkmagician6.eventapi.EventTarget;

@RegisterMod(name = "Speedmine", desc = "Break blocks faster", type = Mod.Type.BUILDING)
public class SpeedMine extends Mod{
	
	@EventTarget
	public void onTick(EventTick e){
		Client.getClient().getMinecraft().playerController.blockHitDelay = 0;
	}
	
	@Override
	public void onEnable() {
		
	}

	@Override
	public void onDisable() {
		Client.getClient().getMinecraft().playerController.blockHitDelay = 5;
	}
}
