package net.yawk.client.cameras;

import java.nio.IntBuffer;

import org.lwjgl.opengl.ARBFramebufferObject;

import static org.lwjgl.opengl.GL11.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.util.MovingObjectPosition;
import net.yawk.client.Client;
import net.yawk.client.utils.GuiUtils;

public class Camera {
	
	private static boolean capturing;
	
	//Taken from Framebuffer.java
	//These won't need updating
	protected int framebufferTextureWidth = 360, framebufferTextureHeight = 200, framebufferObject, framebufferTexture, depthBuffer;
	
	public float cameraRotationYaw, cameraRotationPitch;
    public double cameraPosX, cameraPosY, cameraPosZ;
    
	private Minecraft mc;
	
    public Camera(){
    	mc = Minecraft.getMinecraft();
    	createFrameBuffer();
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
    	if(capturing){
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
        
    	//Notes to self when updating this to a new minecraft version
    	//This code is taken from Minecraft.java in the method func_180510_a on line 889
    	
        GlStateManager.matrixMode(5889);
        GlStateManager.loadIdentity();
        GlStateManager.ortho(0.0D, mc.displayWidth, mc.displayHeight, 0.0D, 1000.0D, 3000.0D);
        GlStateManager.matrixMode(5888);
        GlStateManager.loadIdentity();
        GlStateManager.translate(0.0F, 0.0F, -2000.0F);
        GlStateManager.disableLighting();
        GlStateManager.disableFog();
        GlStateManager.disableDepth();
        GlStateManager.func_179098_w();
        
        glColor4f(1f, 1f, 1f, 1f);
        glBindTexture(GL_TEXTURE_2D, framebufferTexture);
        
    	GuiUtils.drawFlippedTexturedModalRect(x*GuiUtils.scaleManager.getScaleFactor(), y*GuiUtils.scaleManager.getScaleFactor(), x1*GuiUtils.scaleManager.getScaleFactor(), y1*GuiUtils.scaleManager.getScaleFactor());
    	
    	//TODO: rebind font texture
    	
    	//Notes to self when updating this to a new minecraft version
    	//This code is taken from Minecraft.java in the method func_180510_a on line 922
    	//Unneeded or unwanted code has been commented out
    	
    	Tessellator var6 = Tessellator.getInstance();
        WorldRenderer var7 = var6.getWorldRenderer();
        
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        var7.func_178991_c(16777215);
        //short var8 = 256;
        //short var9 = 256;
        //this.scaledTessellator((mc.displayWidth - var8) / 2, (mc.displayHeight - var9) / 2, 0, 0, var8, var9);
        GlStateManager.disableLighting();
        GlStateManager.disableFog();
        //var4.unbindFramebuffer();
        //var4.framebufferRender(var2.getScaledWidth() * var3, var2.getScaledHeight() * var3);
        GlStateManager.enableAlpha();
        GlStateManager.alphaFunc(516, 0.1F);
    }
    
    public static boolean isCapturing(){
		return capturing;
	}

	public static void setCapturing(boolean capturing){
		Camera.capturing = capturing;
	}
}
