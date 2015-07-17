package net.yawk.client.mods.movement;

import java.util.Random;

import net.yawk.client.Client;
import net.yawk.client.events.EventJump;
import net.yawk.client.events.EventMotionUpdate;
import net.yawk.client.events.EventTick;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.ModDetails;

import com.darkmagician6.eventapi.EventTarget;
import com.darkmagician6.eventapi.types.EventType;

@ModDetails(name = "Glide", defaultKey = 0, desc = "Glide when falling", type = Mod.Type.MOVEMENT)
public class Glide extends Mod{
	
	private int updates;
	
	public Glide(){
		
	}
		
	@EventTarget
	public void onTick(EventTick e){
		if(!Client.getClient().getPlayer().onGround){
						
			float var11 = 0.15F;
			
			if (Client.getClient().getPlayer().motionX < (double)(-var11))
			{
				Client.getClient().getPlayer().motionX = (double)(-var11);
			}
			
			if (Client.getClient().getPlayer().motionX > (double)var11)
			{
				Client.getClient().getPlayer().motionX = (double)var11;
			}
			
			if (Client.getClient().getPlayer().motionZ < (double)(-var11))
			{
				Client.getClient().getPlayer().motionZ = (double)(-var11);
			}
			
			if (Client.getClient().getPlayer().motionZ > (double)var11)
			{
				Client.getClient().getPlayer().motionZ = (double)var11;
			}
			
			Client.getClient().getPlayer().fallDistance = 0.0F;
			
			if (Client.getClient().getPlayer().motionY < -0.25D)
			{
				Client.getClient().getPlayer().motionY = -0.25D;
			}
			
			boolean var7 = Client.getClient().getPlayer().isSneaking();
			
			if (var7 && Client.getClient().getPlayer().motionY < 0.0D)
			{
				Client.getClient().getPlayer().motionY = 0.0D;
			}		
		}
	}
	
	@Override
	public void onEnable() {
		
	}

	@Override
	public void onDisable() {
		
	}
}
