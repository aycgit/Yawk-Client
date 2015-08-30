package net.yawk.client.mods.movement;

import net.minecraft.client.Minecraft;
import net.yawk.client.Client;
import net.yawk.client.events.*;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.RegisterMod;
import net.yawk.client.modmanager.values.SliderValue;
import net.yawk.client.modmanager.values.AbstractValue;

import org.lwjgl.input.Keyboard;

import com.darkmagician6.eventapi.EventTarget;

@RegisterMod(name = "Flight", defaultKey = Keyboard.KEY_R, desc = "Fly in the air", type = Mod.Type.MOVEMENT)
public class Flight extends Mod{

	private static SliderValue speed;
	private Minecraft mc;

	public Flight(){

		super(new AbstractValue[]{
				speed = new SliderValue("Fly Speed", "flight.speed", Client.getClient().getValuesRegistry(), 0.5, 0, 3, false),
		});

		mc = Minecraft.getMinecraft();
	}

	@EventTarget
	public void onTick(EventTick e){
		fly();
	}

	public void fly(){

		mc.thePlayer.onGround = true;

		if(Client.getClient().getMinecraft().inGameHasFocus){

			float yaw = mc.thePlayer.rotationYaw + 90;
			float pitch = mc.thePlayer.rotationPitch;

			if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
				pitch += 45;
			}else if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
				pitch -= 45;
			}

			boolean keyW = Keyboard.isKeyDown(Keyboard.KEY_W), 
					keyS = Keyboard.isKeyDown(Keyboard.KEY_S), 
					keyA = Keyboard.isKeyDown(Keyboard.KEY_A), 
					keyD = Keyboard.isKeyDown(Keyboard.KEY_D);

			if(keyW)
			{
				if(keyA)
				{
					yaw -= 45;
				}else if(keyD){
					yaw += 45;
				}
			}else if(keyS){
				yaw += 180;
				if(keyA)
				{
					yaw += 45;
				}else if(keyD){
					yaw -= 45;
				}
			}else if(keyA){
				yaw -= 90;
			}else if(keyD){
				yaw += 90;
			}

			if(keyW || keyA || keyS || keyD)
			{
				mc.thePlayer.motionX = Math.cos(Math.toRadians(yaw))*speed.getValue();
				mc.thePlayer.motionZ = Math.sin(Math.toRadians(yaw))*speed.getValue();
				
				mc.thePlayer.motionY = Math.sin(Math.toRadians(pitch))*speed.getValue();
				
			}else{
				mc.thePlayer.motionX = 0;
				mc.thePlayer.motionZ = 0;
			}
		}else{
			mc.thePlayer.motionY = 0;
		}

	}

	@Override
	public void onEnable() {

	}

	@Override
	public void onDisable() {

	}
}
