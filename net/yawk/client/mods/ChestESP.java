package net.yawk.client.mods;

import net.minecraft.tileentity.TileEntityChest;
import net.yawk.client.Client;
import net.yawk.client.events.EventRender;
import net.yawk.client.events.EventTick;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.ModType;
import net.yawk.client.utils.ESPUtils;

import com.darkmagician6.eventapi.EventTarget;

public class ChestESP implements Mod{
	
	public ChestESP(){
		
	}
	
	@Override
	public String getName() {
		return "ChestESP";
	}
	
	@Override
	public String getDescription() {
		return "Highlights any chests in the world";
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
