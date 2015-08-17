package net.yawk.client.mods.movement;

import net.yawk.client.Client;
import net.yawk.client.events.EventTick;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.RegisterMod;

import com.darkmagician6.eventapi.EventTarget;

@RegisterMod(name = "HorseJump", desc = "Allows your horse to jump constantly", type = Mod.Type.MOVEMENT)
public class HorseJump extends Mod{
	
	public HorseJump(){
		
	}
	
	@EventTarget
	public void onTick(EventTick e){
		Client.getClient().getPlayer().horseJumpPowerCounter = 100;
		Client.getClient().getPlayer().horseJumpPower = 100;
	}

	@Override
	public void onEnable() {
		
	}

	@Override
	public void onDisable() {
		
	}
}
