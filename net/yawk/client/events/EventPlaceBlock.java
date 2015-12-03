package net.yawk.client.events;

import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;

import com.darkmagician6.eventapi.events.callables.EventCancellable;

public class EventPlaceBlock extends EventCancellable{
	
	private BlockPos pos;
	private EnumFacing facing;
	private ItemStack stack;
	private Vec3 vec;
	
	public EventPlaceBlock(BlockPos pos, EnumFacing facing, ItemStack stack,
			Vec3 vec) {
		super();
		this.pos = pos;
		this.facing = facing;
		this.stack = stack;
		this.vec = vec;
	}

	public BlockPos getPos() {
		return pos;
	}

	public void setPos(BlockPos pos) {
		this.pos = pos;
	}

	public EnumFacing getFacing() {
		return facing;
	}

	public void setFacing(EnumFacing facing) {
		this.facing = facing;
	}

	public ItemStack getStack() {
		return stack;
	}

	public void setStack(ItemStack stack) {
		this.stack = stack;
	}

	public Vec3 getVec() {
		return vec;
	}

	public void setVec(Vec3 vec) {
		this.vec = vec;
	}
}
