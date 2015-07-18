package net.yawk.client.mods.movement;

import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C0APacketAnimation;
import net.yawk.client.Client;
import net.yawk.client.events.EventSendPacket;
import net.yawk.client.events.EventTick;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.RegisterMod;
import net.yawk.client.utils.ClientUtils;
import net.yawk.client.utils.MillisecondTimer;

import com.darkmagician6.eventapi.EventTarget;

@RegisterMod(name = "Derp", desc = "Look stupid", type = Mod.Type.MOVEMENT)
public class Derp extends Mod{
	
	public Derp(){
		
	}
	
	private int yaw;
	private MillisecondTimer lookTimer = new MillisecondTimer(150);
	private MillisecondTimer hitTimer = new MillisecondTimer(20);
	
	@EventTarget
	public void onTick(EventTick e){
		
		if(lookTimer.output()){
			ClientUtils.sendPacket(new C03PacketPlayer.C05PacketPlayerLook(yaw, -180, true));
		}
		
		if(hitTimer.output()){
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
