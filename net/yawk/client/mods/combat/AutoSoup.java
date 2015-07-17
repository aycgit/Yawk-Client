package net.yawk.client.mods.combat;

import com.darkmagician6.eventapi.EventTarget;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C09PacketHeldItemChange;
import net.yawk.client.events.EventGuiRender;
import net.yawk.client.events.EventTick;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.ModDetails;

@ModDetails(name = "AutoSoup", defaultKey = 0, desc = "Eat soup automatically", type = Mod.Type.COMBAT)
public class AutoSoup extends Mod{

	Minecraft mc = Minecraft.getMinecraft();
	private boolean inInventory;
	private int invSlot;
	private ItemStack is;
	private int stackSlot = -1;
	private long currentMS;
	private long lastStack = -1;
	private long stackThreshhold = 150L;
	
	public AutoSoup(){
		//TODO: Make this mod better because chris made it and it isn't very good
	}
	
	@EventTarget
	public void onTick(EventTick e){
		if (mc.thePlayer != null && mc.thePlayer.getHealth() <= 13.0F)
		{
			getSoup();
			getBestWeapon();
		}
	}
	
	@Override
	public void onEnable() {

	}

	@Override
	public void onDisable() {

	}

	private void getSoup()
	{
		currentMS = System.nanoTime() / 1000000;
		if (timeHasPassed(stackThreshhold))
		{
			stackBowl(); // it puts the lotion in the basket
			lastStack = System.nanoTime() / 1000000;
		}
		invSlot = -1;
		is = null;
		for (int slot = 44; slot >= 9; slot--)
		{
			is = getStackAt(slot);
			if (is == null)
			{
				continue;
			}
			if (is.getItem().getIdFromItem(is.getItem()) == 282)
			{
				if (slot >= 36 && slot <= 44)
				{
					int theSlot = invSlot - 36;
					if (mc.thePlayer.inventory.currentItem != theSlot)
					{
						mc.thePlayer.inventory.currentItem = slot - 36;
						// switch to the correct item
						Minecraft.getMinecraft().getNetHandler().addToSendQueue(new C09PacketHeldItemChange(Minecraft.getMinecraft().thePlayer.inventory.currentItem));
					}
					Minecraft.getMinecraft().playerController.updateController();
					eatHeldItem(); // eat a sandwich
					break;
				}
				invSlot = slot;
				break;
			}
		}
		
		if (is == null)
		{
			return;
		}
		
		if (invSlot != -1)
		{
			// shift click so that we dont have to pick up and put down, causing violations
			Minecraft.getMinecraft().playerController.windowClick(0, invSlot, 0, 1, Minecraft.getMinecraft().thePlayer);
			Minecraft.getMinecraft().playerController.updateController();
		}
	}
	
	private void getBestWeapon()
	{
		float damageModifier = 1;
		int newItem = mc.thePlayer.inventory.currentItem;
		for (int slot = 0; slot < 9; slot++)
		{
			ItemStack stack = mc.thePlayer.inventory.mainInventory[slot];
			if(stack == null){
				continue;
			}

			if(stack.getItem() instanceof ItemSword){
				ItemSword is = (ItemSword) stack.getItem();
				float damage = is.func_150931_i();
				if(damage > damageModifier){
					newItem = slot;
					damageModifier = damage;
				}
			}
		}

		mc.thePlayer.inventory.currentItem = newItem;
	}

	private boolean timeHasPassed(long threshhold)
	{
		return currentMS - lastStack >= threshhold;
	}

	private void eatHeldItem()
	{
		//Minecraft.getMinecraft().getNetHandler().addToSendQueue(new C08PacketPlayerBlockPlacement(-1, -1, -1, 255, Minecraft.getMinecraft().thePlayer.getCurrentEquippedItem(), 0F, 0F, 0F));
		mc.getNetHandler().addToSendQueue(new C08PacketPlayerBlockPlacement(mc.thePlayer.inventory.getCurrentItem()));
		Minecraft.getMinecraft().playerController.updateController(); // update the controller
	}

	private ItemStack getStackAt(int slot)
	{
		return mc.thePlayer.inventoryContainer.getSlot(slot).getStack();
	}

	private void stackBowl()
	{
		for (int slot = 44; slot >= 9; slot--)
		{ // check ALL the slot
			is = getStackAt(slot);

			if (is == null)
			{ // nothing there? let's try and use it
				if (!(stackSlot >= 36 && stackSlot <= 44))
				{
					stackSlot = slot;
				}
				continue;
			}

			if (is.getItem().getIdFromItem(is.getItem()) == 281)
			{ // is it mushroom soup?
				if (stackSlot != -1)
				{
					if (is.stackSize >= 64)
					{
						continue;
					} // make sure not to event consider stacks that are already full - leave them alone
					Minecraft.getMinecraft().playerController.windowClick(0, slot, 0, 0, mc.thePlayer);
					Minecraft.getMinecraft().playerController.windowClick(0, stackSlot, 0, 0, mc.thePlayer);
					Minecraft.getMinecraft().playerController.updateController();
					break; // stack only one bowl at once to avoid NC+ violations
				}
				else
				{
					stackSlot = slot; //set the slot to stack at
				}
			}
		}
		stackSlot = -1; // reset the slot so it doesn't get stuck later on
	}

}
