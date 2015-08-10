package net.yawk.client.mods.building;

import org.lwjgl.input.Keyboard;

import com.darkmagician6.eventapi.EventTarget;

import net.minecraft.client.Minecraft;
import net.yawk.client.events.EventGuiRender;
import net.yawk.client.events.EventKeyPress;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.RegisterMod;
import net.yawk.client.utils.ClientUtils;
import net.yawk.client.utils.GuiUtils;

@RegisterMod(name = "FastSwitch", desc = "Switch items while sprinting", type = Mod.Type.BUILDING)
public class FastSwitch extends Mod{
	
	private Minecraft mc = Minecraft.getMinecraft();
	
	private int index = 9, prevIndex = 9;
	private boolean isExtended;
	
    @EventTarget
	public void onEvent(EventGuiRender event){
    	
    	if(!Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)){
    		
        	String text = "Hold Left CTRL to extend";
        	mc.fontRendererObj.drawString(text, 
        			GuiUtils.scaleManager.scaledResolution.getScaledWidth()/2 - mc.fontRendererObj.getStringWidth(text)/2, 
        			GuiUtils.scaleManager.scaledResolution.getScaledHeight() - 32, 
        			0xFFFF0000);
        	
    		return;
    	}
    	
    	for(int i = 9; i < 36; i++){
    		
    		int x = GuiUtils.scaleManager.scaledResolution.getScaledWidth()/2 + ((i-9) % 9) * 20 - 88;
    		int y = GuiUtils.scaleManager.scaledResolution.getScaledHeight()/4 * 3 + (int)((i-9) / 9)*20;
    		
    		if(i == index){
    			GuiUtils.drawRect(x, y, x+16, y+16, 0x5FFF0000);
    		}else{
    			GuiUtils.drawRect(x, y, x+16, y+16, 0x5FBDBDBD);
    		}
    		
    		GuiUtils.renderItemStack(mc.thePlayer.inventory.getStackInSlot(i), x, y, mc.timer.renderPartialTicks, mc.thePlayer);
    	}
    	
    	prevIndex = index;
    }
    
    @EventTarget
	public void onEvent(EventKeyPress e){
    	
    	if(!Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) && e.getKey() == Keyboard.KEY_UP){
    		isExtended = true;
    	}else if(isExtended){
    		
    		if(e.getKey() == Keyboard.KEY_RIGHT){
    			index++;
    		}else if(e.getKey() == Keyboard.KEY_LEFT){
    			index--;
    		}else if(e.getKey() == Keyboard.KEY_UP){
    			index -= 9;
    		}else if(e.getKey() == Keyboard.KEY_DOWN){
    			index += 9;
    		}else if(e.getKey() == Keyboard.KEY_RETURN){
    			mc.playerController.windowClick(0, index, 0, 1, mc.thePlayer);
    			mc.playerController.updateController();
    		}
    		
    	}
    	
    	if(index < 9){
    		index = prevIndex;
    	}
    	
    	if(index > 35){
    		index = prevIndex;
    	}
    	
    }
}
