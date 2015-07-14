package net.yawk.client.mods;

import java.lang.reflect.Field;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.Vec3;
import net.yawk.client.Client;
import net.yawk.client.events.EventRender;
import net.yawk.client.events.EventTick;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.ModType;

import com.darkmagician6.eventapi.EventTarget;

public class ArrowSense implements Mod{
	
	public ArrowSense(){
		
	}
	
	@Override
	public String getName() {
		return "ArrowSense";
	}
	
	@Override
	public String getDescription() {
		return "See where mid-air arrows will land";
	}
	
	@EventTarget
	public void onRender(EventRender e){
    	for(Object obj : Client.getClient().getMinecraft().theWorld.loadedEntityList){
    		if(obj instanceof EntityArrow){
    			EntityArrow arrow = (EntityArrow) obj;
    			
    			if(isArrowAlive(arrow)){
    				renderArrow(arrow);
    			}
    		}
    	}
	}
	
	private void renderArrow(EntityArrow arrow){
		
		double x = arrow.posX, y = arrow.posY, z = arrow.posZ;
		double motionX = arrow.motionX, motionY = arrow.motionY, motionZ = arrow.motionZ;
		int vertexCounter = 0;
		
		GL11.glPushMatrix();
		
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_LINE_SMOOTH);
		GL11.glEnable(3042);
		GL11.glDisable(3553);
		
		GL11.glLineWidth(3.5F);
		GL11.glColor4f(0.5f, 0.5f, 0, 0.5f);
		GL11.glBegin(GL11.GL_LINE_STRIP);
		
		while(vertexCounter++ < 200) {
			GL11.glVertex3d(x * 1.0D - Client.getClient().getMinecraft().renderManager.renderPosX, y * 1.0D - Client.getClient().getMinecraft().renderManager.renderPosY, z * 1.0D - Client.getClient().getMinecraft().renderManager.renderPosZ);
			
			x += motionX;
			y += motionY;
			z += motionZ;
			motionX *= 0.99D;
			motionY *= 0.99D;
			motionZ *= 0.99D;
			motionY -= 0.05D;
			
			if(Client.getClient().getMinecraft().theWorld.rayTraceBlocks(new Vec3(arrow.posX, arrow.posY, arrow.posZ), new Vec3(x, y, z), true) != null){
				break;
			}
		}
		
		GL11.glEnd();
		
		GL11.glDisable(3042);
		GL11.glEnable(3553);
		GL11.glDisable(GL11.GL_LINE_SMOOTH);
		GL11.glEnable(GL11.GL_LIGHTING);
		
		GL11.glPopMatrix();
	}
	
	private boolean isArrowAlive(EntityArrow arrow){
		return arrow != null 
				&& !arrow.inGround
				&& !arrow.isCollided
				&& !arrow.isDead
				&& arrow.getDistanceToEntity(Client.getClient().getPlayer()) < 250
				&& Client.getClient().getMinecraft().theWorld.loadedEntityList.contains(arrow);
	}
	
	@Override
	public ModType getType() {
		return ModType.WORLD;
	}
	
	@Override
	public void onEnable() {
		
	}
	
	@Override
	public void onDisable() {
		
	}
}
