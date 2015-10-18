package me.riverhouse.candy.gui.screen.clickGui.themes.Simple;

import java.awt.Color;

import me.riverhouse.candy.gui.screen.clickGui.Component;
import me.riverhouse.candy.gui.screen.clickGui.ComponentRenderer;
import me.riverhouse.candy.gui.screen.clickGui.ComponentType;
import me.riverhouse.candy.gui.screen.clickGui.Theme;
import me.riverhouse.candy.gui.screen.clickGui.parts.Button;
import me.riverhouse.candy.utils.gui.GuiUtils;

public class SimpleButtonUI extends ComponentRenderer {

	public SimpleButtonUI(Theme theme){
		super(ComponentType.BUTTON, theme);
	}

	public void drawComponent(Component c, int mouseX, int mouseY){
		Button b = (Button) c;
		String text = b.getText();

		Color color = new Color(71,71,71);
		
		if(b.isEnabled() || b.isMouseOver(mouseX, mouseY)){
			color = new Color(33,57,67);
		}else{
			color = new Color(71,71,71);
		}

		GuiUtils.setColor(color);
		
		GuiUtils.drawRect(b.getX() - 1, b.getY(), b.getX() + b.getArea().width + 1, b.getY() + b.getArea().height + 1, color);
		
		GuiUtils.setColor(Color.WHITE);
		
		theme.fontRenderer.drawString(text, b.getX() + (b.getArea().width / 2 - theme.fontRenderer.getStringWidth(text) / 2), b.getY() + theme.fontRenderer.FONT_HEIGHT/2,
				(b.isEnabled() || b.isMouseOver(mouseX, mouseY)) ? new Color(81, 121, 127).getRGB() : new Color(41,41,41).getRGB());
	}

}
