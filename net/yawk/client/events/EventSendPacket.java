package net.yawk.client.events;

import net.minecraft.network.Packet;

import com.darkmagician6.eventapi.events.callables.EventCancellable;

public class EventSendPacket extends EventCancellable{
	
	public Packet packet;
	
	public EventSendPacket(Packet packet) {
		super();
		this.packet = packet;
	}
}
