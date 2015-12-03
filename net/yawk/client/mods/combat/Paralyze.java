package net.yawk.client.mods.combat;

import net.minecraft.network.play.client.C03PacketPlayer;
import net.yawk.client.Client;
import net.yawk.client.events.EventGuiRender;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.RegisterMod;
import net.yawk.client.utils.ClientUtils;

import com.darkmagician6.eventapi.EventTarget;

@RegisterMod(name = "Paralyze", desc = "Freeze players inside you", type = Mod.Type.COMBAT)
public class Paralyze extends Mod{
	
	@EventTarget
	public void onTick(EventGuiRender e){
    	if(Client.getClient().getPlayer().getHealth() < 20)
    		return; if(!Client.getClient().getMinecraft().theWorld.getEntitiesWithinAABBExcludingEntity(Client.getClient().getPlayer(), Client.getClient().getPlayer().getBoundingBox()).isEmpty())
    		{
    			for(int i = 0; i < 500; i++)
    				ClientUtils.sendPacket(new C03PacketPlayer(Client.getClient().getPlayer().onGround));
    		}    
	}
	
	@Override
	public void onEnable() {
		
	}

	@Override
	public void onDisable() {
		
	}
}
