package me.riverhouse.candy.event.events;

import me.riverhouse.candy.event.Event;
import net.minecraft.network.Packet;

public class EventReceivePacket extends Event {
	
	private Packet p;

	public EventReceivePacket(Packet p) {
		this.p = p;
	}

	public void setPacket(Packet p){
		this.p = p;
	}
	
	public Packet getPacket() {
		return p;
	}
}