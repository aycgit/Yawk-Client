package net.yawk.client.hooks;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.entity.Entity;
import net.yawk.client.Client;
import net.yawk.client.mods.world.Perception;

//TODO: Use this for something
public class RenderGlobalHook extends RenderGlobal{

	public RenderGlobalHook(Minecraft mcIn) {
		super(mcIn);
	}

	public Perception perception;
	
	@Override
	public void onEntityAdded(Entity e) {
		super.onEntityAdded(e);
		perception.onEntityAdded(e);
	}

}
