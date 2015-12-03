package net.yawk.client.gui.components;

import net.minecraft.util.EnumChatFormatting;
import net.yawk.client.Client;
import net.yawk.client.gui.ColourType;
import net.yawk.client.gui.AbstractComponent;
import net.yawk.client.gui.Window;
import net.yawk.client.utils.Colours;

public class NumberSelector extends AbstractComponent{
	
	private int number;
	private int max;
	private int min;
	private int increment;
	private String name;
	private String unit = "";
	
	public NumberSelector(String name, int min, int max, int start, int increment){
		this.name = name;
		this.min = min;
		this.max = max;
		this.number = start;
		this.increment = increment;
	}
	
	public NumberSelector(String name, int min, int max, int start, int increment, String unit){
		this.name = name;
		this.min = min;
		this.max = max;
		this.number = start;
		this.increment = increment;
		this.unit = unit;
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
		
		String whole = name + ": " + number + unit;
		
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
			
			number-=increment;
			
			if(number < min){
				number = min;
			}
			
		}else if(mouseOverRight(x, y, getX(), getY())){
			
			Client.getClient().getMinecraft().thePlayer.playSound("random.click", 1, 1);
			
			number+=increment;
			
			if(number > max){
				number = max;
			}
		}
	}
	
	private boolean mouseOverLeft(int x, int y, int cx, int cy){
		return x > cx && x < cx+rect.getWidth()/2 && y > cy && y < cy+getHeight();
	}
	
	private boolean mouseOverRight(int x, int y, int cx, int cy){
		return x > cx+rect.getWidth()/2 && x < cx+rect.getWidth() && y > cy && y < cy+getHeight();
	}
	
	@Override
	public int getHeight() {
		return 12;
	}
	
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
}
