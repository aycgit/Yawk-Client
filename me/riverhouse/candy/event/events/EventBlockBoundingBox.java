package me.riverhouse.candy.event.events;

import net.minecraft.block.Block;
import net.minecraft.util.AxisAlignedBB;
import me.riverhouse.candy.api.world.Location;
import me.riverhouse.candy.event.Event;

public class EventBlockBoundingBox extends Event {

	private AxisAlignedBB box;
	private Block block;
	private Location location;
	
	public EventBlockBoundingBox(AxisAlignedBB box, Block block, Location location) {
		this.box = box;
		this.block = block;
		this.location = location;
	}

	public AxisAlignedBB getBox() {
		return box;
	}

	public void setBox(AxisAlignedBB box) {
		this.box = box;
	}

	public Block getBlock() {
		return block;
	}

	public void setBlock(Block block) {
		this.block = block;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
		
}
