package net.yawk.client.mods.world;

import net.minecraft.tileentity.TileEntityChest;
import net.yawk.client.Client;
import net.yawk.client.events.EventRender;
import net.yawk.client.events.EventTick;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.ModDetails;
import net.yawk.client.modmanager.ModType;
import net.yawk.client.utils.ESPUtils;

import com.darkmagician6.eventapi.EventTarget;

@ModDetails(name = "ChestESP", defaultKey = 0, desc = "See chests through walls", type = ModType.WORLD)
public class ChestESP extends Mod{
	
	public ChestESP(){
		
	}
	
	@EventTarget
	public void onRender(EventRender e){
    	for(Object o : Client.getClient().getMinecraft().theWorld.loadedTileEntityList) {
    		if(o instanceof TileEntityChest) {
    			TileEntityChest chest = (TileEntityChest)o;
    			int x = chest.getPos().getX();
    			int y = chest.getPos().getY();
    			int z = chest.getPos().getZ();
    			
    			ESPUtils.drawRawBlockESP(x - Client.getClient().getMinecraft().renderManager.renderPosX, y - Client.getClient().getMinecraft().renderManager.renderPosY, z - Client.getClient().getMinecraft().renderManager.renderPosZ, 0, 0, 1, true);
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
