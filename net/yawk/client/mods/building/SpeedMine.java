package net.yawk.client.mods.building;

import net.yawk.client.Client;
import net.yawk.client.events.*;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.ModType;

import com.darkmagician6.eventapi.EventTarget;

public class SpeedMine implements Mod{
	
	@Override
	public String getName() {
		return "SpeedMine";
	}
	
	@Override
	public String getDescription() {
		return "Break blocks faster";
	}
	
	@EventTarget
	public void onTick(EventTick e){
		Client.getClient().getMinecraft().playerController.blockHitDelay = 0;
	}
	
	@Override
	public ModType getType() {
		return ModType.BUILDING;
	}

	@Override
	public void onEnable() {
		
	}

	@Override
	public void onDisable() {
		Client.getClient().getMinecraft().playerController.blockHitDelay = 5;
	}
}
