package net.yawk.client.cameras;

import net.minecraft.client.entity.EntityPlayerSP;
import net.yawk.client.Client;

public class RearviewCamera extends Camera{
	
	public EntityPlayerSP player;
	
	public RearviewCamera(){
		super();
		player = Client.getClient().getPlayer();
	}
	
	@Override
    protected void setCapture(boolean capture){
		
    	if(capture){
    		
    		cameraPosX = player.posX;
    		cameraPosY = player.posY;
    		cameraPosZ = player.posZ;
    		
    		cameraRotationYaw = player.rotationYaw+180;
    		cameraRotationPitch = player.rotationPitch;
    	}
    	
    	super.setCapture(capture);
    }
	
}
