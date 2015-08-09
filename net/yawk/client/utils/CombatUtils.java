package net.yawk.client.utils;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.C03PacketPlayer;
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

	public static void faceEntity(Entity e, boolean silent){
		
		double x = e.posX - Client.getClient().getMinecraft().thePlayer.posX;
		double y = e.posY - Client.getClient().getMinecraft().thePlayer.posY;
		double z = e.posZ - Client.getClient().getMinecraft().thePlayer.posZ;
		double d1 = (Client.getClient().getMinecraft().thePlayer.posY + (double)Client.getClient().getMinecraft().thePlayer.getEyeHeight())-(e.posY+(double)e.getEyeHeight());
		double d3 = MathHelper.sqrt_double(x*x+z*z);
		float f = (float)((Math.atan2(z, x)*180D)/Math.PI)-90F;
		float f1 = (float) ((Math.atan2(d1, d3)*180D)/Math.PI);
		
		if(silent){
			ClientUtils.sendPacket(new C03PacketPlayer.C05PacketPlayerLook(f, f1, Client.getClient().getPlayer().onGround));
		}else{
			Client.getClient().getPlayer().rotationPitch = f;
			Client.getClient().getPlayer().rotationYaw = f1;
		}
		
	}

	/**
	 * Begins to look towards an entity
	 * @param e
	 * @param move
	 * @return whether you're now looking near to the entity
	 */
	public static boolean faceEntitySmooth(Entity e, boolean move, boolean silent)
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

			if(silent){
				ClientUtils.sendPacket(new C03PacketPlayer.C05PacketPlayerLook(f, f1, Client.getClient().getPlayer().onGround));
			}else{
				Client.getClient().getPlayer().setPositionAndRotation(Client.getClient().getMinecraft().thePlayer.posX, Client.getClient().getMinecraft().thePlayer.posY, Client.getClient().getMinecraft().thePlayer.posZ, Client.getClient().getPlayer().rotationYaw+f, Client.getClient().getPlayer().rotationPitch+f1);
			}
		}

		return f < 30 && f1 < 30 && f > -30 && f1 > -30;
	}

}
