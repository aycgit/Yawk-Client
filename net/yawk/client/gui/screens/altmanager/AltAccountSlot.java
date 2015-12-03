package net.yawk.client.gui.screens.altmanager;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.yawk.client.Client;

public class AltAccountSlot extends GuiSlot {
    final AltAccountSwitch parentScreen;

    public AltAccountSlot(AltAccountSwitch PAS) {
        super(Client.getClient().getMinecraft(), PAS.width, PAS.height, 32, PAS.height - 64, 24);
        parentScreen = PAS;
    }

    @Override
    protected int getSize() {
        return parentScreen.getAccountList().size();
    }

    protected boolean isSelected(int var1) {
        return var1 == parentScreen.getSelectedAccount();
    }

    protected int getContentHeight() {
        return parentScreen.getAccountList().size() * 24;
    }

	protected void drawBackground() {
        parentScreen.drawDefaultBackground();
    }

	@Override
	protected void elementClicked(int var1, boolean var2, int var3, int var4) {
        parentScreen.setSelectedAccount(var1);
        boolean flag2 = parentScreen.getSelectedAccount() >= 0 && parentScreen.getSelectedAccount() < getSize();
        parentScreen.getButtonSelect().enabled = flag2;
        parentScreen.getButtonEdit().enabled = flag2;
        parentScreen.getButtonDelete().enabled = flag2;
        if (var2 && flag2) {
            parentScreen.login(var1);
        }
	}
	
	@Override
	protected void drawSlot(int var1, int var2, int var3, int var4, int p_180791_5_, int p_180791_6_) {
        String[] account = parentScreen.getAccountList().get(var1).split(":");
        String name = account[0];
        boolean cracked = account.length == 1;
        boolean hasRep = account.length >= 3;
        FontRenderer f = Client.getClient().getMinecraft().fontRendererObj;
        f.drawString(name , var2 + 2, var3 + 1, 0xffffff);
        f.drawString(cracked ? EnumChatFormatting.DARK_GRAY+"Non-Premium" : EnumChatFormatting.GRAY+"Premium", var2 + 2, var3 + 12, cracked ? 0x303030 : 0x808080);
	}
}