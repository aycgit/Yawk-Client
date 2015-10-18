package me.riverhouse.candy.event.events;

import me.riverhouse.candy.api.world.Location;
import me.riverhouse.candy.event.Event;
import me.riverhouse.candy.event.EventType;

public class EventMotion extends Event {

	private EventType type;
	private Location location;
	private boolean onGround;
	
	public EventMotion(EventType type, Location location, boolean onGround) {
		this.type = type;
		this.location = location;
		this.onGround = onGround;
	}

	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public boolean isOnGround() {
		return onGround;
	}

	public void setOnGround(boolean onGround) {
		this.onGround = onGround;
	}
	
}
