package me.riverhouse.candy.gui.screen.clickGui.parts;

import java.awt.Dimension;
import java.util.ArrayList;

import me.riverhouse.candy.gui.screen.clickGui.Component;
import me.riverhouse.candy.gui.screen.clickGui.ComponentType;
import me.riverhouse.candy.gui.screen.clickGui.FrameInfo;
import me.riverhouse.candy.gui.screen.clickGui.Theme;

public class Frame extends Component {

	private ArrayList<Component> components = new ArrayList<Component>();
	private boolean pinned, maximized;
	private boolean visible = true;
	private boolean pinnable = false;
	private boolean maximizible = true;

	private int ticksSinceScroll = 100;
	private int scrollAmmount = 0;
	private int boxHeight;

	public Frame(int xPos, int yPos, int width, int height, Theme theme, String title){
		super(xPos, yPos, width, height, ComponentType.FRAME, theme, null, title);
	}

	public void onMousePress(int x, int y, int button){
		if(button != 0) return;

		if(isMouseOverBar(x, y)){
			getTheme().getRenderers().get(getType()).doInteractions(this, x, y);
		}

		if(x >= getX() && y >= getY() + this.getFrameBoxHeight() && x <= getX() + getArea().getWidth() && y <= getY() + getArea().getHeight()){
			for(Component c : components){
				if(c.isMouseOver(x, y) && maximized) c.onMousePress(x, y, button);
			}
		}
	}

	@Override
	public void onMouseRelease(int x, int y, int button) {
		if(x >= getX() && y >= getY() + this.getFrameBoxHeight() && x <= getX() + getArea().getWidth() && y <= getY() + getArea().getHeight()){
			for(Component c : components){
				if(c.isMouseOver(x, y) && maximized) c.onMouseRelease(x, y, button);
			}
		}
	}
	
	public boolean isMouseOverBar(int x, int y){
		return(x >= getX() && y >= getY() && x <= getX() + getArea().getWidth() && y <= getY() + this.getFrameBoxHeight());
	}

	public int getScrollAmmount(){
		return scrollAmmount;
	}

	public void setMaximizible(boolean maximizible){
		this.maximizible = maximizible;
	}

	public void scrollFrame(int ammount){		
		this.scrollAmmount += ammount;
		this.ticksSinceScroll = 0;
	}

	public void removeComponent(Component c){
		components.remove(c);
	}
	
	public void updateComponents(){
		this.ticksSinceScroll++;
		if(this.scrollAmmount < this.getMaxScroll()) this.scrollAmmount = this.getMaxScroll();
		if(this.scrollAmmount > 0) this.scrollAmmount = 0;
		
		for(Component c : components){
			c.onUpdate();
			c.setArea(new Dimension((int) (getArea().getWidth() - 2), (int) c.getArea().getHeight()));
			c.setX(getX() + 1);
			
			c.setX(getX() + 1);

			int yCount = getY() + 18;

			for(Component c1 : components){
				if(components.indexOf(c1) < components.indexOf(c)) yCount += c1.getArea().getHeight() + 1;
			}
			
			c.setBaseY(yCount);
			
			c.setY(c.getBaseY() + this.scrollAmmount);
		}
	}

	public int getTicksSinceScroll() {
		return ticksSinceScroll;
	}

	public int getMaxScroll(){
		if(components.size() == 0) return 0;
		
		Component last = components.get(components.size() - 1);
		int maxLast = (int) (last.getBaseY() + last.getArea().getHeight());

		return this.getMaxY() - maxLast;
	}

	public int getMaxY() {
		return (int) (this.getY() + this.getArea().getHeight());
	}

	public int getFrameBoxHeight(){
		return this.getTheme().getFrameBoxHeight();
	}
	
	public void addComponent(Component c){
		components.add(c);
	}


	public int getBaseY(Component c){
		return c.getY() - this.scrollAmmount;
	}
	
	public ArrayList<Component> getComponents(){
		return components;
	}

	public boolean isVisible(){
		return visible;
	}

	public void setVisible(boolean visible){
		this.visible = visible;
	}

	public boolean isPinned(){
		return pinned;
	}

	public void setPinned(boolean pinned){
		this.pinned = pinned;
	}

	public void setPinnable(boolean pinnable){
		this.pinnable = pinnable;
	}

	public boolean isMaximized(){
		return maximized;
	}

	public void setMaximized(boolean maximized){
		this.maximized = maximized;
	}
	
	public boolean isPinnable(){
		return pinnable;
	}

	public boolean isMaximizible(){
		return maximizible;
	}

	public FrameInfo toFrameInfo(){
		return new FrameInfo(this.getText(), this.getX(), this.getY(), this.isPinned(), this.isMaximized());
	}
	
	public void fromFrameInfo(FrameInfo info){
		setX(info.getX());
		setY(info.getY());
		this.setPinned(info.isPinned());
		this.setMaximized(info.isMaximized());
	}

}
