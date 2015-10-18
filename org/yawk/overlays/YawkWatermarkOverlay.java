package org.yawk.overlays;

import me.riverhouse.candy.gui.overlay.CandyOverlay;
import me.riverhouse.candy.utils.Wrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.EnumChatFormatting;

import org.lwjgl.opengl.GL11;

public class YawkWatermarkOverlay extends CandyOverlay {
	
	@Override
	public void render() {
		Minecraft mc = Wrapper.getMinecraft();
		FontRenderer fr = Wrapper.getFontRenderer();
		GL11.glPushMatrix();
		GL11.glScaled(1.75, 1.75, 1);
		fr.func_175063_a("Yawk2", 2, 2, 0x30D633);
		GL11.glPopMatrix();
	}
}