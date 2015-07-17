package net.yawk.client.hooks;

import com.darkmagician6.eventapi.EventManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.yawk.client.Client;
import net.yawk.client.events.EventGuiRender;
import net.yawk.client.gui.Window;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.mods.world.HideClient;
import net.yawk.client.utils.ChatColours;
import net.yawk.client.utils.Colours;

public class GuiIngameHook extends GuiIngame{
	
	public GuiIngameHook(Minecraft mc) {
		super(mc);
		Client.init(mc);
		
		hideClientMod = Client.getClient().getHideClientMod();
	}
	
	private Mod hideClientMod;
	
	private EventGuiRender eventGuiRender = new EventGuiRender();
	
	@Override
    public void func_175180_a(float p_175180_1_){
		super.func_175180_a(p_175180_1_);
		
		if(!hideClientMod.isEnabled()){
			Client.getClient().getFontRenderer().drawStringWithShadow("Yawk" + ChatColours.GREEN + " v2.5" + ChatColours.LIGHT_PURPLE + " ("+(Client.getClient().getSession().isPremium()? "Premium":"Beta")+")", 3, 2, 0xFFFFFFFF, true);
		}
		
		if(Client.getClient().getMinecraft().currentScreen == null){
			for(Window win : Client.getClient().gui.windows){
				if(win.pinned){
					win.draw(0, 0);
				}
			}
		}
		
		EventManager.call(eventGuiRender);
	}
}
