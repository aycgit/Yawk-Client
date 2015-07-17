package net.yawk.client.mods.movement;

import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C0APacketAnimation;
import net.yawk.client.Client;
import net.yawk.client.events.EventSendPacket;
import net.yawk.client.events.EventTick;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.ModDetails;
import net.yawk.client.modmanager.ModType;
import net.yawk.client.utils.ClientUtils;
import net.yawk.client.utils.HysteriaTimer;

import com.darkmagician6.eventapi.EventTarget;

@ModDetails(name = "Retard", defaultKey = 0, desc = "Look stupid", type = ModType.MOVEMENT)
public class Retard extends Mod{
	
	public Retard(){
		
	}
	
	private int yaw;
	private HysteriaTimer timer = new HysteriaTimer(15);
	private HysteriaTimer timer1 = new HysteriaTimer(2);
	
	@EventTarget
	public void onTick(EventTick e){
		
		if(timer.output()){
			ClientUtils.sendPacket(new C03PacketPlayer.C05PacketPlayerLook(yaw, -180, true));
		}
		
		if(timer1.output()){
			yaw += 72;
			ClientUtils.sendPacket(new C0APacketAnimation());
		}
	}
	
	@EventTarget
	public void onPacketSend(EventSendPacket e){
		
		if(e.packet instanceof C03PacketPlayer){
			C03PacketPlayer p = (C03PacketPlayer) e.packet;
			p.yaw = yaw;
			p.pitch = -180;
		}
	}
	
	@Override
	public void onEnable() {
		
	}
	
	@Override
	public void onDisable() {
		
	}
}
