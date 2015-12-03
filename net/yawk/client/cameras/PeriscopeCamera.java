package net.yawk.client.cameras;

import net.minecraft.client.Minecraft;
import net.yawk.client.Client;
import net.yawk.client.gui.Window;

public class PeriscopeCamera extends Camera{
	
	private Minecraft mc;
	
	public PeriscopeCamera(Window displayedWindow){
		super(displayedWindow);
		mc = Minecraft.getMinecraft();
	}

	@Override
	public void updateFramebuffer() {
		
		setToEntityPositionAndRotation(mc.thePlayer);
		
		this.cameraPosY += 8;
		
		super.updateFramebuffer();
	}
	
}
