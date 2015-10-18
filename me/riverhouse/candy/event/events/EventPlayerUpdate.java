package me.riverhouse.candy.event.events;

import me.riverhouse.candy.event.Event;
import me.riverhouse.candy.event.EventType;

public class EventPlayerUpdate extends Event {

	private EventType type;

	public EventPlayerUpdate(EventType type) {
		this.type = type;
	}

	public EventType getType() {
		return type;
	}
	
}
