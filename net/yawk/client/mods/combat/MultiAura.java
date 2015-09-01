package net.yawk.client.mods.combat;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.yawk.client.Client;
import net.yawk.client.events.EventMotionUpdate;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.RegisterMod;
import net.yawk.client.modmanager.values.AbstractValue;
import net.yawk.client.modmanager.values.SliderValue;
import net.yawk.client.utils.CombatUtils;
import net.yawk.client.utils.timing.MillisecondTimer;
import net.yawk.client.utils.timing.ValueTimer;

import com.darkmagician6.eventapi.EventTarget;

@RegisterMod(name = "MultiAura", desc = "Attack multiple enemies at once", type = Mod.Type.COMBAT)
public class MultiAura extends Mod{

	private static SliderValue delay;
	private Minecraft mc;
	private MillisecondTimer timer;

	public MultiAura(){

		super(new AbstractValue[]{
				delay = new SliderValue("Hit Delay", "multiaura.hitdelay", Client.getClient().getValuesRegistry(), 120, 0, 2000, true),
		});

		timer = new ValueTimer(delay);
		mc = Minecraft.getMinecraft();
	}

	@EventTarget
	public void onPreMotionUpdate(EventMotionUpdate event) {

		if(timer.output()) {
			
			int targets = 0;
			
			for(Object obj : mc.theWorld.loadedEntityList) {

				Entity e = (Entity)obj;

				if(CombatUtils.isAttackable(e) && targets <= 6) {
					CombatUtils.hit(e);
					targets++;
				}
			}
		}
	}
}
