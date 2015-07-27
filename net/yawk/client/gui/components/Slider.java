package net.yawk.client.gui.components;

import net.yawk.client.gui.IPanel;
import net.yawk.client.modmanager.values.Value;
import net.yawk.client.utils.GuiUtils;

public class Slider extends Component{
	
	private IPanel panel;
	private Value<Integer> val;
	private int slide = 0;
	private boolean dragging;
	
	public Slider(IPanel panel, Value<Integer> val) {
		this.panel = panel;
		this.val = val;
	}
	
	@Override
	public void draw(int x, int y, int cx, int cy) {
		
		if(dragging){
			slide = x-(cx+slide);
		}
		
		if(slide > panel.getWidth()-15){
			slide = panel.getWidth()-15;
		}
		
		if(slide < 0){
			slide = 0;
		}
		
		GuiUtils.drawRect(cx, cy, cx+panel.getWidth(), cy+getHeight(), 0xFF909090);
		GuiUtils.drawRect(cx+slide, cy, cx+slide+20, cy+getHeight(), 0xFF000000);
	}
	
	@Override
	public void mouseClicked(int x, int y, int cx, int cy) {
		if(mouseOverSlider(x, y, cx, cy)){
			dragging = true;
		}
	}
	
	@Override
	public void mouseReleased(int mouseX, int mouseY, int state) {
		dragging = false;
	}
	
	protected boolean mouseOverSlider(int x, int y, int cx, int cy){
		return x > cx+slide && x < cx+slide+15 && y > cy && y < cy+panel.getHeight();
	}
	
	@Override
	public int getHeight() {
		return 12;
	}

}
