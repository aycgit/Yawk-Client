package net.yawk.client.mods.world;

import net.minecraft.block.BlockCactus;
import net.minecraft.block.BlockSoulSand;
import net.minecraft.block.BlockWeb;
import net.minecraft.util.AxisAlignedBB;
import net.yawk.client.Client;
import net.yawk.client.events.EventBoundingBox;
import net.yawk.client.events.EventTick;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.ModType;

import com.darkmagician6.eventapi.EventTarget;

public class SafeBlocks implements Mod{
	
	public SafeBlocks(){
		
	}
	
	@Override
	public String getName() {
		return "SafeBlocks";
	}
	
	@Override
	public String getDescription() {
		return "Protects from cacti and webs";
	}
	
	@EventTarget
	public void onBoundingBox(EventBoundingBox e){
		if(e.state.getBlock() instanceof BlockCactus || e.state.getBlock() instanceof BlockWeb){
			e.box = new AxisAlignedBB(e.pos.getX(), e.pos.getY(), e.pos.getZ(), e.pos.getX()+1, e.pos.getY()+1, e.pos.getZ()+1);
		}
	}
	
	@Override
	public ModType getType() {
		return ModType.WORLD;
	}
	
	@Override
	public void onEnable() {
		
	}
	
	@Override
	public void onDisable() {
		
	}
}
