package me.riverhouse.candy.gui.screen.clickGui.themes.Simple;

import java.awt.Color;

import me.riverhouse.candy.gui.screen.clickGui.Component;
import me.riverhouse.candy.gui.screen.clickGui.ComponentRenderer;
import me.riverhouse.candy.gui.screen.clickGui.ComponentType;
import me.riverhouse.candy.gui.screen.clickGui.Theme;
import me.riverhouse.candy.gui.screen.clickGui.parts.Slider;
import me.riverhouse.candy.utils.gui.GuiUtils;

public class SimpleSliderUI extends ComponentRenderer{

	public SimpleSliderUI(Theme theme) {
		super(ComponentType.SLIDER, theme);
	}

	@Override
	public void drawComponent(Component c, int mouseX, int mouseY) {
		Slider s = (Slider) c;

		int width = (int) ((s.getArea().getWidth()) * s.getPercent());
		
		GuiUtils.setColor(new Color(51,173,255));
		
		theme.fontRenderer.drawString(s.getText(), s.getX() + 2, s.getY() + 2, Color.WHITE.getRGB());
		theme.fontRenderer.drawString(s.getValue() + "", s.getX() + s.getArea().width - theme.fontRenderer.getStringWidth(s.getValue() + "") - 2, s.getY() + 2, Color.WHITE.getRGB());
		
		GuiUtils.drawRect(s.getX(), s.getY() + s.getArea().height / 2 + 3,(int) (s.getX() + (width) + 1),(s.getY() + s.getArea().height / 2) + 6, Color.WHITE);
		GuiUtils.drawRect(s.getX(), s.getY() + s.getArea().height / 2 + 3,(int) (s.getX() + (width)),(s.getY() + s.getArea().height / 2) + 6, new Color(51,173,255));
	}

}
