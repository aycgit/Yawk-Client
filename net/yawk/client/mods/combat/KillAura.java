package net.yawk.client.mods.combat;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.MathHelper;
import net.yawk.client.Client;
import net.yawk.client.events.EventGuiRender;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.ModType;
import net.yawk.client.utils.ClientUtils;
import net.yawk.client.utils.CombatUtils;
import net.yawk.client.utils.HysteriaTimer;

import com.darkmagician6.eventapi.EventTarget;

public class KillAura implements Mod{
	
	private HysteriaTimer timer = new HysteriaTimer().setDelay(12);
	private HysteriaTimer rotTimer = new HysteriaTimer().setDelay(4);
	
	@Override
	public String getName() {
		return "KillAura";
	}
	
	@Override
	public String getDescription() {
		return "Kill players who are near you";
	}
	
	@EventTarget
	public void onTick(EventGuiRender e){
		
			EntityPlayer player = CombatUtils.getClosestPlayer(3.95f);
			
			if (player != null && timer.output()) {
				
				if(CombatUtils.faceEntitySmooth(player, false)){
					
					Client.getClient().getMinecraft().playerController.attackEntity(Client.getClient().getPlayer(), player);
					Client.getClient().getPlayer().swingItem();
					//faceEntity(entityplayer);
				}
			}
			
			if (player != null && rotTimer.output()) {
				CombatUtils.faceEntitySmooth(player, true);
			}
	}
	
	@Override
	public ModType getType() {
		return ModType.COMBAT;
	}
	
	@Override
	public void onEnable() {
		
	}

	@Override
	public void onDisable() {
		
	}
}
