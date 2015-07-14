package net.yawk.client.events;

import com.darkmagician6.eventapi.events.Event;

public class EventPushOut implements Event{
	
	public boolean shouldPush;
	
	public EventPushOut(boolean shouldPush) {
		super();
		this.shouldPush = shouldPush;
	}
	
}
