package net.yawk.client.events;

import net.yawk.client.modmanager.Mod;

import com.darkmagician6.eventapi.events.Event;

public class EventDisabled implements Event {
	
	public Mod module;
	
	public EventDisabled(Mod module) {
		super();
		this.module = module;
	}
}
