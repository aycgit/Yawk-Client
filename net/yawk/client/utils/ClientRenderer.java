package net.yawk.client.utils;

import static org.lwjgl.opengl.GL11.*;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumChatFormatting;
import net.yawk.client.Client;
import net.yawk.client.friends.FriendType;

import org.lwjgl.opengl.GL11;

/**
 * Draws various things like boxes
 *@author 10askinsw
 */
//TODO: Make it more consistent for how to decide ESP colours
public class ClientRenderer {
	
	private static float insideAlpha = 0.12f;
	private static float outlineAlpha = 0.2f;
	
	//Used by the Parkour prediction hack
	public static void drawFloorESP(double x, double y, double z, float r, float g, float b, float xArea) {
		
		startDrawing();
		
		glLineWidth(1);
		
		glColor4f(r, g, b, insideAlpha);
		drawCube(x-xArea, y, z-xArea, x+xArea, y + 0.1f, z+xArea);
		
		glColor4f(r, g, b, outlineAlpha);
		drawCubeOutline(x-xArea, y, z-xArea, x+xArea, y + 0.1f, z+xArea);
		
        glColor4f(1, 1, 1, 1);
        stopDrawing();
	}
	
	public static void drawBlockESP(double x, double y, double z, float r, float g, float b) {
		
		startDrawing();
		
		glLineWidth(1);
		
		glColor4f(r, g, b, insideAlpha);
		drawCube(x, y, z, x+1, y+1, z+1);
		
		glColor4f(r, g, b, outlineAlpha);
		drawCubeOutline(x, y, z, x+1, y+1, z+1);
				
        glColor4f(1, 1, 1, 1);
        stopDrawing();
	}
	
	//TODO: Handle friends/enemies and also health and armour stuff
	public static void drawPlayerESP(EntityPlayer p){
		
		startDrawing();
		
		glLineWidth(1);
				
		float r = Client.getClient().getMinecraft().timer.renderPartialTicks;
		
		double x = p.lastTickPosX - (p.lastTickPosX - p.posX)*r - Client.getClient().getMinecraft().renderManager.renderPosX;
		double y = p.lastTickPosY - (p.lastTickPosY - p.posY)*r - Client.getClient().getMinecraft().renderManager.renderPosY;
		double z = p.lastTickPosZ - (p.lastTickPosZ - p.posZ)*r - Client.getClient().getMinecraft().renderManager.renderPosZ;
		
		AxisAlignedBB box = new AxisAlignedBB(x - 0.5, y, z - 0.5, x + 0.5, y + 2, z + 0.5);
		
		glColor4f(0, 1, 0, insideAlpha);
		drawCube(box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ);
		
		glColor4f(0, 1, 0, outlineAlpha);
		drawCubeOutline(box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ);
		
        glColor4f(1, 1, 1, 1);
		stopDrawing();
	}
	
