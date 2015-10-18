package me.riverhouse.candy.gui.screen.clickGui;

import org.lwjgl.opengl.GL11;

public class ScissorUtils {

	public static void enableScissoring(){
		GL11.glEnable(GL11.GL_SCISSOR_TEST);
	}

	public static void disableScissoring(){
		GL11.glDisable(GL11.GL_SCISSOR_TEST);
	}

}
