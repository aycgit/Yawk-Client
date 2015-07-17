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
import net.yawk.client.modmanager.ModDetails;
import net.yawk.client.utils.ClientUtils;
import net.yawk.client.utils.CombatUtils;
import net.yawk.client.utils.HysteriaTimer;

import com.darkmagician6.eventapi.EventTarget;

@ModDetails(name = "KillAura", defaultKey = 0, desc = "Kill people near you", type = Mod.Type.COMBAT)
public class KillAura extends Mod{
	
	private HysteriaTimer timer = new HysteriaTimer().setDelay(12);
	private HysteriaTimer rotTimer = new HysteriaTimer().setDelay(4);
	
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
	public void onEnable() {
		
	}

	@Override
	public void onDisable() {
		
	}
}
