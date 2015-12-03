package net.yawk.client.events;

import net.minecraft.network.Packet;

import com.darkmagician6.eventapi.events.callables.EventCancellable;

public class EventRecievePacket extends EventCancellable{
	
	public Packet packet;
	
	public EventRecievePacket(Packet packet) {
		super();
		this.packet = packet;
	}
}
