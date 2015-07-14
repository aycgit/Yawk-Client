package net.yawk.client.mods;

import net.minecraft.entity.player.EntityPlayer;
import net.yawk.client.Client;
import net.yawk.client.events.EventGuiRender;
import net.yawk.client.events.EventRender;
import net.yawk.client.events.EventRenderEntity;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.ModType;
import net.yawk.client.utils.ESPUtils;

import com.darkmagician6.eventapi.EventTarget;

public class PlayerESP implements Mod{
	
	public PlayerESP(){
		
	}
	
	@Override
	public String getName() {
		return "PlayerESP";
	}
	
	@Override
	public String getDescription() {
		return "Draws boxes round players";
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
	public ModType getType() {
		return ModType.WORLD;
	}

	@Override
	public void onEnable() {
		
	}

	@Override
	public void onDisable() {
		
	}
}
