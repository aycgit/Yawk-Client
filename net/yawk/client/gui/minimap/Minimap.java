package net.yawk.client.gui.minimap;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;
import java.util.HashMap;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.yawk.client.Client;
import net.yawk.client.utils.GuiUtils;
import net.yawk.client.utils.Scissor;

public class Minimap {
	
	private int vID = -1;
	
	private int width = 140, height = 140;
	
	private double lastX, lastY, lastZ;
	
	private Minecraft mc;
	
	public Minimap(){
		mc = Minecraft.getMinecraft();
	}
	
	public void draw(){
		
		if(mc.thePlayer.getDistance(lastX, lastY, lastZ) > 5f){
			
			if(vID != -1){
				glDeleteTextures(vID);
			}
			
			vID = getTexture();
			
			lastX = mc.thePlayer.posX;
			lastY = mc.thePlayer.posY;
			lastZ = mc.thePlayer.posZ;
		}
		
		float rot = mc.thePlayer.rotationYaw+90;
		
		Scissor.enableScissoring();
		Scissor.scissor(width/2 - 50, height/2 - 50, 100, 100);
		
		//System.out.println(mc.thePlayer.posX - lastX);
		
		double x = height/2;
		double z = width/2;
		
		glTranslated(x, z, 0);
		glRotatef(-rot, 0, 0, 1);
		bind();
		
		glColor4f(1, 1, 1, 1);
		glClear(256);
		
		//GL11.glStencilOp(7680, 7680, 7680);
		//GL11.glStencilFunc(514, 1, -1);
		
		GuiUtils.drawTextureRect(-width/2, -height/2, width/2, height/2);
		
		unbind();
		glRotatef(rot, 0, 0, 1);
		glTranslated(-x, -z, 0);
		
		Scissor.disableScissoring();
		
		GuiUtils.drawSmallTriangle(width/2, height/2, 0, 0xFFFF0000);
		
		//glDisable(GL_STENCIL_TEST);
		
		//TODO: FIX TEXT RENDERING AFTER DRAWING MINIMAP
		Client.getClient().getMinecraft().renderEngine.bindTexture(Client.getClient().getFontRenderer().locationFontTexture);
	}
	
	private int getTexture(){
		
		ByteBuffer buffer = BufferUtils.createByteBuffer(width * height * 3);
		
		int playerX = (int) mc.thePlayer.posX-width/2;
		int playerZ = (int) mc.thePlayer.posZ-height/2;
		
		for(int x = 0; x < width; x++){
			for(int z = 0; z < height; z++){
				
				IBlockState top = getTopBlock(playerX+x, playerZ+z);
				
				int pixel = top != null? top.getBlock().getMaterial().getMaterialMapColor().colorValue:0xFF000000;
				
				buffer.put((byte) (pixel >> 16 & 0xFF)); //r
				buffer.put((byte) (pixel >> 8 & 0xFF)); //g
				buffer.put((byte) (pixel & 0xFF)); //b
			}
		}
		
		buffer.flip(); //Buffer limit set to the current position, uses less memory
		
		int id = glGenTextures();
		
		glBindTexture(GL_TEXTURE_2D, id);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, width, height, 0, GL_RGB, GL_UNSIGNED_BYTE, buffer);
		
		return id;
	}
	
	public void bind(){
		
		glEnable(GL_TEXTURE_2D);
		
	    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
	    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
		
		glBindTexture(GL_TEXTURE_2D, vID);
	}
	
	public void unbind(){
		glBindTexture(GL_TEXTURE_2D, 0);
	}
	
	private IBlockState getTopBlock(int x, int z){
		
		int playerY = (int)mc.thePlayer.posY;
		
		IBlockState state = null;
		
		for(int y = 80; y > 0; y--){
			
			state = mc.theWorld.getBlockState(new BlockPos(x, playerY+y-40, z));
			
			if(state.getBlock() != Blocks.air){
				return state;
			}
		}
		
		return null;
	}
}
