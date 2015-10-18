package me.riverhouse.candy.event.events;

import me.riverhouse.candy.event.Event;
import net.minecraft.entity.Entity;

public class EventPlayerAttack extends Event {

	private Entity entity;

	public EventPlayerAttack(Entity entity) {
		this.entity = entity;
	}

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}
	
}
