package net.yawk.client.mods.world;

import javax.vecmath.Vector3d;

import net.minecraft.network.play.client.C01PacketChatMessage;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C03PacketPlayer.C04PacketPlayerPosition;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.WorldSettings.GameType;
import net.yawk.client.Client;
import net.yawk.client.events.EventMoveEntity;
import net.yawk.client.events.EventRecievePacket;
import net.yawk.client.events.EventSendPacket;
import net.yawk.client.events.EventTick;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.ModDetails;
import net.yawk.client.utils.ChatColours;
import net.yawk.client.utils.ClientUtils;
import net.yawk.client.utils.HysteriaTimer;

import com.darkmagician6.eventapi.EventTarget;

@ModDetails(name = "Day", defaultKey = 0, desc = "Set the time to day", type = Mod.Type.WORLD)
public class Day extends Mod{
	
	public Day(){
		
	}
	
	@EventTarget
	public void onTick(EventTick e){
		Client.getClient().getMinecraft().theWorld.setWorldTime(1000);
	}
	
	@Override
	public void onEnable() {
		
	}
	
	@Override
	public void onDisable() {
		
	}
}
