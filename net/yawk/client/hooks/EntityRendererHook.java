package net.yawk.client.hooks;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.resources.IResourceManager;
import net.yawk.client.Client;
import net.yawk.client.cameras.Camera;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.mods.combat.NoFlinch;

public class EntityRendererHook extends EntityRenderer{

	public EntityRendererHook(Minecraft mcIn, IResourceManager p_i45076_2_) {
		super(mcIn, p_i45076_2_);
	}
	
	@Override
	public float getFOVModifier(float partialTicks, boolean p_78481_2_) {
		
		if(Camera.isCapturing()){
            return 90.0F;
		}else{
			return super.getFOVModifier(partialTicks, p_78481_2_);
		}
	}
	
	//Set by Client after the mod manager is created
	public Mod noFlinch;
	
	@Override
	protected void hurtCameraEffect(float damage) {
		
		if(!noFlinch.isEnabled()){
			super.hurtCameraEffect(damage);
		}
	}
}
