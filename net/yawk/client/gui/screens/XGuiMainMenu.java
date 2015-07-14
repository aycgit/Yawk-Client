package net.yawk.client.gui.screens;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.resources.I18n;
import net.yawk.client.utils.ChatColours;
import net.yawk.client.utils.GuiUtils;

public class XGuiMainMenu extends GuiMainMenu{

	@Override
	public void initGui() {
		super.initGui();
		
        int var3 = this.height / 4 + 48;
        this.buttonList.add(new GuiButton(6, this.width / 2 + 2, var3 + 72 + 12 + 24, 98, 20, ChatColours.GREEN+"Hysteria menu"));
        this.buttonList.add(new GuiButton(7, this.width / 2 - 100, var3 + 72 + 12 + 24, 98, 20, ChatColours.RED+"Premium"));
	}
}

