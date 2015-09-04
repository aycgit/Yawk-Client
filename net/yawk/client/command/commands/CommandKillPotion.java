package net.yawk.client.command.commands;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.yawk.client.Client;
import net.yawk.client.command.Argument;
import net.yawk.client.command.Arguments;
import net.yawk.client.command.Command;
import net.yawk.client.command.CommandManager;

public class CommandKillPotion extends Command {
	
	public CommandKillPotion() {
		super("KillPotion", "killpotion", "Gives you a potion which kills people");
	}
	
	@Override
	public void runCommand(CommandManager cm, Arguments args) {
		
		if(mc.thePlayer.inventory.getStackInSlot(0) != null){
			chat("[KillPotion] Remove item out of slot 1!");
			return;
		}else if(!mc.thePlayer.capabilities.isCreativeMode){
			chat("[KillPotion] Get into creative mode!");
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
		
	}

	@Override
	public Argument[] getArguments(CommandManager cm) {
		return null;
	}
}