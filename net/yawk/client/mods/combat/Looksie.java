package net.yawk.client.mods.combat;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBow;
import net.minecraft.util.MathHelper;
import net.yawk.client.Client;
import net.yawk.client.events.EventGuiRender;
import net.yawk.client.events.EventTick;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.ModDetails;

import com.darkmagician6.eventapi.EventTarget;

@ModDetails(name = "Looksie", defaultKey = 0, desc = "See if people are looking at you", type = Mod.Type.COMBAT)
public class Looksie extends Mod{
	
	public Looksie(){
		//TODO: Fix this mod, it's broken
	}
	
	@EventTarget
	public void onGuiRender(EventGuiRender e){
		
    	int lowestDist = 999;
    	EntityPlayer lowestEntity = null;
    	
    	for(Object obj : Client.getClient().getMinecraft().theWorld.playerEntities){
    		if(obj instanceof EntityPlayer && obj != null && obj != Client.getClient().getPlayer()){
    			EntityPlayer player = (EntityPlayer) obj;
    			
    			if(Client.getClient().getPlayer().canEntityBeSeen(player) && player.getHealth() > 0){
    				int angleDist = getAngleDistanceToPlayer(player);
    				
    				if(angleDist < lowestDist){
    					lowestDist = angleDist;
    					lowestEntity = player;
    				}
    			}
    		}
    	}
    	
    	if(lowestEntity != null){
    		
    		double x = Client.getClient().getPlayer().posX - lowestEntity.posX;
    		double y = Client.getClient().getPlayer().posY - lowestEntity.posY;
    		double z = Client.getClient().getPlayer().posZ - lowestEntity.posZ;
    		double d1 = (lowestEntity.posY + (double)lowestEntity.getEyeHeight());
    		double d3 = MathHelper.sqrt_double(x*x+z*z);
    		float f = (float)((Math.atan2(z, x)*180D)/Math.PI);
    		float f1 = (float)(-((Math.atan2(d1, d3)*180D)/Math.PI));
    		
    		if(lowestEntity.getHeldItem() != null && lowestEntity.getHeldItem().getItem() instanceof ItemBow){
    			String text = lowestEntity.getName()+" IS AIMING AT YOU "+getPercent(lowestDist, 20)+"%";
    			Client.getClient().getFontRenderer().drawString(text, Client.getClient().getMinecraft().displayWidth/4 - Client.getClient().getFontRenderer().getStringWidth(text)/2, 12, 0xFFFF0000);
    			return;
    		}else{
    			String text = lowestEntity.getName()+" IS STARING AT YOU "+getPercent(lowestDist, 20)+"%";
    			Client.getClient().getFontRenderer().drawString(text, Client.getClient().getMinecraft().displayWidth/4 - Client.getClient().getFontRenderer().getStringWidth(text)/2, 12, 0xFFFF0000);
    			return;
    		}
    	}
	}
	
    private int getAngleDistanceToPlayer(Entity e){
		double x = Client.getClient().getPlayer().posX - e.posX;
		double y = Client.getClient().getPlayer().posY - e.posY;
		double z = Client.getClient().getPlayer().posZ - e.posZ;
		double d1 = (e.posY + (double)e.getEyeHeight());
		double d3 = MathHelper.sqrt_double(x*x+z*z);
		float f = (float)((Math.atan2(z, x)*180D)/Math.PI);
		float f1= (float)(-((Math.atan2(d1, d3)*180D)/Math.PI));
		
		double dist = (double)((f-90) - f1);
		dist = dist > 0? dist:-dist;
		return (int) (dist);
    }
	
    private int getPercent(int angle, int outof){
    	
    	angle = Math.abs(angle % 360);
    	outof = Math.abs(outof % 360);
    	
    	int difference = Math.abs(outof-angle);
    	return difference / outof;
    }
	
	@Override
	public void onEnable() {
		
	}
	
	@Override
	public void onDisable() {
		
	}
}
