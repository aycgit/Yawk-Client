package net.yawk.client.mods.world;

import net.minecraft.entity.player.EntityPlayer;
import net.yawk.client.Client;
import net.yawk.client.events.EventGuiRender;
import net.yawk.client.events.EventRender;
import net.yawk.client.events.EventRenderEntity;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.ModDetails;
import net.yawk.client.utils.ESPUtils;

import com.darkmagician6.eventapi.EventTarget;

@ModDetails(name = "PlayerESP", defaultKey = 0, desc = "See players through walls", type = Mod.Type.WORLD)
public class PlayerESP extends Mod{
	
	public PlayerESP(){
		
	}
	
    @EventTarget
	public void onEvent(EventRender event)
    {
    	for(Object obj : Client.getClient().getMinecraft().theWorld.playerEntities){
    		if(obj != null && obj != Client.getClient().getPlayer()){
    			EntityPlayer ep = (EntityPlayer)obj;
    			
    			double[] d = ESPUtils.getRenderCoords(ep);
    			
    			ESPUtils.drawPlayerESP(d[0], d[1], d[2], ep, ep.height - 0.1, ep.width - 0.12);
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
