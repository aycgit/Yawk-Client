package net.yawk.client.mods.world;

import org.lwjgl.input.Keyboard;

import com.darkmagician6.eventapi.EventTarget;
import com.darkmagician6.eventapi.types.EventType;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockBarrier;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.material.Material;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.yawk.client.Client;
import net.yawk.client.events.*;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.ModDetails;
import net.yawk.client.utils.ClientUtils;

@ModDetails(name = "Phase", defaultKey = 0, desc = "Walk through transparent blocks", type = Mod.Type.WORLD)
public class Phase extends Mod{

	public Phase(){
		
	}
	
	@Override
	public void onEnable(){
		
	}
	
	@EventTarget
	public void onSendPacket(EventSendPacket e){
		
	}
	
	@EventTarget
	public void onTick(EventTick e){
		
	}
	
	@Override
	public void onDisable()
	{
		
	}
	
	@EventTarget
	public void onPushOut(EventPushOut e){
		e.setCancelled(true);
	}
	
	@EventTarget
	public void onBoundingBox(EventBoundingBox e)
	{
		if(Client.getClient().getPlayer().isCollidedHorizontally){
			if(e.state.getBlock() instanceof BlockDoor || e.state.getBlock().getMaterial() == Material.glass || e.state.getBlock() instanceof BlockBarrier){
				e.box = null;
			}else if(e.state.getBlock().isSolidFullCube()){
				e.box = new AxisAlignedBB(e.pos.getX(), e.pos.getY(), e.pos.getZ(), e.pos.getX()+1, e.pos.getY()+0.8, e.pos.getZ()+1);
			}
		}
	}
	
	private boolean isInsideBlock()
	{
		for (int x = MathHelper.floor_double(Client.getClient().getMinecraft().thePlayer.boundingBox.minX); x < 
				MathHelper.floor_double(Client.getClient().getMinecraft().thePlayer.boundingBox.maxX) + 1; x++) {
			for (int y = MathHelper.floor_double(Client.getClient().getMinecraft().thePlayer.boundingBox.minY); y < 
					MathHelper.floor_double(Client.getClient().getMinecraft().thePlayer.boundingBox.maxY) + 1; y++) {
				for (int z = MathHelper.floor_double(Client.getClient().getMinecraft().thePlayer.boundingBox.minZ); z < 
						MathHelper.floor_double(Client.getClient().getMinecraft().thePlayer.boundingBox.maxZ) + 1; z++)
				{
					Block block = Client.getClient().getMinecraft().theWorld
							.getBlockState(new BlockPos(x, y, z)).getBlock();
					if ((block != null) && (!(block instanceof BlockAir)))
					{
						AxisAlignedBB boundingBox = block
								.getCollisionBoundingBox(Client.getClient().getMinecraft().theWorld, new BlockPos(x, y, z), Client.getClient().getMinecraft().theWorld.getBlockState(new BlockPos(x, y, z)));
						if ((boundingBox != null) && 
								(Client.getClient().getMinecraft().thePlayer.boundingBox.intersectsWith(boundingBox))) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
}
