package net.yawk.client.mods.combat;

import net.minecraft.entity.Entity;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.yawk.client.Client;
import net.yawk.client.events.EventGuiRender;
import net.yawk.client.events.EventTick;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.RegisterMod;
import net.yawk.client.utils.ClientUtils;
import net.yawk.client.utils.CombatUtils;
import net.yawk.client.utils.MillisecondTimer;

import com.darkmagician6.eventapi.EventTarget;

@RegisterMod(name = "Triggerbot", desc = "Hit the player you're looking at", type = Mod.Type.COMBAT)
public class Triggerbot extends Mod{
	
	private MillisecondTimer timer = new MillisecondTimer(120);
	
	@EventTarget
	public void onTick(EventTick e){
		if(Client.getClient().getMinecraft().objectMouseOver != null && Client.getClient().getMinecraft().objectMouseOver.typeOfHit == MovingObjectType.ENTITY){
			Entity entity = Client.getClient().getMinecraft().objectMouseOver.entityHit;
			if(CombatUtils.isAttackable(entity) && timer.output()){
				CombatUtils.hit(entity);
				timer.setDelay(12 + ClientUtils.random.nextInt(3));
			}
		}
	}

	@Override
	public void onEnable() {
		timer.reset();
	}

	@Override
	public void onDisable() {
		
	}
}
