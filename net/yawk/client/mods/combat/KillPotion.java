package net.yawk.client.mods.combat;

import net.minecraft.client.Minecraft;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.yawk.client.Client;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.RegisterMod;

@RegisterMod(name = "KillPotion", desc = "Get a creative killing potion", type = Mod.Type.COMBAT)
public class KillPotion extends Mod{

	Minecraft mc = Minecraft.getMinecraft();

	public KillPotion(){
		
	}
	
	@Override
	public void onEnable() {
		
		if(mc.thePlayer.inventory.getStackInSlot(0) != null){
			mc.thePlayer.addChatComponentMessage(new ChatComponentTranslation("[KillPotion] Remove item out of slot 1!"));
			Client.getClient().getModManager().toggle(this);
			return;
		}else if(!mc.thePlayer.capabilities.isCreativeMode){
			mc.thePlayer.addChatComponentMessage(new ChatComponentTranslation("[KillPotion] Get into creative mode!"));
			Client.getClient().getModManager().toggle(this);
			return;
		}
		
		ItemStack stack = new ItemStack(Items.potionitem);
		stack.setItemDamage(16384);
		NBTTagList effects = new NBTTagList();
		NBTTagCompound effect = new NBTTagCompound();
		effect.setInteger("Amplifier", 125);
		effect.setInteger("Duration", 2000);
		effect.setInteger("Id", 6);
		effects.appendTag(effect);
		stack.setTagInfo("CustomPotionEffects", effects);
		stack.setStackDisplayName(EnumChatFormatting.GREEN+"Free Advertising - Contact Yawk Griefing on Youtube!");
		mc.thePlayer.sendQueue.addToSendQueue(new C10PacketCreativeInventoryAction(36, stack));
		
		Client.getClient().getModManager().toggle(this);
	}
}
