package net.yawk.client.mods;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.MathHelper;
import net.yawk.client.Client;
import net.yawk.client.events.EventGuiRender;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.ModType;
import net.yawk.client.utils.ClientUtils;
import net.yawk.client.utils.HysteriaTimer;

import com.darkmagician6.eventapi.EventTarget;

public class KillAura implements Mod{
	
	Minecraft mc = Minecraft.getMinecraft();
	Minecraft wrapper = Minecraft.getMinecraft();
	
	private HysteriaTimer timer = new HysteriaTimer().setDelay(1 /*12*/);
	private HysteriaTimer rotTimer = new HysteriaTimer().setDelay(1 /*4*/);
	
	@Override
	public String getName() {
		return "KillAura";
	}
	
	@Override
	public String getDescription() {
		return "Kill bitches";
	}
	
	@EventTarget
	public void onTick(EventGuiRender e){
		
			EntityPlayer entityplayer = getClosestPlayer(3.95f);
			
			if (entityplayer != null && timer.output()) {
				
				if(/*faceEntity(entityplayer)*/true){
					
					Minecraft.getMinecraft().playerController.attackEntity(Minecraft.getMinecraft().thePlayer, entityplayer);
					Minecraft.getMinecraft().thePlayer.swingItem();
					faceEntityFixed(entityplayer);
				}
			}
			
			if (entityplayer != null && rotTimer.output()) {
				//faceEntity(entityplayer);
			}
	}
	
	private void faceEntityFixed(Entity e){
		double var4 = e.posX - mc.thePlayer.posX;
		double var6 = e.posZ - mc.thePlayer.posZ;
		double var8 = e.posY + e.getEyeHeight() / 1.3D - (mc.thePlayer.posY + mc.thePlayer.getEyeHeight());
		double var14 = MathHelper.sqrt_double(var4 * var4 + var6 * var6);
		float var12 = (float)(Math.atan2(var6, var4) * 180.0D / 3.141592653589793D) - 90.0F;
		mc.thePlayer.rotationPitch = ((float)-(Math.atan2(var8, var14) * 180.0D / 3.141592653589793D));
		mc.thePlayer.rotationYaw = var12;
	}
	
	private boolean faceEntity(Entity e)
	{
		double x = e.posX - Client.getClient().getMinecraft().thePlayer.posX;
		double y = e.posY - Client.getClient().getMinecraft().thePlayer.posY;
		double z = e.posZ - Client.getClient().getMinecraft().thePlayer.posZ;
		double d1 = (Client.getClient().getMinecraft().thePlayer.posY + (double)Client.getClient().getMinecraft().thePlayer.getEyeHeight())-(e.posY+(double)e.getEyeHeight());
		double d3 = MathHelper.sqrt_double(x*x+z*z);
		float f = (float)((Math.atan2(z, x)*180D)/Math.PI)-90F;
		float f1 = (float) ((Math.atan2(d1, d3)*180D)/Math.PI);
		
		f = MathHelper.wrapAngleTo180_float(f);
		f1 = MathHelper.wrapAngleTo180_float(f1);
		
		f = f-MathHelper.wrapAngleTo180_float(Client.getClient().getPlayer().rotationYaw);
		
		if(f > 0){
			f = MathHelper.clamp_float(f, 0, 10);
		}else if(f < 0){
			f = MathHelper.clamp_float(f, -10, 0);
		}
		
		f1 = f1-MathHelper.wrapAngleTo180_float(Client.getClient().getPlayer().rotationPitch);
		
		if(f1 > 0){
			f1 = MathHelper.clamp_float(f1, 0, 10);
		}else if(f1 < 0){
			f1 = MathHelper.clamp_float(f1, -10, 0);
		}
		
		Client.getClient().getPlayer().setPositionAndRotation(Client.getClient().getMinecraft().thePlayer.posX, Client.getClient().getMinecraft().thePlayer.posY, Client.getClient().getMinecraft().thePlayer.posZ, Client.getClient().getPlayer().rotationYaw+f, Client.getClient().getPlayer().rotationPitch+f1);
		
		return f < 10 && f1 < 10 && f > -10 && f1 > -10;
		
		//ClientUtils.sendPacket(new C03PacketPlayer.C05PacketPlayerLook(f, f1, Client.getClient().getPlayer().onGround));
	}
	
	private EntityPlayer getClosestPlayer(double range) {
        double distance = range;
        EntityPlayer tempEntity = null;
        
        for (EntityPlayer entity : (List<EntityPlayer>) Minecraft.getMinecraft().theWorld.playerEntities) {
        	if(entity != Client.getClient().getPlayer() && isAttackable(entity)){
        		
        		double curDistance = Client.getClient().getPlayer().getDistanceToEntity(entity);
        		if (curDistance <= distance) {
        			distance = curDistance;
        			tempEntity = entity;
        		}
        		
        	}
        }
        
        return tempEntity != null? (EntityPlayer)tempEntity : null;
    }
	
	private boolean isAttackable(EntityPlayer player){
		return Minecraft.getMinecraft().thePlayer.canEntityBeSeen(player) && player.getHealth() > 0 && !player.isInvisible() && player.ticksExisted > 60;
	}
	
	@Override
	public ModType getType() {
		return ModType.COMBAT;
	}
	
	@Override
	public void onEnable() {
		
	}

	@Override
	public void onDisable() {
		
	}
}
