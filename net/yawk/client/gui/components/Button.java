package net.yawk.client.gui.components;

import net.yawk.client.Client;
import net.yawk.client.gui.ColourType;
import net.yawk.client.gui.Window;
import net.yawk.client.utils.Colours;
import net.yawk.client.utils.GuiUtils;

import org.lwjgl.opengl.GL11;

public abstract class Button extends Component{
	
	protected Window win;
	
	public Button(Window win){
		this.win = win;
	}
	
	@Override
	public void draw(int x, int y, int cx, int cy) {
		
		//GuiUtils.drawGradientBorderedRect(cx, cy, cx+win.getWidth(), cy+getHeight(), 1, 0xFF000000, 0xFF0095FF, 0xFF0C53E8);
		
		if(isEnabled()){
			renderText(getText(),
					cx + (isCentered() ? (win.getWidth()/2 - Client.getClient().getFontRenderer().getStringWidth(getText())/2):3),
					cy + getHeight()/2 - Client.getClient().getFontRenderer().FONT_HEIGHT/2,
					mouseOverButton(x, y, cx, cy)? ColourType.HIGHLIGHT.getModifiedColour():ColourType.HIGHLIGHT.getColour(),
							true);
		}else{
			renderText(getText(),
					cx + (isCentered() ? (win.getWidth()/2 - Client.getClient().getFontRenderer().getStringWidth(getText())/2):3),
					cy + getHeight()/2 - Client.getClient().getFontRenderer().FONT_HEIGHT/2,
					mouseOverButton(x, y, cx, cy)? ColourType.TEXT.getModifiedColour():ColourType.TEXT.getColour(),
							true);
		}
	}
	
	protected void renderText(String text, float x, float y, int colour, boolean shadow){
		Client.getClient().getFontRenderer().drawStringWithShadow(text, x, y, colour, shadow);
	}
	
	@Override
	public void mouseClicked(int x, int y, int cx, int cy) {
		if(mouseOverButton(x, y, cx, cy)){
			Client.getClient().getMinecraft().thePlayer.playSound("random.click", 1, 1);
			toggle();
		}
	}
	
	public boolean mouseOverButton(int x, int y, int cx, int cy){
		return x > cx && x < cx+win.getWidth() && y > cy && y < cy+getHeight();
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
