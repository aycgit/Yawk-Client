package net.yawk.client.utils;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.yawk.client.Client;
import net.yawk.client.hooks.EntityPlayerSPHook;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.GL11;

public class ESPUtils {
		
	public static void drawRawBlockESP(double x, double y, double z, float r, float g, float b, boolean noDepthTest) {
		GL11.glPushMatrix();
		GL11.glEnable(3042 /* GL_BLEND */);
		GL11.glDisable(3553 /* GL_TEXTURE_2D */);
		GL11.glDisable(2896 /* GL_LIGHTING */);
		if(noDepthTest){
			GL11.glDisable(2929 /* GL_DEPTH_TEST */);
		}
		GL11.glDepthMask(false);
		GL11.glBlendFunc(770, 771);
		GL11.glEnable(2848 /* GL_LINE_SMOOTH */);
		GL11.glColor4f(r, g, b, 0.11F);
		drawBox(x, y, z, x + 1, y + 1, z + 1);
		GL11.glColor4f(r, g, b, 0.15F);
		drawOutlinedBox(x, y, z, x + 1, y + 1, z + 1, 1.6F);
		GL11.glDisable(2848 /* GL_LINE_SMOOTH */);
		GL11.glDepthMask(true);
		if(noDepthTest){
			GL11.glEnable(2929 /* GL_DEPTH_TEST */);
		}
		GL11.glEnable(3553 /* GL_TEXTURE_2D */);
		GL11.glEnable(2896 /* GL_LIGHTING */);
		GL11.glDisable(3042 /* GL_BLEND */);
		GL11.glPopMatrix();
	}
	
	public static void drawBlockESP(double x, double y, double z, float r, float g, float b, boolean noDepthTest) {
		EntityPlayerSP ep = Client.getClient().getPlayer();
		double d = ep.lastTickPosX + (ep.posX - ep.lastTickPosX) * (double) Client.getClient().getMinecraft().timer.renderPartialTicks;
		double d1 = ep.lastTickPosY + (ep.posY - ep.lastTickPosY) * (double) Client.getClient().getMinecraft().timer.renderPartialTicks;
		double d2 = ep.lastTickPosZ + (ep.posZ - ep.lastTickPosZ) * (double) Client.getClient().getMinecraft().timer.renderPartialTicks;
		double d3 = x - d;
		double d4 = y - d1;
		double d5 = z - d2;
		GL11.glPushMatrix();
		GL11.glEnable(3042 /* GL_BLEND */);
		GL11.glDisable(3553 /* GL_TEXTURE_2D */);
		GL11.glDisable(2896 /* GL_LIGHTING */);
		if(noDepthTest){
			GL11.glDisable(2929 /* GL_DEPTH_TEST */);
		}
		GL11.glDepthMask(false);
		GL11.glBlendFunc(770, 771);
		GL11.glEnable(2848 /* GL_LINE_SMOOTH */);
		GL11.glColor4f(r, g, b, 0.11F);
		drawBox(d3, d4, d5, d3 + 1, d4 + 1, d5 + 1);
		GL11.glColor4f(r, g, b, 0.15F);
		drawOutlinedBox(d3, d4, d5, d3 + 1, d4 + 1, d5 + 1, 1.6F);
		GL11.glDisable(2848 /* GL_LINE_SMOOTH */);
		GL11.glDepthMask(true);
		if(noDepthTest){
			GL11.glEnable(2929 /* GL_DEPTH_TEST */);
		}
		GL11.glEnable(3553 /* GL_TEXTURE_2D */);
		GL11.glEnable(2896 /* GL_LIGHTING */);
		GL11.glDisable(3042 /* GL_BLEND */);
		GL11.glPopMatrix();
	}
	
