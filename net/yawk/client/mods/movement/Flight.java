package net.yawk.client.mods.movement;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.yawk.client.Client;
import net.yawk.client.events.*;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.RegisterMod;
import net.yawk.client.modmanager.values.ArrayValue;
import net.yawk.client.modmanager.values.SliderValue;
import net.yawk.client.modmanager.values.AbstractValue;

import org.lwjgl.input.Keyboard;

import com.darkmagician6.eventapi.EventTarget;

@RegisterMod(name = "Flight", defaultKey = Keyboard.KEY_R, desc = "Fly in the air", type = Mod.Type.MOVEMENT)
public class Flight extends Mod{

	private static SliderValue speed;
	private static ArrayValue mode;
	
	public Flight(){

		super(new AbstractValue[]{
				speed = new SliderValue("Fly Speed", "flight.speed", Client.getClient().getValuesRegistry(), 0.5, 0, 3, false),
				mode = new ArrayValue("Mode", "flight.mode", Client.getClient().getValuesRegistry(), 0, new String[]{
					"Normal",
					"3D",
				}),
		});
	}

	@EventTarget
	public void onTick(EventTick e){
		fly();
	}

	public static void fly(){

		if(mode.getValue() == 0){
			normalFly();
		}else if(mode.getValue() == 1){
			betterFly();
		}

	}

	private static void betterFly(){
		
		Minecraft mc = Minecraft.getMinecraft();
		EntityPlayer p = mc.thePlayer;
		
		double sp = speed.getValue();
		
		boolean focus = mc.inGameHasFocus;
		
		Vec3 look = mc.thePlayer.getLookVec();
		
		if(Keyboard.isKeyDown(mc.gameSettings.keyBindForward.getKeyCode()) && focus){
			
			p.motionX = look.xCoord * sp;
			p.motionY = look.yCoord * sp;
			p.motionZ = look.zCoord * sp;
			
		}else if(Keyboard.isKeyDown(mc.gameSettings.keyBindBack.getKeyCode()) && focus){
			
			p.motionX = -look.xCoord * sp;
			p.motionY = -look.yCoord * sp;
			p.motionZ = -look.zCoord * sp;
			
		}else{
			
			p.motionX = 0;
			p.motionY = 0;
			p.motionZ = 0;
		}
	}
	
	private static void normalFly(){
		
		Client.getClient().getPlayer().onGround = true;

		if(Client.getClient().getMinecraft().inGameHasFocus){

			if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
				Client.getClient().getPlayer().motionY = 0.5d;
			}else if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
				Client.getClient().getPlayer().motionY = -0.5d;
			}else{
				Client.getClient().getPlayer().motionY = 0;
			}

			float yaw = Client.getClient().getPlayer().rotationYaw + 90;

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
				Client.getClient().getPlayer().motionX = Math.cos(Math.toRadians(yaw))*speed.getValue();
				Client.getClient().getPlayer().motionZ = Math.sin(Math.toRadians(yaw))*speed.getValue();
			}else{
				Client.getClient().getPlayer().motionX = 0;
				Client.getClient().getPlayer().motionZ = 0;
			}
		}else{
			Client.getClient().getPlayer().motionY = 0;
		}
	}
	
	@Override
	public void onEnable() {

	}

	@Override
	public void onDisable() {

	}
}
