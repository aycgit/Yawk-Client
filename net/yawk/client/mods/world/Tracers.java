package net.yawk.client.mods.world;

import java.util.List;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;
import net.yawk.client.Client;
import net.yawk.client.events.EventRender;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.RegisterMod;
import net.yawk.client.utils.ClientRenderer;
import static org.lwjgl.opengl.GL11.*;

import com.darkmagician6.eventapi.EventTarget;

@RegisterMod(name = "Tracers", desc = "Draws lines to players", type = Mod.Type.WORLD)
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
		
		ClientRenderer.startDrawing();
		
		for(EntityPlayer player: (List<EntityPlayer>)Client.getClient().getMinecraft().theWorld.playerEntities)
		{
			if (player != Client.getClient().getPlayer() && player != null)
			{				
				float distance = Client.getClient().getPlayer().getDistanceToEntity(player);
				
				if(distance < 10){
					glColor4f(distance/10, 0.1f, 0, 1);
				}else if(distance < 30){
					glColor4f(0.1f, distance/30, 0, 1);
				}else{
					glColor4f(0.3f, 0.3f, 1, 1);
				}
				
				double posX = player.posX - Client.getClient().getMinecraft().renderManager.renderPosX;
				double posY = player.posY + player.getEyeHeight() - Client.getClient().getMinecraft().renderManager.renderPosY;
				double posZ = player.posZ - Client.getClient().getMinecraft().renderManager.renderPosZ;
				
				ClientRenderer.drawLine(0, Client.getClient().getPlayer().getEyeHeight(), 0, posX, posY, posZ);
			}
		}
		
		ClientRenderer.stopDrawing();
		
		Client.getClient().getMinecraft().gameSettings.viewBobbing = viewBobbing;
	}
}
