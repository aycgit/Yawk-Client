package net.yawk.client.cameras;

import net.minecraft.client.Minecraft;
import net.yawk.client.Client;
import net.yawk.client.gui.Window;
import net.yawk.client.gui.components.selectors.EntitySelectorButton;
import net.yawk.client.gui.components.selectors.SelectorButton;
import net.yawk.client.gui.components.selectors.SelectorSystem;

public class PlayerCamera extends Camera{
	
	private SelectorSystem<SelectorButton> selectors;

	public PlayerCamera(Window displayedWindow, SelectorSystem<SelectorButton> selectors){
		super(displayedWindow);
		this.selectors = selectors;
	}

	@Override
	public void updateFramebuffer() {
		
		if(selectors.selectedButton != null){
			setToEntityPositionAndRotation(((EntitySelectorButton)selectors.selectedButton).getPlayer());
		}
		
		super.updateFramebuffer();
	}
	
}
