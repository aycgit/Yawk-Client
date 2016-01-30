package net.yawk.client.mods.combat;

import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C02PacketUseEntity.Action;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.server.S03PacketTimeUpdate;
import net.yawk.client.Client;
import net.yawk.client.events.EventRecievePacket;
import net.yawk.client.events.EventSendPacket;
import net.yawk.client.events.EventTick;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.RegisterMod;
import net.yawk.client.modmanager.values.AbstractValue;
import net.yawk.client.modmanager.values.SliderValue;
import net.yawk.client.utils.MinecraftUtils;

import com.darkmagician6.eventapi.EventTarget;
import com.darkmagician6.eventapi.types.EventType;

@RegisterMod(name = "Criticals", desc = "Get criticals hits on players", type = Mod.Type.COMBAT)
public class Criticals extends Mod{
	
	private Minecraft mc;
	
	public Criticals(){
		mc = Minecraft.getMinecraft();
	}
	
	@EventTarget
	public void onSendPacket(EventSendPacket e){
		
		if(mc.thePlayer.onGround && e.packet instanceof C02PacketUseEntity){
			
			if(((C02PacketUseEntity)e.packet).getAction() == Action.ATTACK && e.type == EventType.PRE){
				MinecraftUtils.sendDirectPacket(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + 0.2, mc.thePlayer.posZ, false));
			}
		}
	}
}
