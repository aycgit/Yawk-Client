package net.yawk.client.cameras;

import static org.lwjgl.opengl.GL11.*;
import io.netty.buffer.Unpooled;

import java.nio.IntBuffer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.entity.Entity;
import net.yawk.client.Client;
import net.yawk.client.gui.Window;
import net.yawk.client.utils.GuiUtils;

import org.lwjgl.opengl.ARBFramebufferObject;
import org.lwjgl.opengl.GL11;

public class Camera {
	
	private static boolean capturing;
	private static int fontRendererID;
	
	private int width = 360, height = 200;
	private Framebuffer frameBuffer;
	private Window displayedWindow;
	
	public float cameraRotationYaw, cameraRotationPitch;
    public double cameraPosX, cameraPosY, cameraPosZ;
    private boolean frameBufferUpdated, reflected;
    
	private Minecraft mc;
	
	public Camera(){
		this(null, false);
	}
	
	public Camera(Window displayedWindow){
		this(displayedWindow, false);
	}
	
    public Camera(Window displayedWindow, boolean reflected){
    	
    	this.reflected = reflected;
    	this.displayedWindow = displayedWindow;
    	
    	mc = Minecraft.getMinecraft();
    	
    	frameBuffer = new Framebuffer(width, height, true);
    	makeNewFrameBuffer();
    	
    	Client.getClient().registerCamera(this);
    	
    	if(fontRendererID == 0){
    		fontRendererID =  mc.getTextureManager().getTexture(mc.fontRendererObj.locationFontTexture).getGlTextureId();
    	}
    }
    
    protected void setCapture(boolean capture){
    	
    	if(capture){
    		frameBuffer.bindFramebuffer(true);
    	}else{
    		frameBuffer.unbindFramebuffer();
    	}
    	
    	capturing = capture;
    }
    
    public void updateFramebuffer(){
    	
    	//So we don't make a loop of rendering the cameras
    	//TODO: make this work when the game isn't in focus
    	if(capturing || !mc.inGameHasFocus || !isCameraVisible()){
    		return;
    	}
    	
    	//Saves the player's current position and game settings
    	
    	double posX, posY, posZ, prevPosX, prevPosY, prevPosZ, lastTickPosX, lastTickPosY, lastTickPosZ;
    	int displayWidth, displayHeight, thirdPersonView;
    	float rotationYaw, rotationPitch, prevRotationYaw, prevRotationPitch;
    	boolean hideGUI, viewBobbing;
    	
    	displayWidth = mc.displayWidth;
    	displayHeight = mc.displayHeight;
    	rotationYaw = mc.renderViewEntity.rotationYaw;
    	prevRotationYaw = mc.renderViewEntity.prevRotationYaw;
    	rotationPitch = mc.renderViewEntity.rotationPitch;
    	prevRotationPitch = mc.renderViewEntity.prevRotationPitch;
    	hideGUI = mc.gameSettings.hideGUI;
    	thirdPersonView = mc.gameSettings.thirdPersonView;
    	viewBobbing = mc.gameSettings.viewBobbing;
    	
    	posX = mc.renderViewEntity.posX;
    	prevPosX = mc.renderViewEntity.prevPosX;
    	lastTickPosX = mc.renderViewEntity.lastTickPosX;
    	
    	posY = mc.renderViewEntity.posY;
    	prevPosY = mc.renderViewEntity.prevPosY;
    	lastTickPosY = mc.renderViewEntity.lastTickPosY;
    	
    	posZ = mc.renderViewEntity.posZ;
    	prevPosZ = mc.renderViewEntity.prevPosZ;
    	lastTickPosZ = mc.renderViewEntity.lastTickPosZ;
    	
    	//Sets the player's position to the camera position
    	
    	mc.renderViewEntity.posX = cameraPosX;
    	mc.renderViewEntity.prevPosX = cameraPosX;
    	mc.renderViewEntity.lastTickPosX = cameraPosX;
    	
    	mc.renderViewEntity.posY = cameraPosY;
    	mc.renderViewEntity.prevPosY = cameraPosY;
    	mc.renderViewEntity.lastTickPosY = cameraPosY;
    	
    	mc.renderViewEntity.posZ = cameraPosZ;
    	mc.renderViewEntity.prevPosZ = cameraPosZ;
    	mc.renderViewEntity.lastTickPosZ = cameraPosZ;
    	
    	mc.displayWidth = width;
    	mc.displayHeight = height;
    	mc.renderViewEntity.rotationYaw = cameraRotationYaw;
    	mc.renderViewEntity.prevRotationYaw = cameraRotationYaw;
    	mc.renderViewEntity.rotationPitch = cameraRotationPitch;
    	mc.renderViewEntity.prevRotationPitch = cameraRotationPitch;
    	mc.gameSettings.thirdPersonView = 0;
    	mc.gameSettings.viewBobbing = false;
    	mc.gameSettings.hideGUI = true;
    	
    	setCapture(true);
    	
    	mc.entityRenderer.updateCameraAndRender(mc.timer.renderPartialTicks);
    	
    	setCapture(false);
    	
    	//Sets the player's position back to the saved position and reverses the game settings changes
    	
    	mc.displayWidth = displayWidth;
    	mc.displayHeight = displayHeight;
    	mc.renderViewEntity.rotationYaw = rotationYaw;
    	mc.renderViewEntity.prevRotationYaw = prevRotationYaw;
    	mc.renderViewEntity.rotationPitch = rotationPitch;
    	mc.renderViewEntity.prevRotationPitch = prevRotationPitch;
    	mc.gameSettings.thirdPersonView = thirdPersonView;
    	mc.gameSettings.hideGUI = hideGUI;
    	mc.gameSettings.viewBobbing = viewBobbing;
    	
    	mc.renderViewEntity.posX = posX;
    	mc.renderViewEntity.prevPosX = prevPosX;
    	mc.renderViewEntity.lastTickPosX = lastTickPosX;
    	
    	mc.renderViewEntity.posY = posY;
    	mc.renderViewEntity.prevPosY = prevPosY;
    	mc.renderViewEntity.lastTickPosY = lastTickPosY;
    	
    	mc.renderViewEntity.posZ = posZ;
    	mc.renderViewEntity.prevPosZ = prevPosZ;
    	mc.renderViewEntity.lastTickPosZ = lastTickPosZ;
    	
    	frameBufferUpdated = true;
    }
    
