package net.yawk.client.mods.building;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.yawk.client.Client;
import net.yawk.client.events.EventClickBlock;
import net.yawk.client.events.EventDamageBlock;
import net.yawk.client.events.EventGuiRender;
import net.yawk.client.events.EventTick;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.ModDetails;
import net.yawk.client.utils.ClientUtils;

import com.darkmagician6.eventapi.EventTarget;

@ModDetails(name = "Nuker", desc = "Break blocks around you", type = Mod.Type.BUILDING)
public class Nuker extends Mod{
	
	private IBlockState block;
	
	@EventTarget
	public void onClickBlock(EventClickBlock e){
		
		if(!(Client.getClient().getMinecraft().theWorld.getBlockState(e.pos) instanceof BlockAir))
		{
			block = Client.getClient().getMinecraft().theWorld.getBlockState(e.pos);
		}
	}
	
	@EventTarget
	public void onTick(EventTick e){
		if(block != null)
		{
			int maxH = 5;
			int minH = -5;
			int maxW = 3;
			int minW = -3;
			
			for(int y = maxH; y >= minH; y--)
			{
				for(int z = minW; z <= maxW; z++)
				{
					for(int x = minW; x <= maxW; x++)
					{
						int xPos = (int) Math.round(Client.getClient().getPlayer().posX + x);
						int yPos = (int) Math.round(Client.getClient().getPlayer().posY + y);
						int zPos = (int) Math.round(Client.getClient().getPlayer().posZ + z);
						
						IBlockState id = Client.getClient().getMinecraft().theWorld.getBlockState(new BlockPos(x, y, z));
						
						if(Client.getClient().getMinecraft().playerController.isInCreativeMode() || id.getBlock().getMaterial() == block.getBlock().getMaterial())
						{
							ClientUtils.sendPacket(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.START_DESTROY_BLOCK, new BlockPos(xPos, yPos, zPos), EnumFacing.NORTH));
							ClientUtils.sendPacket(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.STOP_DESTROY_BLOCK, new BlockPos(xPos, yPos, zPos), EnumFacing.NORTH));
						}
					}
				}
			}
		}
	}
	
	@Override
	public void onEnable() {
		block = null;
	}

	@Override
	public void onDisable() {
		
	}
}
