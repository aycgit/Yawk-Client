package net.yawk.client.mods.combat;

import net.minecraft.client.Minecraft;

import net.minecraft.potion.Potion;
import net.minecraft.util.ChatComponentTranslation;
import net.yawk.client.events.EventGuiRender;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.RegisterMod;

import com.darkmagician6.eventapi.EventTarget;

@RegisterMod(name = "AntiPotion", desc = "Get rid of bad potion effects", type = Mod.Type.COMBAT)
public class AntiPotion extends Mod{
	
	Minecraft mc = Minecraft.getMinecraft();
		
	@EventTarget
	public void onTick(EventGuiRender e){
		 if(mc.thePlayer.isPotionActive(Potion.blindness.getId())){
	    		mc.thePlayer.addChatComponentMessage(new ChatComponentTranslation("§2[§aHysteria§2]§a Removing blindness potion!"));
	    		this.mc.thePlayer.removePotionEffect(Potion.blindness.getId());
	    	}
		 if(mc.thePlayer.isPotionActive(Potion.confusion.getId())){
	    		mc.thePlayer.addChatComponentMessage(new ChatComponentTranslation("§2[§aHysteria§2]§a Removing confusion potion!"));
	    		this.mc.thePlayer.removePotionEffect(Potion.confusion.getId());
	    	} 
		 if(mc.thePlayer.isPotionActive(Potion.digSlowdown.getId())){
	    		mc.thePlayer.addChatComponentMessage(new ChatComponentTranslation("§2[§aHysteria§2]§a Removing slow mining potion!"));
	    		this.mc.thePlayer.removePotionEffect(Potion.digSlowdown.getId());
	    	} 
		 if(mc.thePlayer.isPotionActive(Potion.harm.getId())){
	    		mc.thePlayer.addChatComponentMessage(new ChatComponentTranslation("§2[§aHysteria§2]§a Removing harming potion!"));
	    		this.mc.thePlayer.removePotionEffect(Potion.harm.getId());
	    	}
		 if(mc.thePlayer.isPotionActive(Potion.hunger.getId())){
	    		mc.thePlayer.addChatComponentMessage(new ChatComponentTranslation("§2[§aHysteria§2]§a Removing hunger potion!"));
	    		this.mc.thePlayer.removePotionEffect(Potion.hunger.getId());
	    	}
		 if(mc.thePlayer.isPotionActive(Potion.moveSlowdown.getId())){
	    		mc.thePlayer.addChatComponentMessage(new ChatComponentTranslation("§2[§aHysteria§2]§a Removing slow movement potion!"));
	    		this.mc.thePlayer.removePotionEffect(Potion.moveSlowdown.getId());
	    	}
		 if(mc.thePlayer.isPotionActive(Potion.poison.getId())){
	    		mc.thePlayer.addChatComponentMessage(new ChatComponentTranslation("§2[§aHysteria§2]§a Removing poison potion!"));
	    		this.mc.thePlayer.removePotionEffect(Potion.poison.getId());
	    	}
		 if(mc.thePlayer.isPotionActive(Potion.weakness.getId())){
	    		mc.thePlayer.addChatComponentMessage(new ChatComponentTranslation("§2[§aHysteria§2]§a Removing weakness potion!"));
	    		this.mc.thePlayer.removePotionEffect(Potion.weakness.getId());
	    	}
		 if(mc.thePlayer.isPotionActive(Potion.wither.getId())){
	    		mc.thePlayer.addChatComponentMessage(new ChatComponentTranslation("§2[§aHysteria§2]§a Removing wither potion!"));
	    		this.mc.thePlayer.removePotionEffect(Potion.wither.getId());
	    	}  
		
	}
	
	@Override
	public void onEnable() {
		
	}

	@Override
	public void onDisable() {
		
	}
}
