package me.riverhouse.candy.gui.screen.clickGui;

import java.util.HashMap;

import net.minecraft.client.gui.FontRenderer;

public class Theme {

	private HashMap<ComponentType, ComponentRenderer> renderers = new HashMap<ComponentType, ComponentRenderer>();
	private String name;
	public FontRenderer fontRenderer;
	private int frameBoxHeight = 19;
	
	public Theme(String name){
		this.name = name;
	}

	public HashMap<ComponentType, ComponentRenderer> getRenderers(){
		return renderers;
	}

	public String getName(){
		return name;
	}

	public void addRenderer(ComponentType type, ComponentRenderer renderer){
		this.renderers.put(type, renderer);
	}
	
	public void setFrameBoxHeight(int frameBoxHeight){
		this.frameBoxHeight = frameBoxHeight;
	}
	
	public int getFrameBoxHeight(){
		return frameBoxHeight;
	}
}
