package net.yawk.client.mods.combat;

import net.minecraft.entity.Entity;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.yawk.client.Client;
import net.yawk.client.events.EventGuiRender;
import net.yawk.client.events.EventTick;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.ModDetails;
import net.yawk.client.utils.ClientUtils;
import net.yawk.client.utils.CombatUtils;
import net.yawk.client.utils.HysteriaTimer;

import com.darkmagician6.eventapi.EventTarget;

@ModDetails(name = "Triggerbot", defaultKey = 0, desc = "Hit the player you're looking at", type = Mod.Type.COMBAT)
public class Triggerbot extends Mod{
	
	private HysteriaTimer timer = new HysteriaTimer().setDelay(12);
	
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
