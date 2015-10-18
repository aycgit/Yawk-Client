package me.riverhouse.candy.event.events;

import me.riverhouse.candy.event.Event;
import me.riverhouse.candy.event.EventType;

public class EventOnTick extends Event {
	
	private float time = 0;
	private EventType type;
	
	public float getTime() {
		return time;
	}
	
	public void setTIme(final float time) {
		this.time = time;
	}
	
	public EventType getType() {
		return type;
	}
}