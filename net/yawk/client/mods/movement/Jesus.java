package net.yawk.client.mods.movement;

import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.yawk.client.Client;
import net.yawk.client.events.EventBoundingBox;
import net.yawk.client.events.EventJump;
import net.yawk.client.events.EventSendPacket;
import net.yawk.client.events.EventTick;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.ModDetails;
import net.yawk.client.utils.ClientUtils;

import com.darkmagician6.eventapi.EventTarget;
import com.darkmagician6.eventapi.types.EventType;

@ModDetails(name = "Jesus", defaultKey = 0, desc = "Walk on water", type = Mod.Type.MOVEMENT)
public class Jesus extends Mod{
	
	public Jesus(){
		
	}

	@EventTarget
	public void onBB(EventBoundingBox e){
		if(Client.getClient().getMinecraft().theWorld.getBlockState(e.pos).getBlock().getMaterial().isLiquid()){
			e.box = new AxisAlignedBB(e.pos.getX(), e.pos.getY(), e.pos.getZ(), e.pos.getX() + 1, e.pos.getY() + 1, e.pos.getZ() + 1);
		}
	}
	
	@EventTarget
	public void onSend(EventSendPacket e){
		
		if(e.packet instanceof C03PacketPlayer && inWater()){
			((C03PacketPlayer)e.packet).y = Client.getClient().getPlayer().posY + (Client.getClient().getPlayer().ticksExisted % 2 == 0? 0.01 : -0.01);
		}
		
	}
	
	public boolean inWater(){
		
		double w = Client.getClient().getPlayer().boundingBox.maxX-Client.getClient().getPlayer().boundingBox.minX;
		double half = w/2;
		
		boolean hasLiquid = false;
		
		AxisAlignedBB playerBox = new AxisAlignedBB(Client.getClient().getPlayer().posX-half, Client.getClient().getPlayer().posY-0.3, Client.getClient().getPlayer().posZ-half, Client.getClient().getPlayer().posX+half, Client.getClient().getPlayer().posY+0.5, Client.getClient().getPlayer().posZ+half);
		
		for(int x = -2; x < 2; x++){
			for(int y = -2; y < 2; y++){
				for(int z = -2; z < 2; z++){
					
					AxisAlignedBB box = new AxisAlignedBB(Client.getClient().getPlayer().posX+x, Client.getClient().getPlayer().posY+y, Client.getClient().getPlayer().posZ+z, Client.getClient().getPlayer().posX+x+1, Client.getClient().getPlayer().posY+y+1, Client.getClient().getPlayer().posZ+z+1);
					
					if(box.intersectsWith(playerBox)){
						
						System.out.println("intersect");
						
						IBlockState block = Client.getClient().getMinecraft().theWorld.getBlockState(new BlockPos(Client.getClient().getPlayer().posX+x, Client.getClient().getPlayer().posY+y, Client.getClient().getPlayer().posZ+z));
						
						if(block.getBlock().getMaterial() == Material.air){
							continue;
						}else if(block.getBlock().getMaterial() == Material.water){
							hasLiquid = true;
						}else{
							return false;
						}
					}
				}
			}
		}
				
		return hasLiquid;
	}
	
	@Override
	public void onEnable() {
		
	}
	
	@Override
	public void onDisable() {
		
	}
}
