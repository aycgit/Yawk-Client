package net.yawk.client.gui.components.scrolling;

import java.util.ArrayList;
import java.util.List;

import net.yawk.client.gui.AbstractComponent;
import net.yawk.client.gui.IRectangle;
import net.yawk.client.gui.Window;
import net.yawk.client.utils.GuiUtils;
import net.yawk.client.utils.Scissor;

import org.lwjgl.opengl.GL11;

public class ScrollPane extends AbstractComponent implements IRectangle{
	
	protected List<AbstractComponent> components = new ArrayList<AbstractComponent>();
	protected int height, viewportHeight, dragged, mouseYOffset, BAR_HEIGHT = 12, BAR_WIDTH = 4;
	private boolean dragging;
	
	public ScrollPane(int viewportHeight){
		this.viewportHeight = viewportHeight;
	}
	
	@Override
	public void draw(int x, int y) {
		
		if(dragging){
			
			dragged = y - getY() + mouseYOffset;
			
			if(dragged > viewportHeight-BAR_HEIGHT){
				dragged = viewportHeight-BAR_HEIGHT;
			}
			
			if(dragged < 0){
				dragged = 0;
			}
		}
		
		GuiUtils.drawRect(getX() + rect.getWidth() - BAR_WIDTH - 1, getY(), getX() + rect.getWidth(), getY() + viewportHeight, 0x1FCFCFCF);
		
		GuiUtils.drawRect(getX() + rect.getWidth() - BAR_WIDTH - 1, getY() + dragged, getX() + rect.getWidth(), getY() + dragged + BAR_HEIGHT, 0x4FFFFFFF);
		
		int drag = getScrollHeight();
		
		Scissor.enableScissoring();
		Scissor.scissor(getX(), getY(), rect.getWidth(), viewportHeight);
		
		GL11.glTranslatef(0, -drag, 0);
				
		if(isWithinScrollPane(x, y, getX(), getY())){
			for(AbstractComponent c : components){
				c.draw(x, y + drag);
			}
		}else{
			for(AbstractComponent c : components){
				c.draw(-999, -999);
			}
		}
		
		GL11.glTranslatef(0, drag, 0);
		
		Scissor.disableScissoring();
	}
	
	public int getScrollHeight(){
		return (int) ((float)(dragged/(float)(height-BAR_HEIGHT)) * (height-viewportHeight));
	}
	
	public boolean mouseOverBar(int x, int y, int cx, int cy){
		return x >= cx + rect.getWidth() - BAR_WIDTH - 1 && x < cx + rect.getWidth() && y > cy + dragged && y <= cy + dragged + BAR_HEIGHT;
	}
	
	public boolean mouseOverBarArea(int x, int y, int cx, int cy){
		return x >= cx + rect.getWidth() - BAR_WIDTH - 1 && x <= cx + rect.getWidth() && y >= cy && y <= cy + viewportHeight;
	}
	
	private boolean isWithinScrollPane(int x, int y, int cx, int cy){
		return x > cx && x < cx+rect.getWidth() - BAR_WIDTH - 1 && y > cy && y < cy + viewportHeight;
	}
	
	@Override
	public void mouseClicked(int x, int y) {
		if(mouseOverBar(x, y, getX(), getY())){
			dragging = true;
			
			mouseYOffset = (getY() + dragged) - y;
		}else{
			
			int h = 0;
			int drag = getScrollHeight();
			
			if(isWithinScrollPane(x, y, getX(), getY())){
				for(AbstractComponent c : components){
					c.mouseClicked(x, y + drag);
					h += c.getHeight();
				}
			}else{
				for(AbstractComponent c : components){
					c.mouseClicked(-999, -999);
					h += c.getHeight();
				}
			}
		}
	}
	
	@Override
	public void keyPress(int key, char c) {
		for(AbstractComponent comp : components){
			comp.keyPress(key, c);
		}
	}
	
	@Override
	public void mouseReleased(int mouseX, int mouseY, int state) {
		dragging = false;
	}
	
	@Override
	public int getHeight() {
		return viewportHeight;
	}
	
	@Override
	public int getWidth() {
		return rect.getWidth() - BAR_WIDTH - 1;
	}
	
	public void addComponent(AbstractComponent c){
		this.components.add(c);
		c.setRectangle(this);
		c.init();
		updateHeight();
	}
	
	@Override
	public void updateHeight(){
		
		height = 0;
		
		for(AbstractComponent component : components){
			component.setY(height);
			height += component.getHeight();
		}
	}

	@Override
	public int getRectX() {
		return getX();
	}

	@Override
	public int getRectY() {
		return getY();
	}
}
