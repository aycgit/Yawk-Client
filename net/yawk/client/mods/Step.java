package net.yawk.client.mods;

import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C01PacketChatMessage;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.WorldSettings.GameType;
import net.yawk.client.Client;
import net.yawk.client.events.EventGuiRender;
import net.yawk.client.events.EventRecievePacket;
import net.yawk.client.events.EventSendPacket;
import net.yawk.client.events.EventTick;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.ModType;
import net.yawk.client.utils.ClientUtils;

import com.darkmagician6.eventapi.EventTarget;

public class Step implements Mod{
	
	@Override
	public String getName() {
		return "Step";
	}
	
	@Override
	public String getDescription() {
		return "Instantly teleport up blocks";
	}
	
	@EventTarget
	public void onTick(EventTick e){
		if(Client.getClient().getPlayer().isCollidedHorizontally && Client.getClient().getPlayer().onGround){
			//ClientUtils.sendPacket(new C03PacketPlayer.C04PacketPlayerPosition(Client.getClient().getPlayer().posX, Client.getClient().getPlayer().posY + 0.5f, Client.getClient().getPlayer().posZ, Client.getClient().getPlayer().onGround));
			//Client.getClient().getPlayer().setPosition(Client.getClient().getPlayer().posX, Client.getClient().getPlayer().posY + 0.51f, Client.getClient().getPlayer().posZ);
			Client.getClient().getPlayer().setPosition(Client.getClient().getPlayer().posX, Client.getClient().getPlayer().posY+1.2, Client.getClient().getPlayer().posZ);
		}
	}
	
	@Override
	public ModType getType() {
		return ModType.MOVEMENT;
	}
	
	public void onEnable(){
		
	}
	
	public void onDisable(){
		
	}
}
