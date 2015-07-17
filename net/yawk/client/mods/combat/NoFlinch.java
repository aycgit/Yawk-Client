package net.yawk.client.mods.combat;

import net.yawk.client.Client;
import net.yawk.client.events.EventTick;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.ModDetails;

import com.darkmagician6.eventapi.EventTarget;

@ModDetails(name = "NoFlinch", defaultKey = 0, desc = "Don't flinch when hit", type = Mod.Type.COMBAT)
public class NoFlinch extends Mod{
	
	public NoFlinch(){
		
	}
	
	@EventTarget
	public void onTick(EventTick e){
		Client.getClient().getPlayer().hurtTime = 0;
		Client.getClient().getPlayer().hurtResistantTime = 0;
	}
	
	@Override
	public void onEnable() {
		
	}

	@Override
	public void onDisable() {
		
	}
}