	public static void drawPlayerNametag(EntityPlayer p, double scale, boolean health){
		
		RenderManager rm = Client.getClient().getMinecraft().renderManager;
		FontRenderer fontRenderer = Client.getClient().getFontRenderer();
		
		String displayName = p.getName();
		
		FriendType type = Client.getClient().getFriendManager().getFriendType(displayName);
		
		if(type != null && type != FriendType.NEUTRAL){
			displayName = type.getColour() + displayName;
		}
		
		if(health){
			displayName += " " + EnumChatFormatting.GREEN+(ClientUtils.sfTwo.format(p.getHealth() / 2f));
		}
		
		float r = Client.getClient().getMinecraft().timer.renderPartialTicks;
		
		double x = p.lastTickPosX - (p.lastTickPosX - p.posX)*r - rm.renderPosX;
		double y = p.lastTickPosY - (p.lastTickPosY - p.posY)*r - rm.renderPosY;
		double z = p.lastTickPosZ - (p.lastTickPosZ - p.posZ)*r - rm.renderPosZ;
		
        float tagScale = (float) (Client.getClient().getPlayer().getDistanceToEntity(p) * scale + 0.02f);
        
        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y + p.height + 0.5F, z);
        GL11.glNormal3f(0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(-rm.playerViewY, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(rm.playerViewX, 1.0F, 0.0F, 0.0F);
        GlStateManager.scale(-tagScale, -tagScale, tagScale);
        GlStateManager.disableLighting();
        GlStateManager.depthMask(false);
        GlStateManager.disableDepth();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        
        Tessellator tes = Tessellator.getInstance();
        WorldRenderer wr = tes.getWorldRenderer();
        
        GlStateManager.func_179090_x();
        
        int halfNameWidth = fontRenderer.getStringWidth(displayName) / 2;
        
        GuiUtils.drawRect(-halfNameWidth - 2, -2, halfNameWidth + 1, fontRenderer.FONT_HEIGHT, 0x4F000000);
        
        GlStateManager.func_179098_w();
        
        fontRenderer.drawString(displayName, -halfNameWidth, 0, p.isSneaking()? 0xFFFFFF00:-1);
        
        GlStateManager.enableLighting();
        GlStateManager.disableBlend();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.popMatrix();
	}
	
	private static void drawCubeOutline(double x, double y, double z, double x1, double y1, double z1){
		
		Tessellator tes = Tessellator.getInstance();
        WorldRenderer wr = tes.getWorldRenderer();
        
        //Bottom outline
        wr.startDrawing(3);
        wr.addVertex(x, y, z);
        wr.addVertex(x1, y, z);
        wr.addVertex(x1, y, z1);
        wr.addVertex(x, y, z1);
        wr.addVertex(x, y, z);
        tes.draw();
        
        //Top outline
        wr.startDrawing(3);
        wr.addVertex(x, y1, z);
        wr.addVertex(x1, y1, z);
        wr.addVertex(x1, y1, z1);
        wr.addVertex(x, y1, z1);
        wr.addVertex(x, y1, z);
        tes.draw();
        
        //Vertical lines
        wr.startDrawing(1);
        
        wr.addVertex(x, y, z);
        wr.addVertex(x, y1, z);
        
        wr.addVertex(x1, y, z);
        wr.addVertex(x1, y1, z);
        
        wr.addVertex(x1, y, z1);
        wr.addVertex(x1, y1, z1);
        
        wr.addVertex(x, y, z1);
        wr.addVertex(x, y1, z1);
        
        tes.draw();
	}
	
	//TODO: Work out glitches with this
	//TODO: Does this already use frustum culling or do I need to implement that? Gonna find out sometime
	private static void drawCube(double x, double y, double z, double x1, double y1, double z1){
		
		Tessellator tes = Tessellator.getInstance();
        WorldRenderer wr = tes.getWorldRenderer();
        
	    wr.startDrawingQuads();
	    
	    wr.addVertex(x, y, z);
	    wr.addVertex(x, y1, z);
	    wr.addVertex(x1, y, z);
	    wr.addVertex(x1, y1, z);
	    
	    wr.addVertex(x1, y, z1);
	    wr.addVertex(x1, y1, z1);
	    wr.addVertex(x, y, z1);
	    wr.addVertex(x, y1, z1);
	    
	    tes.draw();
	    
	    wr.startDrawingQuads();
	    
	    wr.addVertex(x1, y1, z);
	    wr.addVertex(x1, y, z);
	    wr.addVertex(x, y1, z);
	    wr.addVertex(x, y, z);
	    
	    wr.addVertex(x, y1, z1);
	    wr.addVertex(x, y, z1);
	    wr.addVertex(x1, y1, z1);
	    wr.addVertex(x1, y, z1);
	    
	    tes.draw();
	    
	    wr.startDrawingQuads();
	    
	    wr.addVertex(x, y1, z);
	    wr.addVertex(x1, y1, z);
	    wr.addVertex(x1, y1, z1);
	    wr.addVertex(x, y1, z1);
	    
	    wr.addVertex(x, y1, z);
	    wr.addVertex(x, y1, z1);
	    wr.addVertex(x1, y1, z1);
	    wr.addVertex(x1, y1, z);
	    
	    tes.draw();
	    
	    wr.startDrawingQuads();
	    
	    wr.addVertex(x, y, z);
	    wr.addVertex(x1, y, z);
	    wr.addVertex(x1, y, z1);
	    wr.addVertex(x, y, z1);
	    
	    wr.addVertex(x, y, z);
	    wr.addVertex(x, y, z1);
	    wr.addVertex(x1, y, z1);
	    wr.addVertex(x1, y, z);
	    
	    tes.draw();
	    
	    wr.startDrawingQuads();
	    
	    wr.addVertex(x, y, z);
	    wr.addVertex(x, y1, z);
	    wr.addVertex(x, y, z1);
	    wr.addVertex(x, y1, z1);
	    
	    wr.addVertex(x1, y, z1);
	    wr.addVertex(x1, y1, z1);
	    wr.addVertex(x1, y, z);
	    wr.addVertex(x1, y1, z);
	    
	    tes.draw();
	    
	    wr.startDrawingQuads();
	    
	    wr.addVertex(x, y1, z1);
	    wr.addVertex(x, y, z1);
	    wr.addVertex(x, y1, z);
	    wr.addVertex(x, y, z);
	    
	    wr.addVertex(x1, y1, z);
	    wr.addVertex(x1, y, z);
	    wr.addVertex(x1, y1, z1);
	    wr.addVertex(x1, y, z1);
	    
	    tes.draw();
	}
	
	public static void startDrawing(){
		glPushMatrix();
		glEnable(GL_BLEND);
		glDisable(GL_TEXTURE_2D);
		glDisable(GL_LIGHTING);
		glEnable(GL_LINE_SMOOTH);
		glDepthMask(false);
		glDisable(GL_DEPTH_TEST);
	}
	
	public static void stopDrawing(){
		glEnable(GL_DEPTH_TEST);
		glDepthMask(true);
		glDisable(GL_LINE_SMOOTH);
		glEnable(GL_LIGHTING);
		glEnable(GL_TEXTURE_2D);
		glDisable(GL_BLEND);
		GL11.glPopMatrix();
	}
	
	public static void drawLine(double x, double y, double z, double x1, double y1, double z1){
		
		Tessellator tes = Tessellator.getInstance();
        WorldRenderer wr = tes.getWorldRenderer();
        
        wr.startDrawing(GL_LINES);
        wr.addVertex(x, y, z);
        wr.addVertex(x1, y1, z1);
        tes.draw();
	}
}
