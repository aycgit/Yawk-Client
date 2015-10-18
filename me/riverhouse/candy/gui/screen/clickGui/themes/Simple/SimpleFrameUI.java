package me.riverhouse.candy.gui.screen.clickGui.themes.Simple;

import java.awt.Color;
import java.awt.Dimension;

import me.riverhouse.candy.gui.screen.clickGui.Component;
import me.riverhouse.candy.gui.screen.clickGui.ComponentRenderer;
import me.riverhouse.candy.gui.screen.clickGui.ComponentType;
import me.riverhouse.candy.gui.screen.clickGui.Theme;
import me.riverhouse.candy.gui.screen.clickGui.parts.Frame;
import me.riverhouse.candy.utils.gui.GuiUtils;

public class SimpleFrameUI extends ComponentRenderer {

	public SimpleFrameUI(Theme theme){
		super(ComponentType.FRAME, theme);
		theme.setFrameBoxHeight(19);
	}

	public void drawComponent(Component c, int mouseX, int mouseY){
		Frame f = (Frame) c;

		GuiUtils.setColor(new Color(36,36,36));
		
		int fontHeight = theme.fontRenderer.FONT_HEIGHT;
		Dimension area = f.getArea();

		if(f.isMaximized()){
			GuiUtils.drawRect(f.getX(), f.getY(), f.getX() + area.width, f.getY() + area.height + 7, new Color(36,36,36));
			GuiUtils.drawRect(f.getX(), f.getY(), f.getX() + area.width, f.getY() + area.height, new Color(71,71,71));
			
			double y = (f.getArea().getHeight() - 19 - 5) * ((double) f.getScrollAmmount() / (double) f.getMaxScroll());

			y += f.getY() + 19;

			GuiUtils.drawRect((int) (f.getX() + area.getWidth() - 3), (int) y, (int) (f.getX() + f.getArea().getWidth()), (int) (y + 5), new Color(51,171,255));
		}

		GuiUtils.drawRect(f.getX(), f.getY(), f.getX() + area.width, f.getY() + 19, new Color(36,36,36));
 
		if(f.isMaximizible()){
			Color color = new Color(36, 36, 36);

			if(mouseX >= f.getX() + area.width - 19 && mouseY >= f.getY() && mouseY <= f.getY() + 19 && mouseX <= f.getX() + area.width || f.isMaximized()){
				color = new Color(15,15,15);
			}

			GuiUtils.drawRect(f.getX() + area.width - 19, f.getY(), f.getX() + area.width, f.getY() + 19, color);			
		}
		
		if(f.isPinnable()){
			Color color = new Color(36, 36, 36);

			if(mouseX >= f.getX() + area.width - 38 && mouseY >= f.getY() && mouseY <= f.getY() + 19 && mouseX <= f.getX() + area.width - 19 || f.isPinned()){
				color = new Color(27,27,27);
			}

			GuiUtils.drawRect(f.getX() + area.width - 38, f.getY(), f.getX() + area.width - 19, f.getY() + 19, color);		
		}

		GuiUtils.drawTri(f.getX(), f.getY() + 19, f.getX(), f.getY() + 19, f.getX() + f.getArea().getWidth(), f.getY() + 19, 1.5, new Color(160,160,160));
		if(f.isMaximized()) GuiUtils.drawTri(f.getX(), f.getY() + area.height, f.getX(), f.getY() + area.height, f.getX() + f.getArea().getWidth(), f.getY() + area.height, 1, new Color(160,160,160));
		
		theme.fontRenderer.drawString(f.getText(), f.getX() + 4, GuiUtils.getMiddle(f.getY() - 6, (int) (f.getY() + 18)) - (theme.fontRenderer.FONT_HEIGHT / 2), new Color(255,255,255,100).getRGB());
	}

	public void doInteractions(Component c, int mouseX, int mouseY){
		Frame f = (Frame) c;

		int fontHeight = theme.fontRenderer.FONT_HEIGHT;
		Dimension area = f.getArea();

		if(mouseX >= f.getX() + area.width - 19 && mouseY >= f.getY() && mouseY <= f.getY() + 19 && mouseX <= f.getX() + area.width)
			f.setMaximized(!f.isMaximized());
		
		if(mouseX >= f.getX() + area.width - 38 && mouseY >= f.getY() && mouseY <= f.getY() + 19 && mouseX <= f.getX() + area.width - 19)
			f.setPinned(!f.isPinned());
	}

}
