package me.riverhouse.candy.gui.screen.clickGui.themes.Simple;

import java.awt.Font;

import me.riverhouse.candy.gui.screen.clickGui.ComponentType;
import me.riverhouse.candy.gui.screen.clickGui.Theme;
import me.riverhouse.candy.gui.screen.clickGui.UnicodeFontRenderer;

public class SimpleThemeUI extends Theme {

	public SimpleThemeUI(){
		super("Simple");
		this.fontRenderer = new UnicodeFontRenderer(new Font("Tahoma", Font.PLAIN, 17));
		this.addRenderer(ComponentType.FRAME, new SimpleFrameUI(this));
		this.addRenderer(ComponentType.BUTTON, new SimpleButtonUI(this));
		this.addRenderer(ComponentType.SLIDER, new SimpleSliderUI(this));
	}

}
