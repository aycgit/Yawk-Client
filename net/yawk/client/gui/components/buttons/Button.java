package net.yawk.client.gui.components.buttons;

import net.yawk.client.Client;
import net.yawk.client.gui.ColourType;
import net.yawk.client.gui.AbstractComponent;
import net.yawk.client.gui.GuiClickable;
import net.yawk.client.gui.IRectangle;
import net.yawk.client.gui.Window;
import net.yawk.client.utils.Colours;
import net.yawk.client.utils.GuiUtils;

import org.lwjgl.opengl.GL11;

public abstract class Button extends AbstractComponent{
		
	public Button(){
		
	}
	
	@Override
	public void draw(int x, int y) {
		
		if(isEnabled()){
			Client.getClient().getFontRenderer().drawStringWithShadow(getText(),
					getX() + (isCentered() ? (rect.getWidth()/2 - Client.getClient().getFontRenderer().getStringWidth(getText())/2):3),
					getY() + getHeight()/2 - Client.getClient().getFontRenderer().FONT_HEIGHT/2,
					mouseOverButton(x, y, getX(), getY())? ColourType.HIGHLIGHT.getModifiedColour():ColourType.HIGHLIGHT.getColour(),
							true);
		}else{
			Client.getClient().getFontRenderer().drawStringWithShadow(getText(),
					getX() + (isCentered() ? (rect.getWidth()/2 - Client.getClient().getFontRenderer().getStringWidth(getText())/2):3),
					getY() + getHeight()/2 - Client.getClient().getFontRenderer().FONT_HEIGHT/2,
					mouseOverButton(x, y, getX(), getY())? ColourType.TEXT.getModifiedColour():ColourType.TEXT.getColour(),
							true);
		}
	}
	
	@Override
	public void mouseClicked(int x, int y) {
		if(mouseOverButton(x, y, getX(), getY())){
			Client.getClient().getMinecraft().thePlayer.playSound("random.click", 1, 1);
			toggle();
		}
	}
	
	public boolean mouseOverButton(int x, int y, int cx, int cy){
		return x > cx && x < cx+rect.getWidth() && y > cy && y < cy+getHeight();
	}
	
	@Override
	public int getHeight() {
		return 12;
	}
	
	public abstract boolean isCentered();
	public abstract boolean isEnabled();
	public abstract void toggle();
	public abstract String getText();
}
