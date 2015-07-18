package net.yawk.client.mods.world;

import net.yawk.client.Client;
import net.yawk.client.events.EventTick;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.RegisterMod;

import org.lwjgl.input.Keyboard;

import com.darkmagician6.eventapi.EventTarget;

@RegisterMod(name = "Zoom", desc = "Magnify your view", type = Mod.Type.WORLD)
public class Zoom extends Mod{
	
	private float prevFOV;
	
	public Zoom(){
		
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
