package net.yawk.client.mods.world;

import net.yawk.client.Client;
import net.yawk.client.events.EventTick;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.RegisterMod;
import net.yawk.client.modmanager.values.SliderValue;
import net.yawk.client.modmanager.values.Value;

import org.lwjgl.input.Keyboard;

import com.darkmagician6.eventapi.EventTarget;

@RegisterMod(name = "Zoom", desc = "Magnify your view", type = Mod.Type.WORLD)
public class Zoom extends Mod{
	
	private float prevFOV;
	private static SliderValue fov;
	
	public Zoom(){
		
		super(new Value[]{
				fov = new SliderValue("FOV", "zoom.fov", Client.getClient().getValuesRegistry(), 20, 10, 50, true),
		});
	}
	
	@Override
	public void onEnable() {
		prevFOV = Client.getClient().getMinecraft().gameSettings.fovSetting;
	}
	
	@EventTarget
	public void onTick(EventTick e) {
		Client.getClient().getMinecraft().gameSettings.fovSetting = fov.getValue().floatValue();
	}
	
	@Override
	public void onDisable() {
		Client.getClient().getMinecraft().gameSettings.fovSetting = prevFOV;
	}
}
