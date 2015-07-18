package net.yawk.client.mods.world;

import net.minecraft.entity.player.EntityPlayer;
import net.yawk.client.Client;
import net.yawk.client.events.EventGuiRender;
import net.yawk.client.events.EventRender;
import net.yawk.client.events.EventRenderEntity;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.RegisterMod;
import net.yawk.client.utils.ClientRenderer;

import com.darkmagician6.eventapi.EventTarget;

@RegisterMod(name = "PlayerESP", desc = "See players through walls", type = Mod.Type.WORLD)
public class PlayerESP extends Mod{
	
	public PlayerESP(){
		
	}
	
    @EventTarget
	public void onEvent(EventRender event)
    {
    	for(Object obj : Client.getClient().getMinecraft().theWorld.playerEntities){
    		if(obj != null && obj != Client.getClient().getPlayer()){
    			ClientRenderer.drawPlayerESP((EntityPlayer)obj);
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
