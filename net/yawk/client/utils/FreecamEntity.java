package net.yawk.client.utils;

import com.mojang.authlib.GameProfile;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.EntityLiving;
import net.minecraft.stats.StatFileWriter;
import net.minecraft.world.World;
import net.yawk.client.hooks.EntityPlayerSPHook;

public class FreecamEntity extends AbstractClientPlayer{

	public FreecamEntity(World worldIn, GameProfile p_i45074_2_) {
		super(worldIn, p_i45074_2_);
	}
	
	
}
