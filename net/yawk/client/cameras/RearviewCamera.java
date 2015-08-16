package net.yawk.client.cameras;

import net.minecraft.client.Minecraft;
import net.yawk.client.Client;
import net.yawk.client.gui.Window;

public class RearviewCamera extends Camera{
	
	private Minecraft mc;
	
	public RearviewCamera(Window displayedWindow){
		super(displayedWindow, true);
		mc = Minecraft.getMinecraft();
	}

	@Override
	public void updateFramebuffer() {
		
		setToEntityPosition(mc.thePlayer);
		
		cameraRotationYaw = mc.thePlayer.rotationYaw+180;
		cameraRotationPitch = mc.thePlayer.rotationPitch;
		
		super.updateFramebuffer();
	}
	
}
