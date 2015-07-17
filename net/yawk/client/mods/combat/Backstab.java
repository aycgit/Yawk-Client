package net.yawk.client.mods.combat;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.yawk.client.Client;
import net.yawk.client.events.EventTick;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.ModDetails;
import net.yawk.client.modmanager.ModType;

import com.darkmagician6.eventapi.EventTarget;

@ModDetails(name = "Backstab", defaultKey = 0, desc = "Teleport behind people", type = ModType.COMBAT)
public class Backstab extends Mod{
	
	public Backstab(){
		
	}
	
	@EventTarget
	public void onTick(EventTick e){
		
		EntityPlayer p = getClosestPlayer(4);
		
		if(p != null){
			
			float yaw = p.rotationYaw + 90 + 180;
			
			double x = p.posX+Math.cos(Math.toRadians(yaw));
			double z = p.posZ+Math.sin(Math.toRadians(yaw));
			
			int y = getTopY((int)x, (int)p.posY, (int)z);
			
			if(y != -1){
				Client.getClient().getPlayer().setPosition(x, y, z);
			}
		}
	}
	
	public int getTopY(int x, int curY, int z){
		
		for(int y = curY + 2; y > curY - 2; y--){
			if(Client.getClient().getMinecraft().theWorld.getBlockState(new BlockPos(x, y, z)).getBlock().getMaterial() != Material.air){
				return y+1;
			}
		}
		
		return -1;
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
        
        return tempEntity;
    }
	
	private boolean isAttackable(EntityPlayer player){
		return !player.getDisplayName().getFormattedText().equalsIgnoreCase("GrapeKush") && Minecraft.getMinecraft().thePlayer.canEntityBeSeen(player) && player.getHealth() > 0 && !player.isInvisible() && player.ticksExisted > 60;
	}
		
	@Override
	public void onEnable() {
		
	}
	
	@Override
	public void onDisable() {
		
	}
}
