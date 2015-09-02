package net.yawk.client.utils;

import net.minecraft.network.Packet;
import net.yawk.client.Client;

public class MinecraftUtils {
	
	public static void sendDirectPacket(Packet p){
		Client.getClient().getMinecraft().getNetHandler().getNetworkManager().sendPacket(p);
	}
	
	public static void sendPacket(Packet p){
		Client.getClient().getPlayer().sendQueue.addToSendQueue(p);
	}
}
