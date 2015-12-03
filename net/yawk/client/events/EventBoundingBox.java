package net.yawk.client.events;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;

import com.darkmagician6.eventapi.events.Event;

public class EventBoundingBox implements Event{
	
	public AxisAlignedBB box;
	public BlockPos pos;
	public IBlockState state;
	
	public EventBoundingBox(BlockPos pos, IBlockState state, AxisAlignedBB box) {
		super();
		this.pos = pos;
		this.state = state;
		this.box = box;
	}
}
