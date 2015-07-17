package net.yawk.client.mods.movement;

import com.darkmagician6.eventapi.EventTarget;

import net.minecraft.client.Minecraft;
import net.yawk.client.events.EventTick;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.ModDetails;

@ModDetails(name = "FastFall", desc = "Fall faster than usual", type = Mod.Type.MOVEMENT)
public class FastFall extends Mod{
	Minecraft mc = Minecraft.getMinecraft();
		
	@EventTarget
	public void onTick(EventTick e){
		if(!mc.thePlayer.isOnLadder() || !mc.thePlayer.isInWater() || !mc.thePlayer.isCollidedHorizontally)
		{
			float Height = (float) mc.thePlayer.posY;
			mc.thePlayer.motionY =- Height;
		}
	}
	
	@Override
	public void onEnable() {
		
	}

	@Override
	public void onDisable() {
		
	}
}
