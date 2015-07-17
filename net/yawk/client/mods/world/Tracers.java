package net.yawk.client.mods.world;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;
import net.yawk.client.Client;
import net.yawk.client.events.EventRender;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.ModDetails;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

import com.darkmagician6.eventapi.EventTarget;

@ModDetails(name = "Tracers", desc = "Draws lines to players", type = Mod.Type.WORLD)
public class Tracers extends Mod{

	@Override
	public void onEnable() {
		
	}

	@Override
	public void onDisable() {
		
	}
	
	@EventTarget
	public void onRender(EventRender e){
		
		boolean viewBobbing = Client.getClient().getMinecraft().gameSettings.viewBobbing;
		
		Client.getClient().getMinecraft().gameSettings.viewBobbing = false;
		
		GL11.glPushMatrix();
		GL11.glEnable(GL11.GL_LINE_SMOOTH);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(false);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glEnable(GL13.GL_MULTISAMPLE);
		for(Object player: Client.getClient().getMinecraft().theWorld.playerEntities)
		{
			if (player != Client.getClient().getPlayer() && player != null)
			{
				EntityPlayer entity = (EntityPlayer)player;
				
				float distance = Client.getClient().getPlayer().getDistanceToEntity(entity);
				
				if(distance < 10){
					GL11.glColor4f(distance/10, 0.1f, 0, 1);
				}else if(distance < 30){
					GL11.glColor4f(0.1f, distance/30, 0, 1);
				}else{
					GL11.glColor4f(0.3f, 0.3f, 1, 1);
				}
				
				double posX = entity.posX - Client.getClient().getMinecraft().renderManager.renderPosX;
				double posY = entity.posY + entity.getEyeHeight() - Client.getClient().getMinecraft().renderManager.renderPosY;
				double posZ = entity.posZ - Client.getClient().getMinecraft().renderManager.renderPosZ;
				
				GL11.glLineWidth(2f);
				GL11.glBegin(GL11.GL_LINE_LOOP);
				GL11.glVertex3d(0, Client.getClient().getPlayer().getEyeHeight(), 0);
				GL11.glVertex3d(posX, posY, posZ);
				GL11.glEnd();
			}
		}
		GL11.glDisable(GL13.GL_MULTISAMPLE);

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glDepthMask(true);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_LINE_SMOOTH);
		GL11.glPopMatrix();
		
		Client.getClient().getMinecraft().gameSettings.viewBobbing = viewBobbing;
	}
}
