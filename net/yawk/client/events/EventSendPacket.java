package net.yawk.client.events;

import net.minecraft.network.Packet;

import com.darkmagician6.eventapi.events.callables.EventCancellable;

public class EventSendPacket extends EventCancellable{
	
	public Packet packet;
	public byte type;

	public EventSendPacket(byte type, Packet packet) {
		super();
		this.type = type;
		this.packet = packet;
	}
}
