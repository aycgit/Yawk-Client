package net.yawk.client.mods;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.yawk.client.Client;
import net.yawk.client.events.EventKeyPress;
import net.yawk.client.events.EventRender;
import net.yawk.client.events.EventTick;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.ModType;
import net.yawk.client.utils.ESPUtils;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import com.darkmagician6.eventapi.EventTarget;

public class Parkour implements Mod{
	
	public Parkour(){
		
	}
	
	@Override
	public String getName() {
		return "JumpPredict";
	}
	
	@Override
	public String getDescription() {
		return "Jump across ledges accurately";
	}
	
	private float xArea = 0.3f;
	private boolean specialJump;
	
	@EventTarget
	public void onRender(EventRender e){
		
		double x = Client.getClient().getPlayer().posX, y = Client.getClient().getPlayer().posY, z = Client.getClient().getPlayer().posZ;
		double motionX = 0, motionY = 0, motionZ = 0;
		int vertexCounter = 0;
		
	    float speedInAir = 0.02F;
	    
        float jumpMovementFactor = speedInAir;
        
        if (true)
        {
            jumpMovementFactor = (float)((double)jumpMovementFactor + (double)speedInAir * 0.3D);
        }
        
		//Helps with motion depletion
        float depletion1 = 0.8F;
        float depletion2 = 0.02F;
        float depletion3 = 0;
        
        if (depletion3 > 3.0F)
        {
            depletion3 = 3.0F;
        }
        
        if (!Client.getClient().getPlayer().onGround)
        {
            depletion3 *= 0.5F;
        }
        
        if (depletion3 > 0.0F)
        {
            depletion1 += (0.54600006F - depletion1) * depletion3 / 3.0F;
        }
        
        //Jumping motion
        float var1 = Client.getClient().getPlayer().rotationYaw * 0.017453292F;
        motionX -= (double)(MathHelper.sin(var1) * 0.2F);
        motionZ += (double)(MathHelper.cos(var1) * 0.2F);
        motionY = 0.42F;
        
        //Draw the prediction
		GL11.glPushMatrix();
		
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_LINE_SMOOTH);
		GL11.glEnable(3042);
		GL11.glDisable(3553);
		
		GL11.glLineWidth(2.5F);
		GL11.glColor4f(0.5f, 0.5f, 0, 0.5f);
		
		GL11.glBegin(GL11.GL_LINE_STRIP);
		
		boolean hitBlock = false;
		boolean flat = false;
		
		while(vertexCounter++ < 300) {
			
			GL11.glVertex3d(x * 1.0D - Client.getClient().getMinecraft().renderManager.renderPosX, y * 1.0D - Client.getClient().getMinecraft().renderManager.renderPosY, z * 1.0D - Client.getClient().getMinecraft().renderManager.renderPosZ);
			
			x += motionX;
			y += motionY;
			z += motionZ;
			
			if(Client.getClient().getMinecraft().theWorld.getBlockState(new BlockPos(x, y, z)).getBlock().getMaterial() != Material.air
					|| Client.getClient().getMinecraft().theWorld.getBlockState(new BlockPos(x+xArea, y, z+xArea)).getBlock().getMaterial() != Material.air
					|| Client.getClient().getMinecraft().theWorld.getBlockState(new BlockPos(x+xArea, y, z-xArea)).getBlock().getMaterial() != Material.air
					|| Client.getClient().getMinecraft().theWorld.getBlockState(new BlockPos(x-xArea, y, z+xArea)).getBlock().getMaterial() != Material.air
					|| Client.getClient().getMinecraft().theWorld.getBlockState(new BlockPos(x-xArea, y, z-xArea)).getBlock().getMaterial() != Material.air){
				
				hitBlock = true;
				
				System.out.println("1: "+y);
				System.out.println("2: "+(int)y);
				System.out.println("3: "+((int)y - y));
				
				double f = ((int)y - y);
				
				if((f < -0.99 && f > -1) || (f < -0.92 && f > -0.93)){
					flat = true;
				}
			}
			
			if(hitBlock){
				break;
			}
			
	        //Walking forward motion
	        float strafe = 0;
	        float forward = 1;
	        float friction = 0.02f;
	        
	        float var4 = strafe * strafe + forward * forward;
	        
	        if (var4 >= 1.0E-4F)
	        {
	            var4 = MathHelper.sqrt_float(var4);
	            
	            if (var4 < 1.0F)
	            {
	                var4 = 1.0F;
	            }
	            
	            var4 = friction / var4;
	            strafe *= var4;
	            forward *= var4;
	            float var5 = MathHelper.sin(Client.getClient().getPlayer().rotationYaw * (float)Math.PI / 180.0F);
	            float var6 = MathHelper.cos(Client.getClient().getPlayer().rotationYaw * (float)Math.PI / 180.0F);
	            motionX += (double)(strafe * var6 - forward * var5);
	            motionZ += (double)(forward * var6 + strafe * var5);
	        }
	        
	        //Motion depletion
	        depletion1 = 0.8F;
	        depletion2 = 0.02F;
	        depletion3 = 0;
	        
	        if (depletion3 > 3.0F)
	        {
	            depletion3 = 3.0F;
	        }
	        
	        if (!Client.getClient().getPlayer().onGround)
	        {
	            depletion3 *= 0.5F;
	        }
	        
	        if (depletion3 > 0.0F)
	        {
	            depletion1 += (0.54600006F - depletion1) * depletion3 / 3.0F;
	        }
	        
			//Depletes the motion
	        motionX *= (double)depletion1;
	        motionY *= 0.800000011920929D;
	        motionZ *= (double)depletion1;
	        motionY -= 0.02D;
	    }
		
