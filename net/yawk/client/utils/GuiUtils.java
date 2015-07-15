package net.yawk.client.utils;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;

import org.lwjgl.opengl.GL11;

public class GuiUtils {
	
	public static void drawRect(double paramXStart, double paramYStart, double paramXEnd, double paramYEnd, int paramColor)
	{
		float alpha = (float)(paramColor >> 24 & 0xFF) / 255F;
		float red = (float)(paramColor >> 16 & 0xFF) / 255F;
		float green = (float)(paramColor >> 8 & 0xFF) / 255F;
		float blue = (float)(paramColor & 0xFF) / 255F;
		
		GL11.glClear(256);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glEnable(GL11.GL_LINE_SMOOTH);
		
		GL11.glPushMatrix();
		GL11.glColor4f(red, green, blue, alpha);
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2d(paramXEnd, paramYStart);
		GL11.glVertex2d(paramXStart, paramYStart);
		GL11.glVertex2d(paramXStart, paramYEnd);
		GL11.glVertex2d(paramXEnd, paramYEnd);
		GL11.glEnd();
		GL11.glPopMatrix();
		
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_LINE_SMOOTH);
		
		//Added by Yaweh
		GL11.glColor4f(1, 1, 1, 1);
		GL11.glClear(256);
	}
	
	public static void drawNodusBorder(int x, int y, int x1, int y1) {
		drawBorder(x, y, x1, y1, 2, 0x5FFFFFFF);
	}
	
	public static void drawTopNodusRect(int x, int y, int x1, int y1) {
		//GuiUtils.drawRect(par1 - 2.0F, par2 - 2.0F, par3 + 2.0F, par4 /*+ 2.0F*/, 0x5FFFFFFF);
		
		GuiUtils.drawBorderedRect(x, y, x1, y1, 2, 0x5FFFFFFF, 0xFF001000);
	}
	
	public static void drawBottomNodusRect(float x, float y, float x1, float y1) {		
		//GuiUtils.drawRect(par1 - 2.0F, par2 /*- 2.0F*/, par3 + 2.0F, par4 + 2.0F, 0x5FFFFFFF);
		GuiUtils.drawRect(x, y, x1, y1, 0x9F000000);
		
		int lw = 2;
		
		//Cuts off the top border
		//GuiUtils.drawRect(x, y - lw, x1, y, 0x5FFFFFFF);
		GuiUtils.drawRect(x, y1, x1, y1 + lw, 0x5FFFFFFF);
		
		GuiUtils.drawRect(x - lw, y, x, y1 + lw, 0x5FFFFFFF);
		GuiUtils.drawRect(x1, y, x1 + lw, y1 + lw, 0x5FFFFFFF);
	}
	
	public static void drawBorder(int x, int y, int x1, int y1, int lw, int borderC){
		GuiUtils.drawRect(x, y - lw, x1, y, 0x5FFFFFFF);
		GuiUtils.drawRect(x, y1, x1, y1 + lw, 0x5FFFFFFF);
		
		GuiUtils.drawRect(x - lw, y - lw, x, y1 + lw, 0x5FFFFFFF);
		GuiUtils.drawRect(x1, y - lw, x1 + lw, y1 + lw, 0x5FFFFFFF);
	}
	
	public static void drawBorderedRect(int x, int y, int x1, int y1, int lw, int borderC, int insideC){
		GuiUtils.drawRect(x, y - lw, x1, y, borderC);
		GuiUtils.drawRect(x, y1, x1, y1 + lw, borderC);
		
		GuiUtils.drawRect(x - lw, y - lw, x, y1 + lw, borderC);
		GuiUtils.drawRect(x1, y - lw, x1 + lw, y1 + lw, borderC);
		
		GuiUtils.drawRect(x, y, x1, y1, insideC);
	}
	
    public static void drawCorrectTexturedModalRect(double x, double y, double x1, double y1)
    {
        Tessellator tes = Tessellator.getInstance();
        WorldRenderer wr = tes.getWorldRenderer();
        
        wr.startDrawingQuads();
        wr.addVertexWithUV(x, y1, 0, 0, 1);
        wr.addVertexWithUV(x1, y1, 0, 1, 1);
        wr.addVertexWithUV(x1, y, 0, 1, 0);
        wr.addVertexWithUV(x, y, 0, 0, 0);
        tes.draw();
    }
}
