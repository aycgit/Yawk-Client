package net.yawk.client.mods;

import javax.vecmath.Vector2f;
import javax.vecmath.Vector3d;

import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.yawk.client.Client;
import net.yawk.client.events.EventInsideOpaqueBlock;
import net.yawk.client.events.EventMotionUpdate;
import net.yawk.client.events.EventMoveEntity;
import net.yawk.client.events.EventTick;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.ModData;
import net.yawk.client.modmanager.ModType;

import com.darkmagician6.eventapi.EventTarget;
import com.darkmagician6.eventapi.types.EventType;

public class Freecam implements Mod{
	
	private Vector3d prevPosition;
	private Vector2f prevRotation;
	
	private Vector3d freecamPosition;
	private Vector2f freecamRotation;
	
	private ModData flightModData;
	
	public Freecam(){
		
	}
	
	@Override
	public String getName() {
		return "Freecam";
	}
	
	@Override
	public String getDescription() {
		return "Move independant of your body";
	}
	
	@Override
	public ModType getType() {
		return ModType.MOVEMENT;
	}
	
	@EventTarget
	public void onTick(EventTick e){
		if(!flightModData.isEnabled){
			Flight.fly();
		}
	}
	
	@EventTarget
	public void onOpaqueBlockCheck(EventInsideOpaqueBlock e){
		e.inside = false;
	}
	
	@EventTarget
	public void onMove(EventMoveEntity e){
		if(e.type == EventType.PRE){
			Client.getClient().getPlayer().noClip = true;
		}else if(e.type == EventType.POST){
			Client.getClient().getPlayer().noClip = false;
		}
	}
	
	@Override
	public void onEnable() {
		
		prevPosition = new Vector3d(Client.getClient().getPlayer().posX, Client.getClient().getPlayer().posY, Client.getClient().getPlayer().posZ);
		prevRotation = new Vector2f(Client.getClient().getPlayer().rotationYaw, Client.getClient().getPlayer().rotationPitch);
		Client.getClient().getPlayer().noClip = true;
		
		flightModData = Client.getClient().getModManager().dataMap.get(Client.getClient().getModManager().getMod(Flight.class));
		
		EntityOtherPlayerMP yawk = new EntityOtherPlayerMP(Client.getClient().getMinecraft().theWorld, Client.getClient().getPlayer().getGameProfile());
		yawk.inventory = Client.getClient().getPlayer().inventory;
		yawk.inventoryContainer = Client.getClient().getPlayer().inventoryContainer;
		yawk.setPositionAndRotation(Client.getClient().getPlayer().posX, Client.getClient().getPlayer().posY, Client.getClient().getPlayer().posZ, Client.getClient().getPlayer().rotationYaw, Client.getClient().getPlayer().rotationPitch);
		yawk.rotationYawHead = Client.getClient().getPlayer().rotationYawHead;
		Client.getClient().getMinecraft().theWorld.addEntityToWorld(-2, yawk);
	}
	
	@Override
	public void onDisable() {
		
		resetToOriginalPosition();
		Client.getClient().getPlayer().noClip = false;
		
		Client.getClient().getMinecraft().theWorld.removeEntityFromWorld(-2);
	}
	
	@EventTarget
	public void onMotionUpdate(EventMotionUpdate e){
		if(e.type == EventType.PRE){
			resetToOriginalPosition();
		}else if(e.type == EventType.POST){
			setBackToFreecam();
		}
	}
	
	private void resetToOriginalPosition(){
		
		freecamPosition = new Vector3d(Client.getClient().getPlayer().posX, Client.getClient().getPlayer().posY, Client.getClient().getPlayer().posZ);
		freecamRotation = new Vector2f(Client.getClient().getPlayer().rotationYaw, Client.getClient().getPlayer().rotationPitch);
		
		Client.getClient().getPlayer().setPositionAndRotation(prevPosition.x, prevPosition.y, prevPosition.z, prevRotation.x, prevRotation.y);
	}
	
	private void setBackToFreecam(){		
		Client.getClient().getPlayer().setPositionAndRotation(freecamPosition.x, freecamPosition.y, freecamPosition.z, freecamRotation.x, freecamRotation.y);
	}
}
