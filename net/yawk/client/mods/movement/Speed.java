package net.yawk.client.mods.movement;

import java.util.Random;

import net.minecraft.item.EnumAction;
import net.minecraft.network.play.client.C09PacketHeldItemChange;
import net.yawk.client.Client;
import net.yawk.client.events.*;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.RegisterMod;
import net.yawk.client.modmanager.values.SliderValue;
import net.yawk.client.modmanager.values.Value;

import com.darkmagician6.eventapi.EventTarget;
import com.darkmagician6.eventapi.types.EventType;

@RegisterMod(name = "Speed", desc = "Move faster", type = Mod.Type.MOVEMENT)
public class Speed extends Mod{
	
	private static SliderValue speed;
	
	public Speed(){
		
		super(new Value[]{
				speed = new SliderValue("Walk Speed", Client.getClient().getValuesRegistry(), 2, 0, 5, false),
		});
	}
	
	@EventTarget
	public void onMove(EventMoveEntity e){
		if(e.type == EventType.PRE){
			
			e.x *= speed.getValue();
			e.z *= speed.getValue();
			
			if(e.y > 0){
				e.y *= speed.getValue();
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