	public static void drawFloorESP(double x, double y, double z, float r, float g, float b, boolean noDepthTest, float xArea) {
		EntityPlayerSP ep = Client.getClient().getPlayer();
		double d = ep.lastTickPosX + (ep.posX - ep.lastTickPosX) * (double) Client.getClient().getMinecraft().timer.renderPartialTicks;
		double d1 = ep.lastTickPosY + (ep.posY - ep.lastTickPosY) * (double) Client.getClient().getMinecraft().timer.renderPartialTicks;
		double d2 = ep.lastTickPosZ + (ep.posZ - ep.lastTickPosZ) * (double) Client.getClient().getMinecraft().timer.renderPartialTicks;
		double d3 = x - d;
		double d4 = y - d1;
		double d5 = z - d2;
		GL11.glPushMatrix();
		GL11.glEnable(3042 /* GL_BLEND */);
		GL11.glDisable(3553 /* GL_TEXTURE_2D */);
		GL11.glDisable(2896 /* GL_LIGHTING */);
		if(noDepthTest){
			GL11.glDisable(2929 /* GL_DEPTH_TEST */);
		}
		GL11.glDepthMask(false);
		GL11.glBlendFunc(770, 771);
		GL11.glEnable(2848 /* GL_LINE_SMOOTH */);
		GL11.glColor4f(r, g, b, 0.2F);
		drawBox(d3, d4, d5, d3 + xArea*2, d4 + 0.1f, d5 + xArea*2);
		GL11.glColor4f(r, g, b, 0.2F);
		drawOutlinedBox(d3, d4, d5, d3 + xArea*2, d4 + 0.1f, d5 + xArea*2, 1.6F);
		GL11.glDisable(2848 /* GL_LINE_SMOOTH */);
		GL11.glDepthMask(true);
		if(noDepthTest){
			GL11.glEnable(2929 /* GL_DEPTH_TEST */);
		}
		GL11.glEnable(3553 /* GL_TEXTURE_2D */);
		GL11.glEnable(2896 /* GL_LIGHTING */);
		GL11.glDisable(3042 /* GL_BLEND */);
		GL11.glPopMatrix();
	}
	
	public static void drawBox(double x, double y, double z, double x2, double y2, double z2) {
		glBegin(GL_QUADS);
		glVertex3d(x, y, z);
		glVertex3d(x, y2, z);
		glVertex3d(x2, y, z);
		glVertex3d(x2, y2, z);
		glVertex3d(x2, y, z2);
		glVertex3d(x2, y2, z2);
		glVertex3d(x, y, z2);
		glVertex3d(x, y2, z2);
		glEnd();

		glBegin(GL_QUADS);
		glVertex3d(x2, y2, z);
		glVertex3d(x2, y, z);
		glVertex3d(x, y2, z);
		glVertex3d(x, y, z);
		glVertex3d(x, y2, z2);
		glVertex3d(x, y, z2);
		glVertex3d(x2, y2, z2);
		glVertex3d(x2, y, z2);
		glEnd();

		glBegin(GL_QUADS);
		glVertex3d(x, y2, z);
		glVertex3d(x2, y2, z);
		glVertex3d(x2, y2, z2);
		glVertex3d(x, y2, z2);
		glVertex3d(x, y2, z);
		glVertex3d(x, y2, z2);
		glVertex3d(x2, y2, z2);
		glVertex3d(x2, y2, z);
		glEnd();

		glBegin(GL_QUADS);
		glVertex3d(x, y, z);
		glVertex3d(x2, y, z);
		glVertex3d(x2, y, z2);
		glVertex3d(x, y, z2);
		glVertex3d(x, y, z);
		glVertex3d(x, y, z2);
		glVertex3d(x2, y, z2);
		glVertex3d(x2, y, z);
		glEnd();

		glBegin(GL_QUADS);
		glVertex3d(x, y, z);
		glVertex3d(x, y2, z);
		glVertex3d(x, y, z2);
		glVertex3d(x, y2, z2);
		glVertex3d(x2, y, z2);
		glVertex3d(x2, y2, z2);
		glVertex3d(x2, y, z);
		glVertex3d(x2, y2, z);
		glEnd();

		glBegin(GL_QUADS);
		glVertex3d(x, y2, z2);
		glVertex3d(x, y, z2);
		glVertex3d(x, y2, z);
		glVertex3d(x, y, z);
		glVertex3d(x2, y2, z);
		glVertex3d(x2, y, z);
		glVertex3d(x2, y2, z2);
		glVertex3d(x2, y, z2);
		glEnd();
	}

