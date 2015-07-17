package net.yawk.client.mods.building;

import net.minecraft.block.Block;

import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.yawk.client.Client;
import net.yawk.client.events.EventTick;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.ModDetails;

import com.darkmagician6.eventapi.EventTarget;

@ModDetails(name = "LightsOut", defaultKey = 0, desc = "Destroy torches and other one hit blocks", type = Mod.Type.BUILDING)
public class LightsOut extends Mod{

	public LightsOut(){
		
	}
	
	private BlockPos blockPos;
	private Block blockType;
	
	@EventTarget
	public void onTick(EventTick tick){
		
		for(int ix = -3; ix<4; ix++)
		{
			for(int iy = -3; iy<4; iy++)
			{
				for(int iz = -3; iz<4; iz++)
				{
					int e = (int) Client.getClient().getPlayer().posX+ix;
					int f = (int) Client.getClient().getPlayer().posY+iy;
					int g = (int) Client.getClient().getPlayer().posZ+iz;

					blockPos = new BlockPos(e,f,g);
					blockType = Minecraft.getMinecraft().theWorld.getBlockState(blockPos).getBlock();
					//nether_wart added (1.31)
					//reeds added (1.32)
					if(blockType == Block.getBlockFromName("reeds") || blockType == Block.getBlockFromName("nether_wart") || blockType == Block.getBlockFromName("torch") || blockType == Block.getBlockFromName("wheat") || blockType == Block.getBlockFromName("unlit_redstone_torch") || blockType == Block.getBlockFromName("redstone_torch") || blockType == Block.getBlockFromName("redstone_wire") || blockType == Block.getBlockFromName("red_mushroom") || blockType == Block.getBlockFromName("brown_mushroom") || blockType == Block.getBlockFromName("tallgrass") || blockType == Block.getBlockFromName("deadbush") || blockType == Block.getBlockFromName("yellow_flower") || blockType == Block.getBlockFromName("red_flower") || blockType == Block.getBlockFromName("sapling") || blockType == Block.getBlockFromName("fire") || blockType == Block.getBlockFromName("carrots") || blockType == Block.getBlockFromName("potatoes") || blockType == Block.getBlockFromName("powered_comparator") || blockType == Block.getBlockFromName("unpowered_comparator") || blockType == Block.getBlockFromName("powered_repeater") || blockType == Block.getBlockFromName("unpowered_repeater"))
					{
						C07PacketPlayerDigging.Action nuke1 = C07PacketPlayerDigging.Action.START_DESTROY_BLOCK;
						C07PacketPlayerDigging.Action nuke2 = C07PacketPlayerDigging.Action.STOP_DESTROY_BLOCK;
						
						Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C07PacketPlayerDigging(nuke1,blockPos,EnumFacing.UP));
						Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C07PacketPlayerDigging(nuke2,blockPos,EnumFacing.UP));
					}
				}
			}
		}
	}
	
	@Override
	public void onEnable() {

	}

	@Override
	public void onDisable() {

	}
}
