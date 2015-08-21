package net.yawk.client.gui.components;

import net.yawk.client.Client;
import net.yawk.client.gui.ColourType;
import net.yawk.client.gui.AbstractComponent;
import net.yawk.client.gui.IRectangle;
import net.yawk.client.modmanager.values.ArrayValue;

public class ArrayValueBox extends AbstractComponent{
	
	private ArrayValue val;
	
	public ArrayValueBox(ArrayValue val) {
		super();
		this.val = val;
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
		
		String whole = val.getSelectedMode();
		
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
			
			val.increment();
			
		}else if(mouseOverRight(x, y, getX(), getY())){
			
			Client.getClient().getMinecraft().thePlayer.playSound("random.click", 1, 1);
			
			val.decrement();
		}
	}
	
	private boolean mouseOverLeft(int x, int y, int cx, int cy){
		return x > cx && x < cx+rect.getWidth()/2 && y > cy && y < cy+getHeight();
	}
	
	private boolean mouseOverRight(int x, int y, int cx, int cy){
		return x > cx+rect.getWidth()/2 && x < cx+rect.getWidth() && y > cy && y < cy+getHeight();
	}
		
	public String getSelectedOption(){
		return val.getSelectedMode();
	}
	
	@Override
	public int getHeight() {
		return 12;
	}
}
