package net.yawk.client.mods.combat;

import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.yawk.client.Client;
import net.yawk.client.events.EventTick;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.RegisterMod;
import net.yawk.client.utils.ClientUtils;

import com.darkmagician6.eventapi.EventTarget;

@RegisterMod(name = "AutoBlock", desc = "Block with your sword automatically", type = Mod.Type.COMBAT)
public class AutoBlock extends Mod{
	
	public AutoBlock(){
		//TODO: Fix this hack
	}
		
	@EventTarget
	public void onTick(EventTick e){
		ClientUtils.sendPacket(new C08PacketPlayerBlockPlacement(Client.getClient().getPlayer().getHeldItem()));
	}

	@Override
	public void onEnable() {
		
	}

	@Override
	public void onDisable() {
		
	}
}
