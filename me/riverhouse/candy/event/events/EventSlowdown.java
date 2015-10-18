package me.riverhouse.candy.event.events;

import me.riverhouse.candy.event.Event;
import me.riverhouse.candy.event.EventType;

public class EventSlowdown extends Event {

	private EventType type;

	public EventSlowdown(EventType type) {
		this.type = type;
	}

	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
	}
	
}
