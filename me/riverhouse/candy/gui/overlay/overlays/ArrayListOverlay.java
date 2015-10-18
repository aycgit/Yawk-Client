package me.riverhouse.candy.gui.overlay.overlays;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import me.riverhouse.candy.Candy;
import me.riverhouse.candy.gui.overlay.CandyOverlay;
import me.riverhouse.candy.module.ModType;
import me.riverhouse.candy.module.Module;
import me.riverhouse.candy.utils.Wrapper;
import me.riverhouse.candy.utils.gui.GuiUtils;
import me.riverhouse.candy.utils.gui.ScreenUtils;

public class ArrayListOverlay extends CandyOverlay {

	@Override
	public void render() {
		int y = 2;
		for(Module m : Candy.getCandy().getManagers().getModuleManager().getModules()){			
			if(m.getState() && m.getType() != ModType.UNLISTED){
				int right = ScreenUtils.getScreenWidth() - Wrapper.getFontRenderer().getStringWidth(m.getDisplayName()) - 2;
				Wrapper.getFontRenderer().func_175063_a(m.getDisplayName(), right, y + 1, m.getModData().getColor().getRGB());
				
				drawRect(ScreenUtils.getScreenWidth() - 1, y - 1, ScreenUtils.getScreenWidth(), y + Wrapper.getFontRenderer().FONT_HEIGHT + 1, m.getModData().getColor());
				
				y += Wrapper.getFontRenderer().FONT_HEIGHT + 1;
			} 
		}
		
		GuiUtils.setColor(Color.WHITE);
	}
	
}
