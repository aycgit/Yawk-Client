package net.yawk.client.mods.combat;

import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemEgg;
import net.minecraft.item.ItemEnderPearl;
import net.minecraft.item.ItemSnowball;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.yawk.client.Client;
import net.yawk.client.events.EventRender;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.ModDetails;
import net.yawk.client.utils.ESPUtils;

import org.lwjgl.opengl.GL11;

import com.darkmagician6.eventapi.EventTarget;

@ModDetails(name = "Projectiles", desc = "See where arrows will land", type = Mod.Type.COMBAT)
public class Projectiles extends Mod{
	
	public Projectiles(){
		
	}
		
	@EventTarget
	public void onRender(EventRender e){
		
		if (Client.getClient().getPlayer().getCurrentEquippedItem() != null && isThrowable(Client.getClient().getPlayer().getCurrentEquippedItem().getItem())) {
			
			double x = Client.getClient().getPlayer().lastTickPosX
					+ (Client.getClient().getPlayer().posX - Client.getClient().getPlayer().lastTickPosX)
					* (double) Client.getClient().getMinecraft().timer.renderPartialTicks
					- (double) (MathHelper.cos((float) Math.toRadians((double) Client.getClient().getPlayer().rotationYaw)) * 0.16F);
			
			double y = Client.getClient().getPlayer().lastTickPosY
					+ (Client.getClient().getPlayer().posY - Client.getClient().getPlayer().lastTickPosY) * (double) Client.getClient().getMinecraft().timer.renderPartialTicks
					+ (double) Client.getClient().getPlayer().getEyeHeight() - 0.100149011612D;
			
			double z = Client.getClient().getPlayer().lastTickPosZ
					+ (Client.getClient().getPlayer().posZ - Client.getClient().getPlayer().lastTickPosZ)
					* (double) Client.getClient().getMinecraft().timer.renderPartialTicks
					- (double) (MathHelper.sin((float) Math.toRadians((double) Client.getClient().getPlayer().rotationYaw)) * 0.16F);
			
			double motionX, motionY, motionZ;
			int vertexCounter = 0;
			
			float con;
			
			if (Client.getClient().getPlayer().getCurrentEquippedItem().getItem() instanceof ItemBow) {
				con = 1f;
			}else{
				con = 0.4f;
			}
			
			motionX = (-MathHelper.sin((float) Math.toRadians(Client.getClient().getPlayer().rotationYaw)) * MathHelper.cos((float) Math.toRadians(Client.getClient().getPlayer().rotationPitch)) * con);
			motionZ = (MathHelper.cos((float) Math.toRadians(Client.getClient().getPlayer().rotationYaw)) * MathHelper.cos((float) Math.toRadians(Client.getClient().getPlayer().rotationPitch)) * con);
			motionY = (-MathHelper.sin((float) Math.toRadians(Client.getClient().getPlayer().rotationPitch)) * con);
			
			double ssum = Math.sqrt(motionX * motionX + motionY * motionY + motionZ * motionZ);
			motionX /= ssum;
			motionY /= ssum;
			motionZ /= ssum;
			
			if (Client.getClient().getPlayer().getCurrentEquippedItem().getItem() instanceof ItemBow) {
				
				float pow = (float) (72000 - Client.getClient().getPlayer().getItemInUseCount()) / 20.0F;
				pow = (pow * pow + pow * 2.0F) / 3.0F;
				
				if (pow > 1.0F) {
					pow = 1.0F;
				}
				
				if (pow <= 0.1F) {
					pow = 1.0F;
				}
				
				pow *= 2.0F;
				pow *= 1.5F;
				
				motionX *= pow;
				motionY *= pow;
				motionZ *= pow;
				
			}else{
				
				motionX *= 1.5d;
				motionY *= 1.5d;
				motionZ *= 1.5d;
				
			}
			
			GL11.glPushMatrix();
			
			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glEnable(GL11.GL_LINE_SMOOTH);
			GL11.glEnable(3042);
			GL11.glDisable(3553);
			
			GL11.glLineWidth(3.5F);
			GL11.glColor4f(0.5f, 0.5f, 0, 0.5f);
			GL11.glBegin(GL11.GL_LINE_STRIP);
			
			while(vertexCounter++ < 200) {
				
				GL11.glVertex3d(x * 1.0D - Client.getClient().getMinecraft().renderManager.renderPosX, y * 1.0D - Client.getClient().getMinecraft().renderManager.renderPosY, z * 1.0D - Client.getClient().getMinecraft().renderManager.renderPosZ);
				
				x += motionX;
				y += motionY;
				z += motionZ;
				
				motionX *= 0.99D;
				motionY *= 0.99D;
				motionZ *= 0.99D;
				motionY -= 0.05D;
				
				if(Client.getClient().getMinecraft().theWorld.rayTraceBlocks(new Vec3(Client.getClient().getPlayer().posX, Client.getClient().getPlayer().posY+Client.getClient().getPlayer().getEyeHeight(), Client.getClient().getPlayer().posZ), new Vec3(x, y, z), true) != null){
					break;
				}
			}
			
			GL11.glEnd();
			
			GL11.glDisable(3042);
			GL11.glEnable(3553);
			GL11.glDisable(GL11.GL_LINE_SMOOTH);
			GL11.glEnable(GL11.GL_LIGHTING);
			
			GL11.glPopMatrix();
		}
	}
	
	private boolean isThrowable(Item item) {
		return item instanceof ItemBow || item instanceof ItemSnowball
				|| item instanceof ItemEgg || item instanceof ItemEnderPearl;
	}
		
	@Override
	public void onEnable() {
		
	}
	
	@Override
	public void onDisable() {
		
	}
}
