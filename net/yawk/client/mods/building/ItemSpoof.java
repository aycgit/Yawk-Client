package net.yawk.client.mods.building;

import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C09PacketHeldItemChange;
import net.yawk.client.Client;
import net.yawk.client.events.EventMotionUpdate;
import net.yawk.client.events.EventTick;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.ModType;
import net.yawk.client.utils.ClientUtils;

import com.darkmagician6.eventapi.EventTarget;
import com.darkmagician6.eventapi.types.EventType;

public class ItemSpoof implements Mod{
	
	public ItemSpoof(){
		
	}
	
	@Override
	public String getName() {
		return "ItemSpoof";
	}
	
	@Override
	public String getDescription() {
		return "Hides what item you're holding";
	}
		
	@Override
	public ModType getType() {
		return ModType.BUILDING;
	}

	@Override
	public void onEnable() {
		
	}
	
	private boolean reverse = false;
	
    @EventTarget
    public void onMotionUpdate(EventMotionUpdate event)
    {

    	if (event.type == EventType.PRE)
    	{
    		if (!reverse)
    		{
    			ClientUtils.sendPacket(new C09PacketHeldItemChange(getAir()));
    		}
    		else
    		{
    			ClientUtils.sendPacket(new C09PacketHeldItemChange(Client.getClient().getPlayer().inventory.currentItem));
    		}
    	}
    	else if (event.type == EventType.POST)
    	{
    		if (!reverse)
    		{
    			ClientUtils.sendPacket(new C09PacketHeldItemChange(Client.getClient().getPlayer().inventory.currentItem));
    		}
    		else
    		{
    			ClientUtils.sendPacket(new C09PacketHeldItemChange(getAir()));
    		}
    	}
    }
    
    private int getAir(){
    	//Loop through all slots
    	for (int slot = 0; slot < 9; slot++) 
    	{
    		ItemStack currentStack = Client.getClient().getPlayer().inventory.mainInventory[slot];
    		
    		//Find empty slots
    		if(currentStack == null){
    			return slot;
    		}
    	}
    	
    	return 0;
    }
    
    @Override
    public void onDisable()
    {
        ClientUtils.sendPacket(new C09PacketHeldItemChange(Client.getClient().getPlayer().inventory.currentItem));
    }
}