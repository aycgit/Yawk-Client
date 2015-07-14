package net.yawk.client.mods;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.server.S06PacketUpdateHealth;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.yawk.client.Client;
import net.yawk.client.events.EventRecievePacket;
import net.yawk.client.events.EventTick;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.ModType;
import net.yawk.client.utils.ClientUtils;
import net.yawk.client.utils.HysteriaTimer;

import com.darkmagician6.eventapi.EventTarget;

public class FakeHacker implements Mod{
	
	public FakeHacker(){
		
	}
	
	@Override
	public String getName() {
		return "FakeHacker";
	}
	
	@Override
	public String getDescription() {
		return "Make a player look like they're hacking";
	}
	
	private HysteriaTimer timer = new HysteriaTimer(12);
	private float health;
	
	@EventTarget
	public void onTick(EventTick e){
		
		if(health > 0){
			EntityPlayer entityplayer = getClosestPlayer(3.95f);
			
			if(entityplayer != null){
				
				faceEntityManipulator(Client.getClient().getPlayer(), entityplayer);
				
				if(timer.output()){
					
					entityplayer.swingItem();
					Client.getClient().getPlayer().setHealth(health--);
					Client.getClient().getPlayer().performHurtAnimation();
					Client.getClient().getPlayer().hurtResistantTime = 20;
					
					Client.getClient().getPlayer().playSound("game.player.hurt", 1, 1);
					
					Client.getClient().getPlayer().motionX = Math.cos(Math.toRadians(entityplayer.rotationYaw))*0.1f;
					Client.getClient().getPlayer().motionY = 0.1;
					Client.getClient().getPlayer().motionZ = Math.sin(Math.toRadians(entityplayer.rotationYaw))*0.1f;
					
					if(health == 0){
						Client.getClient().getMinecraft().displayGuiScreen(new GuiGameOver());
					}
				}
			}
		}
	}
	
	@EventTarget
	public void onPacketRecieve(EventRecievePacket e){
		if(e.packet instanceof S06PacketUpdateHealth){
			if(((S06PacketUpdateHealth) e.packet).getHealth() == 0){
				e.setCancelled(true);
			}
		}
	}
	
	private void faceEntityManipulator(Entity toFace, Entity rotate){
		double var4 = toFace.posX - rotate.posX;
		double var6 = toFace.posZ - rotate.posZ;
		double var8 = toFace.posY + toFace.getEyeHeight() / 1.3D - (rotate.posY + rotate.getEyeHeight());
		double var14 = MathHelper.sqrt_double(var4 * var4 + var6 * var6);
		float var12 = (float)(Math.atan2(var6, var4) * 180.0D / 3.141592653589793D) - 90.0F;
		rotate.rotationPitch = ((float)-(Math.atan2(var8, var14) * 180.0D / 3.141592653589793D));
		rotate.rotationYaw = var12;
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
		health = 10;
	}
	
	@Override
	public void onDisable() {
		
	}
}
