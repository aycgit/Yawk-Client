package net.yawk.client.mods.building;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.yawk.client.events.EventClickBlock;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.RegisterMod;

import com.darkmagician6.eventapi.EventTarget;

@RegisterMod(name = "AutoTool", desc = "Picks the best tool", type = Mod.Type.BUILDING)
public class AutoTool extends Mod{
	
	private Minecraft mc = Minecraft.getMinecraft();
	
	@EventTarget
	public void onClickBlock(EventClickBlock e){
		getBestTool(e.pos);
	}
	
	public void getBestTool(BlockPos pos){
		
		Block block = mc.theWorld.getBlockState(pos).getBlock();
		
		int bestSlot = 0;
		float bestStrength = 0F;
		
		for(int inventorySlot = 36; inventorySlot < 45; inventorySlot++)
		{
			ItemStack curSlot = mc.thePlayer.inventoryContainer.getSlot(inventorySlot).getStack();
			
			if(curSlot != null){
				
				float strength = curSlot.getStrVsBlock(block);
				
				if(strength > bestStrength)
				{
					bestSlot = inventorySlot;
					bestStrength = strength;
				}
			}
		}
		
		mc.thePlayer.inventory.currentItem = bestSlot-36;
	}
}
