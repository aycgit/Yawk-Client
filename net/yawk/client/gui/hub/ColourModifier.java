package net.yawk.client.gui.hub;

import java.util.HashMap;

import net.minecraft.util.MathHelper;

public class ColourModifier {
	
	private HashMap<Integer, Integer> darkColours = new HashMap<Integer, Integer>();
	
	public int getDarkColour(int paramColour){
		
		if(darkColours.containsKey(paramColour)){
			return darkColours.get(paramColour);
		}
		
		//float a = (float)(paramColor >> 24 & 0xFF) / 255F;
		int r = paramColour >> 16 & 0xFF;
		int g = paramColour >> 8 & 0xFF;
		int b = paramColour & 0xFF;
		
		r *= 0.8;
		g *= 0.8;
		b *= 0.8;
		
		String rHex = Integer.toString(r, 16);
		String gHex = Integer.toString(g, 16);
		String bHex = Integer.toString(b, 16);
		
		String hexValue = (rHex.length() == 2 ? "" + rHex : "0" + rHex)
				+ (gHex.length() == 2 ? "" + gHex : "0" + gHex)
				+ (bHex.length() == 2 ? "" + bHex : "0" + bHex);
		
		int intValue = Integer.parseInt(hexValue, 16); 
		
		darkColours.put(paramColour, intValue);
		
		return intValue;
	}
	
	public int getDarkColourWithAlpha(int paramColour){
		
		if(darkColours.containsKey(paramColour)){
			return darkColours.get(paramColour);
		}
		
		int a = paramColour >> 24 & 0xFF;
		int r = paramColour >> 16 & 0xFF;
		int g = paramColour >> 8 & 0xFF;
		int b = paramColour & 0xFF;
		
		r *= 0.7;
		g *= 0.7;
		b *= 0.7;
		
		String aHex = Integer.toString(a, 16);
		String rHex = Integer.toString(r, 16);
		String gHex = Integer.toString(g, 16);
		String bHex = Integer.toString(b, 16);
		
		String hexValue = (aHex.length() == 2 ? "" + aHex : "0" + aHex)
				+ (rHex.length() == 2 ? "" + rHex : "0" + rHex)
				+ (gHex.length() == 2 ? "" + gHex : "0" + gHex)
				+ (bHex.length() == 2 ? "" + bHex : "0" + bHex);
		
		int intValue = Integer.parseInt(hexValue, 16); 
		
		darkColours.put(paramColour, intValue);
		
		return intValue;
	}
	
	public void clear(){
		darkColours.clear();
	}
}
