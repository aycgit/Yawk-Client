package net.yawk.client.cameras;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.projectile.EntityArrow;
import net.yawk.client.gui.Window;

public class ArrowCamera extends Camera{
	
	private EntityArrow currentArrow;
	private Minecraft mc;
	
	public ArrowCamera(Window displayedWindow) {
		super(displayedWindow);
		mc = Minecraft.getMinecraft();
	}

	@Override
	public void updateFramebuffer() {
		
		if(isArrowAlive()){
			
			setToEntityPosition(currentArrow);
			cameraRotationYaw = currentArrow.rotationYaw+90;
			cameraRotationPitch = currentArrow.rotationPitch+180;
			
		}else{
			
			for(Object obj : mc.theWorld.loadedEntityList){
				if(obj != null && obj instanceof EntityArrow){
					EntityArrow arrow = (EntityArrow)obj;
					if(arrow.shootingEntity == mc.thePlayer){
						currentArrow = arrow;
					}
				}
			}
			
			setToEntityPositionAndRotation(mc.thePlayer);
		}
		
		super.updateFramebuffer();
	}

	private boolean isArrowAlive(){
		return currentArrow != null 
				&& !currentArrow.inGround
				&& !currentArrow.isCollided
				&& !currentArrow.isDead
				&& mc.theWorld.loadedEntityList.contains(currentArrow);
	}
}