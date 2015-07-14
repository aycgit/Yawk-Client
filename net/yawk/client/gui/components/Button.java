package net.yawk.client.gui.components;

import net.yawk.client.Client;
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
		
		//GuiUtils.drawRect(cx, cy, cx+Window.TITLE_WIDTH, cy+getHeight(), mouseOverButton(x, y, cx, cy) ? 0x5F00A6FF:0x5FDDFF00);
		
		if(isEnabled()){
			Client.getClient().getFontRenderer().drawStringWithShadow(getText(),
					cx + (isCentered() ? (win.getWidth()/2 - Client.getClient().getFontRenderer().getStringWidth(getText())/2):3),
					cy + getHeight()/2 - Client.getClient().getFontRenderer().FONT_HEIGHT/2,
					mouseOverButton(x, y, cx, cy)? 0xFF6AFF6D:Colours.BRIGHT_TEXT,
							true);
		}else{
			Client.getClient().getFontRenderer().drawStringWithShadow(getText(),
					cx + (isCentered() ? (win.getWidth()/2 - Client.getClient().getFontRenderer().getStringWidth(getText())/2):3),
					cy + getHeight()/2 - Client.getClient().getFontRenderer().FONT_HEIGHT/2,
					mouseOverButton(x, y, cx, cy)? 0xFF9FFF9F:0xFFFFFFFF,
							true);
		}
	}
	
	@Override
	public void mouseClicked(int x, int y, int cx, int cy) {
		if(mouseOverButton(x, y, cx, cy)){
			Client.getClient().getMinecraft().thePlayer.playSound("random.click", 1, 1);
			toggle();
		}
	}
	
	private boolean mouseOverButton(int x, int y, int cx, int cy){
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
