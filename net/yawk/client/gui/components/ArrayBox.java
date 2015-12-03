package net.yawk.client.gui.components;

import net.yawk.client.Client;
import net.yawk.client.gui.ColourType;
import net.yawk.client.gui.AbstractComponent;
import net.yawk.client.gui.IRectangle;
import net.yawk.client.gui.Window;

//TODO: Maybe merge this with the NumberSelector
public class ArrayBox<T> extends AbstractComponent{
	
	private T[] options;
	private int index;
	
	public ArrayBox(T[] options) {
		super();
		this.options = options;
	}
	
	@Override
	public void draw(int x, int y) {
		
		Client.getClient().getFontRenderer().drawStringWithShadow("<",
				getX() + 3,
				getY() + 2,
				mouseOverLeft(x,y,getX(),getY())? ColourType.TEXT.getModifiedColour():ColourType.TEXT.getColour(),
				true);
		
		Client.getClient().getFontRenderer().drawStringWithShadow(">",
				getX() + rect.getWidth() - 3 - Client.getClient().getFontRenderer().getStringWidth(">"),
				getY() + 2,
				mouseOverRight(x,y,getX(),getY())? ColourType.TEXT.getModifiedColour():ColourType.TEXT.getColour(),
				true);
		
		String whole = options[index].toString();
		
		Client.getClient().getFontRenderer().drawStringWithShadow(whole,
				getX() + rect.getWidth()/2 - Client.getClient().getFontRenderer().getStringWidth(whole)/2,
				getY() + 2,
				ColourType.TEXT.getColour(),
				true);
	}
	
	@Override
	public void mouseClicked(int x, int y) {
		
		if(mouseOverLeft(x, y, getX(), getY())){
			
			Client.getClient().getMinecraft().thePlayer.playSound("random.click", 1, 1);
			
			index--;
			
			if(index < 0){
				index = options.length-1;
			}
			
		}else if(mouseOverRight(x, y, getX(), getY())){
			
			Client.getClient().getMinecraft().thePlayer.playSound("random.click", 1, 1);
			
			index++;
			
			if(index >= options.length){
				index = 0;
			}
		}
	}
	
	private boolean mouseOverLeft(int x, int y, int cx, int cy){
		return x > cx && x < cx+rect.getWidth()/2 && y > cy && y < cy+getHeight();
	}
	
	private boolean mouseOverRight(int x, int y, int cx, int cy){
		return x > cx+rect.getWidth()/2 && x < cx+rect.getWidth() && y > cy && y < cy+getHeight();
	}
	
	public T[] getOptions() {
		return options;
	}
	
	public void setOptions(T[] options) {
		this.options = options;
	}
	
	public T getSelectedOption(){
		return options[index];
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	@Override
	public int getHeight() {
		return 12;
	}
}
