package net.yawk.client.mods;

import com.darkmagician6.eventapi.EventTarget;

import net.minecraft.client.Minecraft;
import net.yawk.client.events.EventTick;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.ModType;

public class FastFall implements Mod{
	Minecraft mc = Minecraft.getMinecraft();
	
	@Override
	public String getName() {
		return "FastFall";
	}
	
	@Override
	public String getDescription() {
		return "Fall faster than before!";
	}
	
	@EventTarget
	public void onTick(EventTick e){
		if(!mc.thePlayer.isOnLadder() || !mc.thePlayer.isInWater() || !mc.thePlayer.isCollidedHorizontally)
		{
			float Height = (float) mc.thePlayer.posY;
			mc.thePlayer.motionY =- Height;
		}
	}
	
	@Override
	public ModType getType() {
		return ModType.MOVEMENT;
	}

	@Override
	public void onEnable() {
		
	}

	@Override
	public void onDisable() {
		
	}
}
