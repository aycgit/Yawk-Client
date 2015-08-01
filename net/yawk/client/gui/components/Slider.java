package net.yawk.client.gui.components;

import net.yawk.client.Client;
import net.yawk.client.gui.Component;
import net.yawk.client.gui.IPanel;
import net.yawk.client.modmanager.values.SliderValue;
import net.yawk.client.modmanager.values.Value;
import net.yawk.client.utils.GuiUtils;

public class Slider extends Component{
	
	private IPanel panel;
	private SliderValue val;
	private int slide, mouseXOffset;
	private boolean dragging;
	
	public Slider(IPanel panel, SliderValue val) {
		this.panel = panel;
		this.val = val;
	}
	
	@Override
	public void draw(int x, int y, int cx, int cy) {
		
		if(dragging){
			
			slide = x + mouseXOffset;
			
			double factor = (slide - cx)/(double)panel.getWidth();
			double range = val.getUpperBound() - val.getLowerBound();
			double addition = range*factor;
			
			val.setValue(addition+val.getLowerBound());
		}
		
		if(slide > cx+panel.getWidth()-15){
			slide = cx+panel.getWidth()-15;
		}
		
		if(slide < cx){
			slide = cx;
		}
		
		GuiUtils.drawRect(cx, cy, cx+panel.getWidth(), cy+getHeight(), 0x2FDFDFDF);
		GuiUtils.drawRect(slide, cy, slide+15, cy+getHeight(), 0x7F9F9F9F);
		
		String displayString = val.getName()+": "+val.getValue();
		
		Client.getClient().getFontRenderer().drawString(displayString, cx + panel.getWidth()/2 - Client.getClient().getFontRenderer().getStringWidth(displayString)/2, cy + 2, 0xFFFFFFFF);
	}
	
	@Override
	public void mouseClicked(int x, int y, int cx, int cy) {
		if(mouseOverSlider(x, y, cx, cy)){
			dragging = true;
			mouseXOffset = slide - x;
		}
	}
	
	@Override
	public void mouseReleased(int mouseX, int mouseY, int state) {
		dragging = false;
	}
	
	protected boolean mouseOverSlider(int x, int y, int cx, int cy){
		return x > slide && x < slide+15 && y > cy && y < cy+panel.getHeight();
	}
	
	@Override
	public int getHeight() {
		return 12;
	}

}
