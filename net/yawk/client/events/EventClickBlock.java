package net.yawk.client.events;

import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

import com.darkmagician6.eventapi.events.callables.EventCancellable;

public class EventClickBlock extends EventCancellable{
	
	public BlockPos pos;
	public EnumFacing facing;
	
	public EventClickBlock(BlockPos pos, EnumFacing facing) {
		this.pos = pos;
		this.facing = facing;
	}
}
