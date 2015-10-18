package me.riverhouse.candy.utils;

import me.riverhouse.candy.api.entity.Player;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.multiplayer.WorldClient;

public class Wrapper {

	private static Minecraft minecraft = Minecraft.getMinecraft();
	
	public static Minecraft getMinecraft() {
		return minecraft;
	}
	public static Player getPlayer() {
		return minecraft.thePlayer;
	}
	public static WorldClient getWorld() {
		return minecraft.theWorld;
	}
	
	public static FontRenderer getFontRenderer() {
		return minecraft.fontRendererObj;
	}
	
}
