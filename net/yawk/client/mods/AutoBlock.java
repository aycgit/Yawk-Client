package net.yawk.client.mods;

import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.yawk.client.Client;
import net.yawk.client.events.EventTick;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.ModType;
import net.yawk.client.utils.ClientUtils;

import com.darkmagician6.eventapi.EventTarget;


public class AutoBlock implements Mod{
	
	public AutoBlock(){
		
	}
	
	@Override
	public String getName() {
		return "AutoBlock";
	}
	
	@Override
	public String getDescription() {
		return "Block automatically";
	}
	
	@EventTarget
	public void onTick(EventTick e){
		ClientUtils.sendPacket(new C08PacketPlayerBlockPlacement(Client.getClient().getPlayer().getHeldItem()));
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
