package net.yawk.client.mods;

import java.util.Random;

import net.minecraft.item.EnumAction;
import net.minecraft.network.play.client.C09PacketHeldItemChange;
import net.yawk.client.Client;
import net.yawk.client.events.*;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.ModType;

import com.darkmagician6.eventapi.EventTarget;
import com.darkmagician6.eventapi.types.EventType;

public class Speed implements Mod{
	
	public Speed(){
		
	}
	
	@Override
	public String getName() {
		return "Speed";
	}
	
	@Override
	public String getDescription() {
		return "Walk faster";
	}
	
	@EventTarget
	public void onMove(EventMoveEntity e){
		if(e.type == EventType.PRE){
			
			e.x *= 4f;
			e.z *= 4f;
		}
	}
		
	@Override
	public ModType getType() {
		return ModType.MOVEMENT;
	}
	
	@Override
	public void onEnable() {
		
	}
	
	@Override
	public void onDisable() {
		
	}
}
