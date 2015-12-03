package net.yawk.client.gui.components.selectors;

import net.minecraft.entity.player.EntityPlayer;

public class EntitySelectorButton extends SelectorButton{

	private EntityPlayer player;
	
	public EntitySelectorButton(EntityPlayer player, SelectorSystem system) {
		super(player.getName(), system);
		this.player = player;
	}

	@Override
	public String getText() {
		return player.getName();
	}
	
	public EntityPlayer getPlayer() {
		return player;
	}
}
