package me.riverhouse.candy.utils.gui;

import me.riverhouse.candy.utils.Wrapper;
import net.minecraft.client.gui.ScaledResolution;

public class ScreenUtils {

	public static int getScreenWidth() {
		ScaledResolution sr = new ScaledResolution(Wrapper.getMinecraft(), Wrapper.getMinecraft().displayWidth, Wrapper.getMinecraft().displayHeight);
		return sr.getScaledWidth();
	}
	
	public static int getScreenHeight() {
		ScaledResolution sr = new ScaledResolution(Wrapper.getMinecraft(), Wrapper.getMinecraft().displayWidth, Wrapper.getMinecraft().displayHeight);
		return sr.getScaledHeight();
	}
	
}