    private boolean isCameraVisible(){
    	return displayedWindow != null && displayedWindow.pinned && displayedWindow.extended;
    }
    
    public void draw(double x, double y, double x1, double y1){
    	
    	//Note to self for updating
    	//Taken from Framebuffer.java in method func_178038_a on line 234
        GlStateManager.func_179098_w();
        GlStateManager.disableLighting();
        GlStateManager.disableAlpha();
        
        GlStateManager.disableBlend();
        GlStateManager.enableColorMaterial();
        
        glColor4f(1f, 1f, 1f, 1f);
        frameBuffer.bindFramebufferTexture();
        
        if(reflected){
        	GuiUtils.drawReflectedTexturedRect(x, y, x1, y1);
        }else{
        	GuiUtils.drawFlippedTexturedModalRect(x, y, x1, y1);
        }
        
    	frameBuffer.unbindFramebufferTexture();
    }
    
    protected void setToEntityPosition(Entity e){
		cameraPosX = e.lastTickPosX - (e.lastTickPosX-e.posX)*mc.timer.elapsedPartialTicks;
		cameraPosY = e.lastTickPosY - (e.lastTickPosY-e.posY)*mc.timer.elapsedPartialTicks;
		cameraPosZ = e.lastTickPosZ - (e.lastTickPosZ-e.posZ)*mc.timer.elapsedPartialTicks;
    }
    
    protected void setToEntityPositionAndRotation(Entity e){
    	setToEntityPosition(e);
		cameraRotationYaw = e.rotationYaw;
		cameraRotationPitch = e.rotationPitch;
    }
    
    public void makeNewFrameBuffer(){
    	frameBuffer.createFramebuffer(width, height);
    }
    
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
    	frameBufferUpdated = false;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
    	frameBufferUpdated = false;
	}
	
	public boolean isFrameBufferUpdated() {
		return frameBufferUpdated;
	}
	
    public Camera setReflected(boolean reflected){
    	this.reflected = reflected;
    	return this;
    }
    
    public static boolean isCapturing(){
		return capturing;
	}

	public static void setCapturing(boolean capturing){
		Camera.capturing = capturing;
	}
}
