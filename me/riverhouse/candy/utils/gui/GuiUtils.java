package me.riverhouse.candy.utils.gui;

import java.awt.Color;
import java.util.ArrayList;

import net.minecraft.client.gui.Gui;

import org.lwjgl.opengl.GL11;

public class GuiUtils {

	public static void drawRect(int x, int y, int x1, int y1, Color color){
		Gui.drawRect(x, y, x1, y1, color.getRGB());
	}
	
	public static int getMiddle(int x1, int x2){
		return (x1 + x2) / 2;
	}
	
	public static void drawBorderedRect(int x, int y, int x1, int y1, int size, int borderC, int insideC)
	{
		Gui.drawRect(x + size, y + size, x1 - size, y1 - size, insideC);
		Gui.drawRect(x, y + size, x1, y, borderC);
		Gui.drawRect(x, y, x + size, y1, borderC);
		Gui.drawRect(x1, y1, x1 - size, y + size, borderC);
		Gui.drawRect(x, y1 - size, x1, y1, borderC);
	}
	
	public static void drawGradiantRect(int x, int y, int x1, int y1, Color color, Color color1, int steps){
        for (int i = 0; i < steps; i++) {
            float ratio = (float) i / (float) steps;
            int red = (int) (color1.getRed() * ratio + color.getRed() * (1 - ratio));
            int green = (int) (color1.getGreen() * ratio + color.getGreen() * (1 - ratio));
            int blue = (int) (color1.getBlue() * ratio + color.getBlue() * (1 - ratio));
            Color stepColor = new Color(red, green, blue);
        }
	}
	
	public static void setColor(Color c){
		GL11.glColor4d(c.getRed() / 255f, c.getGreen() / 255f, c.getBlue() / 255f, c.getAlpha() / 255f);
	}
	
	public static void drawTri(double x1, double y1, double x2, double y2, double x3, double y3, double width, Color c){
		GL11.glEnable(3042);
		GL11.glDisable(3553);
		GL11.glEnable(2848);
		GL11.glBlendFunc(770, 771);
		setColor(c);

		GL11.glLineWidth((float) width);

		GL11.glBegin(3);

		GL11.glVertex2d(x1, y1);

		GL11.glVertex2d(x2, y2);

		GL11.glVertex2d(x3, y3);

		GL11.glEnd();
		GL11.glDisable(2848);
		GL11.glEnable(3553);
		GL11.glDisable(3042);
	}
	
	public static ArrayList<Color> getRainbowGradiant(ArrayList<Color> colors, int stepsPerColor){
		ArrayList<Color> gradiant = new ArrayList<Color>();
		
		for(Color color : colors){
			Color color1;
			
			if(colors.indexOf(color) + 1 > colors.size() - 1){
				color1 = colors.get(0);
			}else{
				color1 = colors.get(colors.indexOf(color) + 1);
			}
			
			for (int i = 0; i < stepsPerColor; i++) {
	            float ratio = (float) i / (float) stepsPerColor;
	            int red = (int) (color1.getRed() * ratio + color.getRed() * (1 - ratio));
	            int green = (int) (color1.getGreen() * ratio + color.getGreen() * (1 - ratio));
	            int blue = (int) (color1.getBlue() * ratio + color.getBlue() * (1 - ratio));
	            Color stepColor = new Color(red, green, blue);
	            gradiant.add(stepColor);
	        }
			
		}
		
		return gradiant;
	}
	
}
