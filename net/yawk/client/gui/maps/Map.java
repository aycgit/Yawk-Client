package net.yawk.client.gui.maps;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
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
import net.yawk.client.gui.hub.ColourModifier;
import net.yawk.client.utils.GuiUtils;
import net.yawk.client.utils.Scissor;

public class Map {
	
	private int vID = -1, width = 170, height = 170;
	private double lastX, lastY, lastZ;
	private Minecraft mc;
	private ColourModifier colourModifier;
	
	public Map(ColourModifier colourModifier){
		this.mc = Client.getClient().getMinecraft();
		this.colourModifier = colourModifier;
	}
	
	public void draw(int x, int y){
		
		//GuiUtils.drawRect(x-5, y-5, x+5, y+5, 0x5FFF0000);
		
		if(mc.thePlayer.getDistance(lastX, lastY, lastZ) > 5){
			
			if(vID != -1){
				glDeleteTextures(vID);
			}
			
			vID = getTexture();
			
			lastX = mc.thePlayer.posX;
			lastY = mc.thePlayer.posY;
			lastZ = mc.thePlayer.posZ;
		}
		
		float rot = mc.thePlayer.rotationYaw+90;
		//double distX = mc.thePlayer.posX - lastX;
		//double distZ = mc.thePlayer.posZ - lastZ;
		
		//Scissor.enableScissoring();
		//Scissor.scissor(width/2 - 50, height/2 - 50, 100, 100);
		
		//System.out.println(mc.thePlayer.posX - lastX);
		
		glTranslated(x, y, 0);
		//glRotatef(-rot, 0, 0, 1);
		glScalef(2, 2, 2);
		
		bind();
		
		glColor4f(1, 1, 1, 1);
		glClear(256);
		
		GuiUtils.drawTextureRect(-width/2, -height/2, width/2, height/2);
		
		unbind();
		
		glScalef(0.5f, 0.5f, 0.5f);
		
		GuiUtils.drawSmallTriangle(0, 0, rot, 0xFFFF0000);
		
		//glRotatef(rot, 0, 0, 1);
		glTranslated(-x, -y, 0);
		
		//Scissor.disableScissoring();
	}
	
	private int getTexture(){
		
		ByteBuffer buffer = BufferUtils.createByteBuffer(width * height * 3);
		
		int playerX = (int) mc.thePlayer.posX-width/2;
		int playerZ = (int) mc.thePlayer.posZ-height/2;
		
		for(int x = 0; x < width; x++){
			for(int z = 0; z < height; z++){

				int pixel = 0;

				int xPos = playerX+x;
				int zPos = playerZ+z;
				
				BlockPos pos = getTopBlock(playerX+x, playerZ+z);
				
				if(pos != null){
					
					IBlockState top = mc.theWorld.getBlockState(pos);
					pixel = top.getBlock().getMaterial().getMaterialMapColor().colorValue;
					
					if(xPos % 16 == 0 || zPos % 16 == 0){
						pixel = colourModifier.getDarkColour(pixel);
					}
					
				}else{
					pixel = 0xFFB0B0B0;
				}
				
				buffer.put((byte) (pixel >> 16 & 0xFF)); //r
				buffer.put((byte) (pixel >> 8 & 0xFF)); //g
				buffer.put((byte) (pixel & 0xFF)); //b
			}
		}
		
		buffer.flip();
		
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
	
	private BlockPos getTopBlock(int x, int z){
		
		int playerY = (int)mc.thePlayer.posY;
		
		BlockPos pos = null;
		
		for(int y = 80; y > 0; y--){
			
			pos = new BlockPos(x, playerY+y-40, z);
			IBlockState state = mc.theWorld.getBlockState(pos);
			
			if(state.getBlock() != Blocks.air){
				return pos;
			}
		}
		
		return null;
	}
}
