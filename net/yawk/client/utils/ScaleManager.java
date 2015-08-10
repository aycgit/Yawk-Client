package net.yawk.client.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

public class ScaleManager {

	private Minecraft mc = Minecraft.getMinecraft();
	
	private int lastGuiSize, scaleFactor;
	
    public ScaledResolution scaledResolution = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
    
	public int getScaleFactor(){
		if(mc.gameSettings.guiScale != lastGuiSize){
			scaledResolution = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
	        scaleFactor = scaledResolution.getScaleFactor();
	        
	        lastGuiSize = mc.gameSettings.guiScale;
		}
		
		return scaleFactor;
	}
}
