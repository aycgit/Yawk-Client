package net.yawk.client.mods.combat;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBow;
import net.minecraft.util.MathHelper;
import net.yawk.client.Client;
import net.yawk.client.events.EventGuiRender;
import net.yawk.client.events.EventTick;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.RegisterMod;

import com.darkmagician6.eventapi.EventTarget;

@RegisterMod(name = "Looksie", desc = "See if people are looking at you", type = Mod.Type.COMBAT)
public class Looksie extends Mod{
	
	public Looksie(){
		
	}
	
	@EventTarget
	public void onGuiRender(EventGuiRender e){
		
    	int lowestDist = 999;
    	EntityPlayer lowestEntity = null;
    	
    	for(Object obj : Client.getClient().getMinecraft().theWorld.playerEntities){
    		if(obj instanceof EntityPlayer && obj != null && obj != Client.getClient().getPlayer()){
    			EntityPlayer player = (EntityPlayer) obj;
    			
    			if(Client.getClient().getPlayer().canEntityBeSeen(player) && Client.getClient().getPlayer().getDistanceToEntity(player) < 10 && player.getHealth() > 0){
    				int angleDist = getAngleDistanceToPlayer(player);
    				
    				if(angleDist < lowestDist){
    					lowestDist = angleDist;
    					lowestEntity = player;
    				}
    			}
    		}
    	}
    	
    	if(lowestEntity != null && lowestDist < 30){
    		System.out.println(lowestDist);
    		String text = lowestEntity.getName()+" IS LOOKING AT YOU "+getPercent(lowestDist)+"%";
    		Client.getClient().getFontRenderer().drawString(text, Client.getClient().getMinecraft().displayWidth/4 - Client.getClient().getFontRenderer().getStringWidth(text)/2, 12, lowestDist < 20? 0xFF0000FF:0xFF00FF00);
    		return;
    	}
	}
	
    private int getAngleDistanceToPlayer(Entity e){
    	
		double x = Client.getClient().getPlayer().posX - e.posX;
		double y = Client.getClient().getPlayer().posY - e.posY;
		double z = Client.getClient().getPlayer().posZ - e.posZ;
		double d1 = (e.posY + (double)e.getEyeHeight())-(Client.getClient().getPlayer().posY+(double)Client.getClient().getPlayer().getEyeHeight());
		double d3 = MathHelper.sqrt_double(x*x+z*z);
		float f = (float)((Math.atan2(z, x)*180D)/Math.PI)-90F;
		float f1 = (float)(-((Math.atan2(d1, d3)*180D)/Math.PI));
		
		return getAngleDistance(f, e.rotationYaw) + getAngleDistance(f1, e.rotationPitch);
    }
	
    private int getPercent(int angle){
    	return (360 / angle) * 4;
    }
	
    private int getAngleDistance(double a, double b){
    	double d = Math.abs(a - b) % 360;
    	return (int) (d > 180 ? 360 - d : d);
    }
    
	@Override
	public void onEnable() {
		
	}
	
	@Override
	public void onDisable() {
		
	}
}
