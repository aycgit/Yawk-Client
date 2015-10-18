package me.riverhouse.candy.event.events;

import me.riverhouse.candy.event.Event;
import net.minecraft.entity.Entity;

public class EventRenderNametag extends Event {

	private Entity entity;

	public EventRenderNametag(Entity entity) {
		this.entity = entity;
	}

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}
	
}
