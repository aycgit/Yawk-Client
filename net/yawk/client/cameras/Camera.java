package net.yawk.client.cameras;

import static org.lwjgl.opengl.GL11.*;

import java.nio.IntBuffer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.yawk.client.utils.GuiUtils;

import org.lwjgl.opengl.ARBFramebufferObject;

public class Camera {
	
	private static boolean capturing;
	private static int fontRendererID;
	
	//Taken from Framebuffer.java
	//These won't need updating
	protected int framebufferTextureWidth = 360, framebufferTextureHeight = 200, framebufferObject, framebufferTexture, depthBuffer;
	
	public float cameraRotationYaw, cameraRotationPitch;
    public double cameraPosX, cameraPosY, cameraPosZ;
    
	private Minecraft mc;
	
    public Camera(){
    	mc = Minecraft.getMinecraft();
    	createFrameBuffer();
    	
    	if(fontRendererID == 0){
    		fontRendererID =  mc.getTextureManager().getTexture(mc.fontRendererObj.locationFontTexture).getGlTextureId();
    	}
    }
    
    private void createFrameBuffer(){
    	
    	//Notes to self when updating this to a new minecraft version
    	//This code is taken from Framebuffer.java in method createFramebuffer on line 101
        this.framebufferObject = OpenGlHelper.func_153165_e();
        this.framebufferTexture = TextureUtil.glGenTextures();
        this.depthBuffer = OpenGlHelper.func_153185_f();
        
        //TODO: Use the mc code for the Framebuffer
        glBindTexture(GL_TEXTURE_2D, framebufferTexture);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB8, framebufferTextureWidth, framebufferTextureHeight, 0, GL_RGBA, GL_INT,
                (IntBuffer) null);
        
        glBindTexture(GL_TEXTURE_2D, depthBuffer);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glTexImage2D(GL_TEXTURE_2D, 0, GL_DEPTH_COMPONENT, framebufferTextureWidth, framebufferTextureHeight, 0, GL_DEPTH_COMPONENT,
                GL_INT, (IntBuffer) null);
        
        glBindTexture(GL_TEXTURE_2D, 0);
    }
    
    protected void setCapture(boolean capture){
    	
    	if(capture){
    		
            ARBFramebufferObject.glBindFramebuffer(ARBFramebufferObject.GL_DRAW_FRAMEBUFFER, framebufferObject);
            
            ARBFramebufferObject.glFramebufferTexture2D(ARBFramebufferObject.GL_DRAW_FRAMEBUFFER,
                    ARBFramebufferObject.GL_COLOR_ATTACHMENT0, GL_TEXTURE_2D,
                    framebufferTexture, 0);
            
            ARBFramebufferObject.glFramebufferTexture2D(ARBFramebufferObject.GL_DRAW_FRAMEBUFFER,
                    ARBFramebufferObject.GL_DEPTH_ATTACHMENT, GL_TEXTURE_2D,
                    depthBuffer, 0);
    	}else{
            ARBFramebufferObject.glBindFramebuffer(ARBFramebufferObject.GL_DRAW_FRAMEBUFFER, 0);
    	}
    	
    	capturing = capture;
    }
    
    public void updateFramebuffer(){
    	
    	//So we don't make a loop of rendering the cameras
    	//TODO: make this work when the game isn't in focus
    	if(capturing || !mc.inGameHasFocus){
    		return;
    	}
    	
    	//Saves the player's current position and game settings
    	
    	double posX, posY, posZ, prevPosX, prevPosY, prevPosZ, lastTickPosX, lastTickPosY, lastTickPosZ;
    	int displayWidth, displayHeight, thirdPersonView;
    	float rotationYaw, rotationPitch, prevRotationYaw, prevRotationPitch, fovSetting;
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
    	fovSetting = mc.gameSettings.fovSetting;
    	
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
    	
    	mc.displayWidth = framebufferTextureWidth;
    	mc.displayHeight = framebufferTextureHeight;
    	mc.renderViewEntity.rotationYaw = cameraRotationYaw;
    	mc.renderViewEntity.prevRotationYaw = cameraRotationYaw;
    	mc.renderViewEntity.rotationPitch = cameraRotationPitch;
    	mc.renderViewEntity.prevRotationPitch = cameraRotationPitch;
    	mc.gameSettings.thirdPersonView = 0;
    	mc.gameSettings.viewBobbing = false;
    	mc.gameSettings.hideGUI = true;
    	mc.gameSettings.fovSetting = 90;
    	
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
    }
    
    public void draw(double x, double y, double x1, double y1){
        
        glColor4f(1f, 1f, 1f, 1f);
        glBindTexture(GL_TEXTURE_2D, framebufferTexture);
        
    	GuiUtils.drawFlippedTexturedModalRect(x, y, x1, y1);
    	
    	//Done so that the text rendered after the cameras will have the right texture
		glBindTexture(GL_TEXTURE_2D, fontRendererID);
    }
    
    public static boolean isCapturing(){
		return capturing;
	}

	public static void setCapturing(boolean capturing){
		Camera.capturing = capturing;
	}
}
