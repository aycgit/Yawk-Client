package net.yawk.client.utils;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

public class Scissor {

	public static ScaledResolution getScaledResolution() {
		final ScaledResolution sr = new ScaledResolution( Minecraft.getMinecraft(),
				Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight );
		return sr;
	}

	public static void enableScissoring() {
		GL11.glEnable( GL11.GL_SCISSOR_TEST );
	}

	/**
	 * @author Jonalu
	 * @return Used for nifty things ;)
	 * */
	public static void scissor( final double x, final double y, final double w, final double h ) {
		final ScaledResolution sr = getScaledResolution();
		final int factor = sr.getScaleFactor();

		final double x2 = x + w, y2 = y + h;

		GL11.glScissor( ( int ) ( x * factor ), ( int ) ( ( sr.getScaledHeight() - y2 ) * factor ),
				( int ) ( ( x2 - x ) * factor ), ( int ) ( ( y2 - y ) * factor ) );
	}

	public static void disableScissoring() {
		GL11.glDisable( GL11.GL_SCISSOR_TEST );
	}

}