	public static void drawOutlinedBox(double x, double y, double z, double x2, double y2, double z2, float l1) {
		glLineWidth(l1);

		glBegin(GL_LINES);
		glVertex3d(x, y, z);
		glVertex3d(x, y2, z);
		glVertex3d(x2, y, z);
		glVertex3d(x2, y2, z);
		glVertex3d(x2, y, z2);
		glVertex3d(x2, y2, z2);
		glVertex3d(x, y, z2);
		glVertex3d(x, y2, z2);
		glEnd();

		glBegin(GL_LINES);
		glVertex3d(x2, y2, z);
		glVertex3d(x2, y, z);
		glVertex3d(x, y2, z);
		glVertex3d(x, y, z);
		glVertex3d(x, y2, z2);
		glVertex3d(x, y, z2);
		glVertex3d(x2, y2, z2);
		glVertex3d(x2, y, z2);
		glEnd();

		glBegin(GL_LINES);
		glVertex3d(x, y2, z);
		glVertex3d(x2, y2, z);
		glVertex3d(x2, y2, z2);
		glVertex3d(x, y2, z2);
		glVertex3d(x, y2, z);
		glVertex3d(x, y2, z2);
		glVertex3d(x2, y2, z2);
		glVertex3d(x2, y2, z);
		glEnd();

		glBegin(GL_LINES);
		glVertex3d(x, y, z);
		glVertex3d(x2, y, z);
		glVertex3d(x2, y, z2);
		glVertex3d(x, y, z2);
		glVertex3d(x, y, z);
		glVertex3d(x, y, z2);
		glVertex3d(x2, y, z2);
		glVertex3d(x2, y, z);
		glEnd();

		glBegin(GL_LINES);
		glVertex3d(x, y, z);
		glVertex3d(x, y2, z);
		glVertex3d(x, y, z2);
		glVertex3d(x, y2, z2);
		glVertex3d(x2, y, z2);
		glVertex3d(x2, y2, z2);
		glVertex3d(x2, y, z);
		glVertex3d(x2, y2, z);
		glEnd();

		glBegin(GL_LINES);
		glVertex3d(x, y2, z2);
		glVertex3d(x, y, z2);
		glVertex3d(x, y2, z);
		glVertex3d(x, y, z);
		glVertex3d(x2, y2, z);
		glVertex3d(x2, y, z);
		glVertex3d(x2, y2, z2);
		glVertex3d(x2, y, z2);
		glEnd();
	}
	
	public static double[] getRenderCoords(Entity ep){
		double d = ep.lastTickPosX + (ep.posX - ep.lastTickPosX) * Client.getClient().getMinecraft().timer.renderPartialTicks;
		double d1 = ep.lastTickPosY + (ep.posY - ep.lastTickPosY) * Client.getClient().getMinecraft().timer.renderPartialTicks;
		double d2 = ep.lastTickPosZ + (ep.posZ - ep.lastTickPosZ) * Client.getClient().getMinecraft().timer.renderPartialTicks;
		
		return new double[]{
				d - Client.getClient().getMinecraft().renderManager.renderPosX, d1 - Client.getClient().getMinecraft().renderManager.renderPosY, d2 - Client.getClient().getMinecraft().renderManager.renderPosZ,
		};
	}

