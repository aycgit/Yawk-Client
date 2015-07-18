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
import net.yawk.client.utils.CombatUtils;

import com.darkmagician6.eventapi.EventTarget;

@ModDetails(name = "Backstab", desc = "Teleport behind people", type = Mod.Type.COMBAT)
public class Backstab extends Mod{
	
	public Backstab(){
		
	}
	
	@EventTarget
	public void onTick(EventTick e){
		
		EntityPlayer p = CombatUtils.getClosestPlayer(4);
		
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
			
	@Override
	public void onEnable() {
		
	}
	
	@Override
	public void onDisable() {
		
	}
}
