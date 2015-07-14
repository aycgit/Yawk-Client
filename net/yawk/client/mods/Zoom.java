package net.yawk.client.mods;

import net.yawk.client.Client;
import net.yawk.client.events.EventTick;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.ModData;
import net.yawk.client.modmanager.ModType;

import org.lwjgl.input.Keyboard;

import com.darkmagician6.eventapi.EventTarget;

public class Zoom implements Mod{
	
	private float prevFOV;
	
	public Zoom(){
		
	}
	
	@Override
	public String getName() {
		return "Zoom";
	}
	
	@Override
	public String getDescription() {
		return "Magnifies the world";
	}
	
	@Override
	public ModType getType() {
		return ModType.WORLD;
	}
	
	@Override
	public void onEnable() {
		prevFOV = Client.getClient().getMinecraft().gameSettings.fovSetting;
		Client.getClient().getMinecraft().gameSettings.fovSetting = 15;
	}
	
	@Override
	public void onDisable() {
		Client.getClient().getMinecraft().gameSettings.fovSetting = prevFOV;
	}
}
