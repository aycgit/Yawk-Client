package net.yawk.client.mods.combat;

import net.minecraft.item.*;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C09PacketHeldItemChange;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.yawk.client.Client;
import net.yawk.client.events.EventTick;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.RegisterMod;
import net.yawk.client.utils.ClientUtils;

import com.darkmagician6.eventapi.EventTarget;

@RegisterMod(name = "RapidFire", desc = "Shoot bows faster", type = Mod.Type.COMBAT)
public class RapidFire extends Mod{
	
	public RapidFire(){
		
	}
	
    @EventTarget
	public void onEvent(EventTick event){
    			
		if(!Client.getClient().getPlayer().onGround){
			return;
		}
		
		if(Client.getClient().getMinecraft().gameSettings.keyBindUseItem.pressed && Client.getClient().getPlayer().getHeldItem() != null && isThrowable(Client.getClient().getPlayer().getHeldItem().getItem())){
			
			for (int i = 0; i < 50; i++){
				ClientUtils.sendPacket(new C08PacketPlayerBlockPlacement(Client.getClient().getPlayer().getHeldItem()));
			}
			
			ClientUtils.sendPacket(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, EnumFacing.DOWN));
		}
	}
    
	private boolean isThrowable(Item item) {
		return item != null && (item instanceof ItemBow || item instanceof ItemSnowball
				|| item instanceof ItemEgg || item instanceof ItemEnderPearl);
	}
	
	@Override
	public void onEnable() {
		
	}

	@Override
	public void onDisable() {
		
	}
}
