package net.yawk.client.mods;

import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C0BPacketEntityAction;
import net.yawk.client.events.EventGuiRender;
import net.yawk.client.events.EventMotionUpdate;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.ModType;
import net.yawk.client.utils.ClientUtils;

import com.darkmagician6.eventapi.EventTarget;
import com.darkmagician6.eventapi.types.EventType;

public class Sneak implements Mod{
	
	Minecraft mc = Minecraft.getMinecraft();
	
	private boolean wasSneaking;
	
	@Override
	public String getName() {
		return "Sneak";
	}
	
	@Override
	public String getDescription() {
		return "Walk fast while sneaking";
	}
	
	@EventTarget
    private void onMotionUpdate(EventMotionUpdate e) {
            if (e.type == EventType.PRE) {
            	sneak(true);
            	sneak(false);
            } else if (e.type == EventType.POST) {
            	sneak(false);
            	sneak(true);
            }
    }
	
	/*
	@EventTarget
	public void onTick(EventGuiRender e){
		if(wasSneaking && !mc.thePlayer.isSneaking()){
			sneak(true);
		}
		
		wasSneaking = mc.thePlayer.isSneaking();
	}*/
	
	@Override
	public ModType getType() {
		return ModType.MOVEMENT;
	}
	
	public void onEnable(){
		//sneak(true);
	}
	
	public void onDisable(){/*
		if(mc.thePlayer.isSneaking()){
    		sneak(true);
    	}else{
    		sneak(false);
    	}*/
	}
	
	public void sneak(boolean on){
		if(on){
        	Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C0BPacketEntityAction(Minecraft.getMinecraft().thePlayer, C0BPacketEntityAction.Action.START_SNEAKING));
		}else{
        	Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C0BPacketEntityAction(Minecraft.getMinecraft().thePlayer, C0BPacketEntityAction.Action.STOP_SNEAKING));
		}
	}
}
