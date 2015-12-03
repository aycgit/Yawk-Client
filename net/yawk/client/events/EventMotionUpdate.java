package net.yawk.client.events;

import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

import com.darkmagician6.eventapi.events.callables.EventCancellable;

public class EventMotionUpdate extends EventCancellable{
	
	public byte type;
	
	public EventMotionUpdate(byte type){
		this.type = type;
	}
}
