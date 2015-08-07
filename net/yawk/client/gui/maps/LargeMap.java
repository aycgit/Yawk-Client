package net.yawk.client.gui.maps;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import com.darkmagician6.eventapi.EventTarget;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.init.Blocks;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.util.BlockPos;
import net.yawk.client.Client;
import net.yawk.client.events.EventRecievePacket;
import net.yawk.client.gui.hub.ColourModifier;
import net.yawk.client.modmanager.EventListener;
import net.yawk.client.utils.ClientUtils;
import net.yawk.client.utils.Colours;
import net.yawk.client.utils.GuiUtils;
import net.yawk.client.utils.Scissor;

public class LargeMap {
	
	private int width = 170, height = 170;
	private double lastX, lastZ;
	private Minecraft mc;
	private ColourModifier colourModifier;
	private EventListener listener;
	private Map<String,Integer> factionColours;
	private BiMap<String,List<ChunkData>> factionChunkMap;
	private int vID = -1, fontRendererID;
	private boolean showChunks, cavefinder, factions, changed;
	
	public LargeMap(ColourModifier colourModifier){
		
		this.mc = Client.getClient().getMinecraft();
		this.colourModifier = colourModifier;
		factionColours = new HashMap<String,Integer>();
		factionChunkMap = HashBiMap.create();
		createFactionListener();
		
		fontRendererID =  mc.getTextureManager().getTexture(mc.fontRendererObj.locationFontTexture).getGlTextureId();
	}
	
	public void draw(int x, int y, double scale){
		
		//GuiUtils.drawRect(x-5, y-5, x+5, y+5, 0x5FFF0000);
		
		if(changed || mc.thePlayer.getDistance(lastX, mc.thePlayer.posY, lastZ) > 1){
			
			if(vID != -1){
				glDeleteTextures(vID);
			}
			
			vID = getTexture();
			
			lastX = mc.thePlayer.posX;
			lastZ = mc.thePlayer.posZ;
			
			changed = false;
		}
		
		float rot = mc.thePlayer.rotationYaw+90;
		//double distX = mc.thePlayer.posX - lastX;
		//double distZ = mc.thePlayer.posZ - lastZ;
		
		//Scissor.enableScissoring();
		//Scissor.scissor(width/2 - 50, height/2 - 50, 100, 100);
		
		//System.out.println(mc.thePlayer.posX - lastX);
		
		glTranslated(x, y, 0);
		//glRotatef(-rot, 0, 0, 1);
		glScaled(scale, scale, scale);
		
		bind();
		
		glColor4f(1, 1, 1, 1);
		glClear(256);
		
		GuiUtils.drawTextureRect(-50, -50, 50, 50);
		
		unbind();
		
		glScaled(1/scale, 1/scale, 1/scale);
				
		GuiUtils.drawSmallTriangle(0, 0, rot, 0xFFFF0000);
		
		//glRotatef(rot, 0, 0, 1);
		glTranslated(-x, -y, 0);
		
		//Scissor.disableScissoring();
		
		glBindTexture(GL_TEXTURE_2D, fontRendererID);
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
					
					if(showChunks && (xPos % 16 == 0 || zPos % 16 == 0)){
						pixel = colourModifier.getDarkColour(pixel);
					}else if (factions){
						
						String faction = getChunkOwner(xPos, zPos);
						
						if(faction != null){
							pixel = colourModifier.getMergedColour(pixel, factionColours.get(faction));
						}
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
	
	private void createFactionListener(){
		
		listener = new EventListener(){
			
			@EventTarget
			public void onPacketRecieve(EventRecievePacket e){
				
				if(e.packet instanceof S02PacketChat){
					
					String msg = ((S02PacketChat)e.packet).func_148915_c().getUnformattedText();
					
					if(msg.startsWith(" ~ ")){
						
						try{
							
							String faction = msg.split(" ~ ")[1].split(" ")[0];
							
							int chunkX = ClientUtils.roundTo16((int)Client.getClient().getPlayer().posX);
							int chunkZ = ClientUtils.roundTo16((int)Client.getClient().getPlayer().posZ);
							
							ChunkData chunk = new ChunkData(chunkX, chunkZ);
							
							if(!factionColours.containsKey(faction)){
								factionColours.put(faction, getNewFactionColour());
							}
							
							List<ChunkData> chunks = factionChunkMap.get(faction);
							
							if(chunks == null){
								chunks = new ArrayList<ChunkData>();
								chunks.add(chunk);
								factionChunkMap.put(faction, chunks);
							}
							
							if(!chunks.contains(chunk)){
								chunks.add(chunk);
							}
							
						}catch(Exception ex){
							ex.printStackTrace();
						}
					}
				}
			}			
		};
	}
	
	private int getNewFactionColour(){
		return Colours.brightColours[factionColours.size() % 10];
	}
	
	private String getChunkOwner(int x, int z){
		
		for(String faction : factionChunkMap.keySet()){
			
			List<ChunkData> chunks = factionChunkMap.get(faction);
			
			for(ChunkData chunk : chunks){
				if(chunk.containsPoint(x, z)){
					return faction;
				}
			}
		}
		
		return null;
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
		if(cavefinder){
			return getCaveFinderTopBlock(x, z);
		}else{
			return getNormalTopBlock(x, z);
		}
	}

	private BlockPos getNormalTopBlock(int x, int z){
		
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
	
	private BlockPos getCaveFinderTopBlock(int x, int z){
		
		int playerY = (int)mc.thePlayer.posY;
		BlockPos pos = null;
		boolean wasBlock = false;
		
		for(int y = -30; y < 30; y++){
			
			pos = new BlockPos(x, playerY+y, z);
			IBlockState state = mc.theWorld.getBlockState(pos);
			
			if(state.getBlock() != Blocks.air){
				wasBlock = true;
			}else if(wasBlock){
				return pos.offsetDown(1);
			}else{
				wasBlock = false;
			}
		}
		
		return null;
	}
	
	public void registerFactionsListener(){
		listener.register();
	}
	
	public void unregisterFactionsListener(){
		listener.unregister();
	}
	
	public void setShowFactions(boolean factions){
		
		if(factions != this.factions){
			this.changed = true;
			this.factions = factions;
			
			if(factions){
				registerFactionsListener();
			}else{
				unregisterFactionsListener();
			}
		}
	}
	
	public boolean isShowChunks() {
		return showChunks;
	}

	public void setShowChunks(boolean showChunks) {
		
		if(showChunks != this.showChunks){
			this.changed = true;
			this.showChunks = showChunks;
		}
		
	}
	
	public boolean isCavefinder() {
		return cavefinder;
	}

	public void setCavefinder(boolean cavefinder) {
		
		if(cavefinder != this.cavefinder){
			this.changed = true;
			this.cavefinder = cavefinder;
		}
		
	}
}
