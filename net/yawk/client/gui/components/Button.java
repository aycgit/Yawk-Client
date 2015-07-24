package net.yawk.client.gui.components;

import net.yawk.client.Client;
import net.yawk.client.gui.ColourType;
import net.yawk.client.gui.GuiClickable;
import net.yawk.client.gui.IPanel;
import net.yawk.client.gui.Window;
import net.yawk.client.utils.Colours;
import net.yawk.client.utils.GuiUtils;

import org.lwjgl.opengl.GL11;

public abstract class Button extends Component{
	
	protected IPanel win;
	
	public Button(IPanel win){
		this.win = win;
	}
	
	@Override
	public void draw(int x, int y, int cx, int cy) {
		GuiClickable.theme.getButtonRenderer().renderButton(win, this, x, y, cx, cy);
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
		return GuiClickable.theme.getButtonRenderer().getThemeHeight();
	}
	
	public abstract boolean isCentered();
	public abstract boolean isEnabled();
	public abstract void toggle();
	public abstract String getText();
}