		GL11.glEnd();
		
		if(hitBlock){
			if(flat){
				ESPUtils.drawFloorESP(x - xArea, y + 0.1f, z - xArea, 0, 1, 0, false, xArea);
			}else{
				ESPUtils.drawFloorESP(x - xArea, y + 0.1f, z - xArea, 1, 0, 0, false, xArea);
			}
		}
		
		GL11.glDisable(3042);
		GL11.glEnable(3553);
		GL11.glDisable(GL11.GL_LINE_SMOOTH);
		GL11.glEnable(GL11.GL_LIGHTING);
		
		GL11.glPopMatrix();
		
		//System.out.println("1: "+Client.getClient().getPlayer().sprintingTicksLeft);
		//System.out.println("2: "+Client.getClient().getPlayer().sprintToggleTimer);
		//System.out.println("3: "+Client.getClient().getPlayer().moveForward);
		//System.out.println("4: "+Client.getClient().getPlayer().movementInput.moveForward);
		//System.out.println("5: "+Client.getClient().getPlayer().movementInput.updatePlayerMoveState(););
	}
	
	@EventTarget
	public void onTick(EventTick e){
		if(specialJump){
			Client.getClient().getPlayer().moveForward = 0.98f;
			Client.getClient().getPlayer().movementInput.moveForward = 1;
			
			if(Client.getClient().getPlayer().onGround){
				specialJump = false;
				Client.getClient().getMinecraft().gameSettings.keyBindForward.pressed = false;
			}
		}
	}
	
	@EventTarget
	public void onKeyPress(EventKeyPress e){
		if(Keyboard.isKeyDown(Keyboard.KEY_R) && Client.getClient().getPlayer().onGround){
			Client.getClient().getMinecraft().gameSettings.keyBindForward.pressed = true;
			Client.getClient().getMinecraft().gameSettings.keyBindSneak.pressed = false;
			Client.getClient().getPlayer().setSprinting(true);
			Client.getClient().getPlayer().setSneaking(false);
			Client.getClient().getPlayer().sprintingTicksLeft = 600;
			Client.getClient().getPlayer().sprintToggleTimer = 5;
			Client.getClient().getPlayer().moveForward = 0.98f;
			Client.getClient().getPlayer().movementInput.moveForward = 1;
			Client.getClient().getPlayer().jump();
			specialJump = true;
		}
	}
	
	@Override
	public ModType getType() {
		return ModType.WORLD;
	}
	
	@Override
	public void onEnable() {
		
	}
	
	@Override
	public void onDisable() {
		
	}
}
