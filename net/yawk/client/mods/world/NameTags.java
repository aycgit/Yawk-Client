package net.yawk.client.mods.world;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntityChest;
import net.yawk.client.Client;
import net.yawk.client.events.EventRender;
import net.yawk.client.events.EventTick;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.RegisterMod;
import net.yawk.client.modmanager.values.AbstractValue;
import net.yawk.client.modmanager.values.ArrayValue;
import net.yawk.client.modmanager.values.BooleanValue;
import net.yawk.client.modmanager.values.SliderValue;
import net.yawk.client.utils.ClientRenderer;

import com.darkmagician6.eventapi.EventTarget;

@RegisterMod(name = "NameTags", desc = "See larger player nametags", type = Mod.Type.WORLD)
public class NameTags extends Mod{
	
	private static BooleanValue health;
	private static SliderValue scale;
	
	public NameTags(){
		super(new AbstractValue[]{
				scale = new SliderValue("Scale", "nametags.scale", Client.getClient().getValuesRegistry(), 3, 0, 10, false),
				health = new BooleanValue("Health", "nametags.health", Client.getClient().getValuesRegistry(), false),
		});
	}
	
	@EventTarget
	public void onRender(EventRender e){
		
		double scaleValue = scale.getValue()/1000;
		
    	for(EntityPlayer pl : (List<EntityPlayer>)Client.getClient().getMinecraft().theWorld.playerEntities){
    		if(pl != null && pl != Client.getClient().getPlayer() && !pl.isDead){
    			ClientRenderer.drawPlayerNametag(pl, scaleValue, health.getValue());
    		}
    	}
	}
}