	public static void drawPlayerESP(double d, double d1, double d2, Entity ep, double e, double f)
	{
		GL11.glPushMatrix();
		GL11.glEnable(3042);
		
		GL11.glColor4f(0.1F, 0.5F, 0.1F, 0.15F);
		
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(false);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glEnable(GL11.GL_LINE_SMOOTH);
		drawBoundingBox(new AxisAlignedBB(d - f, d1 + 0.1, d2 - f, d + f, d1 + e + 0.25, d2 + f));
		
		GL11.glColor4f(0F, 1F, 0.0F, 0.5F);
		GL11.glLineWidth(0.75F);
		drawOutlinedBoundingBox(new AxisAlignedBB(d - f, d1 + 0.1, d2 - f, d + f, d1 + e + 0.25, d2 + f));
		
		GL11.glDepthMask(true);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_LINE_SMOOTH);
		GL11.glDisable(3042);
		GL11.glPopMatrix();
	}
	
    public static void drawOutlinedBoundingBox(AxisAlignedBB par1AxisAlignedBB)
    {
        Tessellator var2 = Tessellator.getInstance();
        var2.getWorldRenderer().startDrawing(3);
        var2.getWorldRenderer().addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.minY, par1AxisAlignedBB.minZ);
        var2.getWorldRenderer().addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.minY, par1AxisAlignedBB.minZ);
        var2.getWorldRenderer().addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.minY, par1AxisAlignedBB.maxZ);
        var2.getWorldRenderer().addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.minY, par1AxisAlignedBB.maxZ);
        var2.getWorldRenderer().addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.minY, par1AxisAlignedBB.minZ);
        var2.draw();
        var2.getWorldRenderer().startDrawing(3);
        var2.getWorldRenderer().addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.minZ);
        var2.getWorldRenderer().addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.minZ);
        var2.getWorldRenderer().addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.maxZ);
        var2.getWorldRenderer().addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.maxZ);
        var2.getWorldRenderer().addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.minZ);
        var2.draw();
        var2.getWorldRenderer().startDrawing(1);
        var2.getWorldRenderer().addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.minY, par1AxisAlignedBB.minZ);
        var2.getWorldRenderer().addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.minZ);
        var2.getWorldRenderer().addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.minY, par1AxisAlignedBB.minZ);
        var2.getWorldRenderer().addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.minZ);
        var2.getWorldRenderer().addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.minY, par1AxisAlignedBB.maxZ);
        var2.getWorldRenderer().addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.maxZ);
        var2.getWorldRenderer().addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.minY, par1AxisAlignedBB.maxZ);
        var2.getWorldRenderer().addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.maxZ);
        var2.draw();
    }
    
    public static void drawBoundingBox(AxisAlignedBB axisalignedbb)
	{
		Tessellator tessellator = Tessellator.getInstance();
	    tessellator.getWorldRenderer().startDrawingQuads();
	    tessellator.getWorldRenderer().addVertex(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ);
	    tessellator.getWorldRenderer().addVertex(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ);
	    tessellator.getWorldRenderer().addVertex(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ);
	    tessellator.getWorldRenderer().addVertex(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ);
	    tessellator.getWorldRenderer().addVertex(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
	    tessellator.getWorldRenderer().addVertex(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
	    tessellator.getWorldRenderer().addVertex(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ);
	    tessellator.getWorldRenderer().addVertex(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ);
	    tessellator.draw();
	    tessellator.getWorldRenderer().startDrawingQuads();
	    tessellator.getWorldRenderer().addVertex(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ);
	    tessellator.getWorldRenderer().addVertex(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ);
	    tessellator.getWorldRenderer().addVertex(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ);
	    tessellator.getWorldRenderer().addVertex(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ);
	    tessellator.getWorldRenderer().addVertex(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ);
	    tessellator.getWorldRenderer().addVertex(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ);
	    tessellator.getWorldRenderer().addVertex(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
	    tessellator.getWorldRenderer().addVertex(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
	    tessellator.draw();
	    tessellator.getWorldRenderer().startDrawingQuads();
	    tessellator.getWorldRenderer().addVertex(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ);
	    tessellator.getWorldRenderer().addVertex(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ);
	    tessellator.getWorldRenderer().addVertex(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
	    tessellator.getWorldRenderer().addVertex(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ);
	    tessellator.getWorldRenderer().addVertex(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ);
	    tessellator.getWorldRenderer().addVertex(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ);
	    tessellator.getWorldRenderer().addVertex(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
	    tessellator.getWorldRenderer().addVertex(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ);
	    tessellator.draw();
	    tessellator.getWorldRenderer().startDrawingQuads();
	    tessellator.getWorldRenderer().addVertex(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ);
	    tessellator.getWorldRenderer().addVertex(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ);
	    tessellator.getWorldRenderer().addVertex(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
	    tessellator.getWorldRenderer().addVertex(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ);
	    tessellator.getWorldRenderer().addVertex(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ);
	    tessellator.getWorldRenderer().addVertex(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ);
	    tessellator.getWorldRenderer().addVertex(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
	    tessellator.getWorldRenderer().addVertex(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ);
	    tessellator.draw();
	    tessellator.getWorldRenderer().startDrawingQuads();
	    tessellator.getWorldRenderer().addVertex(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ);
	    tessellator.getWorldRenderer().addVertex(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ);
	    tessellator.getWorldRenderer().addVertex(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ);
	    tessellator.getWorldRenderer().addVertex(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ);
	    tessellator.getWorldRenderer().addVertex(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
	    tessellator.getWorldRenderer().addVertex(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
	    tessellator.getWorldRenderer().addVertex(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ);
	    tessellator.getWorldRenderer().addVertex(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ);
	    tessellator.draw();
	    tessellator.getWorldRenderer().startDrawingQuads();
	    tessellator.getWorldRenderer().addVertex(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ);
	    tessellator.getWorldRenderer().addVertex(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ);
	    tessellator.getWorldRenderer().addVertex(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ);
	    tessellator.getWorldRenderer().addVertex(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ);
	    tessellator.getWorldRenderer().addVertex(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ);
	    tessellator.getWorldRenderer().addVertex(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ);
	    tessellator.getWorldRenderer().addVertex(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
	    tessellator.getWorldRenderer().addVertex(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
	    tessellator.draw();
	}
}
