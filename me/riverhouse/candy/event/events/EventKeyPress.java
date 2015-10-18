package me.riverhouse.candy.event.events;

import me.riverhouse.candy.event.Event;

public class EventKeyPress extends Event {

	private int key;

	public EventKeyPress(int key) {
		this.key = key;
	}

	public int getKey() {
		return key;
	}
	
}
