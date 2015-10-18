package me.riverhouse.candy.gui;

import me.riverhouse.candy.Candy;
import me.riverhouse.candy.event.EventSystem;
import me.riverhouse.candy.event.events.EventRenderOverlay;
import me.riverhouse.candy.gui.overlay.CandyOverlay;
import me.riverhouse.candy.gui.overlay.overlays.ArrayListOverlay;
import me.riverhouse.candy.utils.Wrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;

public class GuiIngameHook extends GuiIngame {

	private Minecraft mc = Wrapper.getMinecraft();

	public GuiIngameHook(Minecraft mcIn) {
		super(mcIn);
	}

	@Override
	public void func_175180_a(float p_175180_1_) {
		super.func_175180_a(p_175180_1_);
		EventSystem.call(new EventRenderOverlay());

		for(CandyOverlay overlay : Candy.getCandy().getManagers().getOverlayManager().getOverlays()){
			ScaledResolution scaledRes = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);

			float scale = scaledRes.getScaleFactor() / (float) Math.pow(scaledRes.getScaleFactor(), 2.0D);

			GlStateManager.pushMatrix();
			GlStateManager.translate(0.0F, 0.0F, 1000.0F);
			GlStateManager.scale(scale * 2, scale * 2, scale * 2);

			if(!mc.gameSettings.showDebugInfo) overlay.render();

			GlStateManager.popMatrix();
		}
	}

}
