package net.yawk.client.hooks;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.entity.Entity;

//TODO: Use this for something
public class RenderGlobalHook extends RenderGlobal{

	public RenderGlobalHook(Minecraft mcIn) {
		super(mcIn);
	}

	@Override
	public void loadRenderers() {
		super.loadRenderers();
	}

	@Override
	public void onEntityAdded(Entity entityIn) {
		super.onEntityAdded(entityIn);
	}

	@Override
	public void onEntityRemoved(Entity entityIn) {
		super.onEntityRemoved(entityIn);
	}

	
}
