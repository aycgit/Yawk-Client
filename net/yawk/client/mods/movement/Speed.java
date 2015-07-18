package net.yawk.client.mods.movement;

import java.util.Random;

import net.minecraft.item.EnumAction;
import net.minecraft.network.play.client.C09PacketHeldItemChange;
import net.yawk.client.Client;
import net.yawk.client.events.*;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.RegisterMod;

import com.darkmagician6.eventapi.EventTarget;
import com.darkmagician6.eventapi.types.EventType;

@RegisterMod(name = "Speed", desc = "Move faster", type = Mod.Type.MOVEMENT)
public class Speed extends Mod{
	
	public Speed(){
		
	}
	
	@EventTarget
	public void onMove(EventMoveEntity e){
		if(e.type == EventType.PRE){
			
			e.x *= 3f;
			e.z *= 3f;
			
			if(e.y > 0){
				e.y *= 2f;
			}
		}
	}
	
	@Override
	public void onEnable() {
		
	}
	
	@Override
	public void onDisable() {
		
	}
}
