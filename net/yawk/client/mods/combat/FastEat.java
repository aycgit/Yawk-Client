package net.yawk.client.mods.combat;

import net.minecraft.network.play.client.C03PacketPlayer;
import net.yawk.client.Client;
import net.yawk.client.events.EventGuiRender;
import net.yawk.client.events.EventTick;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.RegisterMod;
import net.yawk.client.utils.ClientUtils;

import com.darkmagician6.eventapi.EventTarget;

@RegisterMod(name = "FastEat", desc = "Eat food faster", type = Mod.Type.COMBAT)
public class FastEat extends Mod{
	
	@EventTarget
	public void onTick(EventTick e)
	{
		if(Client.getClient().getPlayer().isEating()){
			if (Client.getClient().getPlayer().getItemInUseDuration() > 5){
				for (int index = 0; index < 100; index++) {
					Client.getClient().getMinecraft().getNetHandler().addToSendQueue(new C03PacketPlayer(Client.getClient().getPlayer().onGround));
				}
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
