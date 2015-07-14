package net.yawk.client.mods;

import net.yawk.client.Client;
import net.yawk.client.events.EventTick;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.ModType;

import com.darkmagician6.eventapi.EventTarget;

public class NoFlinch implements Mod{
	
	public NoFlinch(){
		
	}
	
	@Override
	public String getName() {
		return "NoFlinch";
	}
	
	@Override
	public String getDescription() {
		return "Don't flinch when damaged";
	}
	
	@EventTarget
	public void onTick(EventTick e){
		Client.getClient().getPlayer().hurtTime = 0;
		Client.getClient().getPlayer().hurtResistantTime = 0;
	}
	
	@Override
	public ModType getType() {
		return ModType.COMBAT;
	}

	@Override
	public void onEnable() {
		
	}

	@Override
	public void onDisable() {
		
	}
}
