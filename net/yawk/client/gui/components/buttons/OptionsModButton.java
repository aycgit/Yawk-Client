package net.yawk.client.gui.components.buttons;

import java.util.ArrayList;
import java.util.List;

import net.yawk.client.Client;
import net.yawk.client.gui.Canvas;
import net.yawk.client.gui.ColourType;
import net.yawk.client.gui.AbstractComponent;
import net.yawk.client.gui.Window;
import net.yawk.client.gui.WindowSubPanel;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.values.AbstractValue;
import net.yawk.client.utils.GuiUtils;

public class OptionsModButton extends ModButton{
	
	private boolean extended;
	private WindowSubPanel panel;
	
	public OptionsModButton(Mod mod) {
		super(mod);
	}
	
	@Override
	public void init() {
		
		panel = new WindowSubPanel(rect, this, 12);
		
		for(AbstractValue option : mod.getOptions()){
			panel.addComponent(option.getComponent(panel));
		}
	}
	
	@Override
	public void draw(int x, int y) {
		
		if(mod.isEnabled()){
			Client.getClient().getFontRenderer().drawStringWithShadow(getText(),
					getX()+3,
					getY()+2,
					mouseOverButton(x, y, getX(), getY())? ColourType.HIGHLIGHT.getModifiedColour():ColourType.HIGHLIGHT.getColour(),
					true);
		}else{
			Client.getClient().getFontRenderer().drawStringWithShadow(getText(),
					getX()+3,
					getY()+2,
					mouseOverButton(x, y, getX(), getY())? ColourType.TEXT.getModifiedColour():ColourType.TEXT.getColour(),
					true);
		}
		
		GuiUtils.drawRect(getX()+rect.getWidth()-10, getY()+2, getX()+rect.getWidth()-2, getY()+10, extended? 0x4FFFFFFF:0x2FFFFFFF);
		
		if(extended){
			panel.draw(x, y);
		}
	}

	@Override
	public void mouseClicked(int x, int y) {
		
		if(mouseOverExtendButton(x, y, getX(), getY())){
			extended = !extended;
			Client.getClient().getMinecraft().thePlayer.playSound("random.click", 1, 1);
			rect.updateHeight();
		}else{
			super.mouseClicked(x, y);
		}
		
		if(extended){
			panel.mouseClicked(x, y);
		}
	}
	
	@Override
	public void mouseReleased(int mouseX, int mouseY, int state) {
		if(extended){
			panel.mouseReleased(mouseX, mouseY, state);
		}
	}
	
	public boolean mouseOverExtendButton(int x, int y, int cx, int cy){
		return x >= cx+rect.getWidth()-10 && x <= cx+rect.getWidth()-2 && y >= cy+2 && y <= cy+10;
	}
	
	@Override
	public boolean mouseOverButton(int x, int y, int cx, int cy){
		return x > cx && x < cx+rect.getWidth() && y > cy && y < cy+12;
	}
	
	@Override
	public int getHeight() {
		
		if(extended){
			return super.getHeight() + panel.getHeight() + 1;
		}else{
			return super.getHeight();
		}
	}
}