package net.yawk.client.mods.world;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntityChest;
import net.yawk.client.Client;
import net.yawk.client.events.EventRender;
import net.yawk.client.events.EventTick;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.RegisterMod;
import net.yawk.client.utils.ClientRenderer;

import com.darkmagician6.eventapi.EventTarget;

@RegisterMod(name = "NameTags", desc = "See larger player nametags", type = Mod.Type.WORLD)
public class NameTags extends Mod{
	
	public NameTags(){
		
	}
	
	@EventTarget
	public void onRender(EventRender e){
    	for(Object obj : Client.getClient().getMinecraft().theWorld.playerEntities){
    		if(obj != null && obj != Client.getClient().getPlayer()){
    			ClientRenderer.drawPlayerNametag((EntityPlayer)obj);
    		}
    	}
	}
}
