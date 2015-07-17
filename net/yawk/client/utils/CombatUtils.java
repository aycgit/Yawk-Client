package net.yawk.client.utils;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.yawk.client.Client;

public class CombatUtils {
	
	public static EntityPlayer getClosestPlayer(double range) {
        double distance = range;
        EntityPlayer tempEntity = null;
        
        for (EntityPlayer entity : (List<EntityPlayer>) Client.getClient().getMinecraft().theWorld.playerEntities) {
        	if(entity != Client.getClient().getPlayer() && isAttackable(entity)){
        		
        		double curDistance = Client.getClient().getPlayer().getDistanceToEntity(entity);
        		if (curDistance <= distance) {
        			distance = curDistance;
        			tempEntity = entity;
        		}
        		
        	}
        }
        
        return tempEntity;
    }
	
	public static void hit(Entity entity){
		Client.getClient().getMinecraft().playerController.attackEntity(Client.getClient().getPlayer(), entity);
		Client.getClient().getPlayer().swingItem();
	}
	
	public static boolean isAttackable(Entity entity){
		return entity instanceof EntityLiving && isAttackable((EntityLiving)entity);
	}
	
	//TODO: make this more sophisticated
	public static boolean isAttackable(EntityLivingBase entity){
		return isAttackableBase(entity);
	}
	
	//TODO: add some stuff to the player check
	public static boolean isAttackable(EntityPlayer player){
		return isAttackableBase(player);
	}
	
	private static boolean isAttackableBase(EntityLivingBase entity){
		return Client.getClient().getMinecraft().thePlayer.canEntityBeSeen(entity) 
				&& entity.getHealth() > 0 
				&& !entity.isInvisible() 
				&& entity.ticksExisted > 60 
				&& Client.getClient().getPlayer().getDistanceToEntity(entity) <= 4.2;
	}
	
	public static void faceEntity(Entity e){
		double var4 = e.posX - Client.getClient().getPlayer().posX;
		double var6 = e.posZ - Client.getClient().getPlayer().posZ;
		double var8 = e.posY + e.getEyeHeight() / 1.3D - (Client.getClient().getPlayer().posY + Client.getClient().getPlayer().getEyeHeight());
		double var14 = MathHelper.sqrt_double(var4 * var4 + var6 * var6);
		float var12 = (float)(Math.atan2(var6, var4) * 180.0D / 3.141592653589793D) - 90.0F;
		Client.getClient().getPlayer().rotationPitch = ((float)-(Math.atan2(var8, var14) * 180.0D / 3.141592653589793D));
		Client.getClient().getPlayer().rotationYaw = var12;
	}
	
	/**
	 * Begins to look towards an entity
	 * @param e
	 * @param move
	 * @return whether you're now looking near to the entity
	 */
	public static boolean faceEntitySmooth(Entity e, boolean move)
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
		
		if(move){
			Client.getClient().getPlayer().setPositionAndRotation(Client.getClient().getMinecraft().thePlayer.posX, Client.getClient().getMinecraft().thePlayer.posY, Client.getClient().getMinecraft().thePlayer.posZ, Client.getClient().getPlayer().rotationYaw+f, Client.getClient().getPlayer().rotationPitch+f1);
		}
		
		return f < 10 && f1 < 10 && f > -10 && f1 > -10;
		
		//ClientUtils.sendPacket(new C03PacketPlayer.C05PacketPlayerLook(f, f1, Client.getClient().getPlayer().onGround));
	}

}
