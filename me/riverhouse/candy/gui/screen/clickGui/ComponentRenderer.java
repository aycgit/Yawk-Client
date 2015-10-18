package me.riverhouse.candy.gui.screen.clickGui;

import org.lwjgl.opengl.GL11;

public class ComponentRenderer {

	private ComponentType type;
	public Theme theme;
	
	
	public ComponentRenderer(ComponentType type, Theme theme){
		this.type = type;
		this.theme = theme;
	}

	public void drawComponent(Component c, int mouseX, int mouseY){

	}

	public void doInteractions(Component c, int mouseX, int mouseY){

	}
	
}
