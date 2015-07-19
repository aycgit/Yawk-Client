package net.yawk.client.gui.components;

import net.minecraft.util.EnumChatFormatting;
import net.yawk.client.Client;
import net.yawk.client.gui.ColourType;
import net.yawk.client.gui.Window;
import net.yawk.client.utils.Colours;

public class NumberSelector extends Component{
	
	protected Window win;
	private int number;
	private int max;
	private int min;
	private int increment;
	private String name;
	private String unit = "";
	
	public NumberSelector(Window win, String name, int min, int max, int start, int increment){
		this.win = win;
		this.name = name;
		this.min = min;
		this.max = max;
		this.number = start;
		this.increment = increment;
	}
	
	public NumberSelector(Window win, String name, int min, int max, int start, int increment, String unit){
		this.win = win;
		this.name = name;
		this.min = min;
		this.max = max;
		this.number = start;
		this.increment = increment;
		this.unit = unit;
	}
	
	@Override
	public void draw(int x, int y, int cx, int cy) {
		
		//TODO: Change this to use the right colours rather than the chat formatting colours
		
		String left = (mouseOverLeft(x,y,cx,cy)? EnumChatFormatting.GREEN +"< "+EnumChatFormatting.WHITE:"< ");
		String right = (mouseOverRight(x,y,cx,cy)? EnumChatFormatting.GREEN+" >"+EnumChatFormatting.WHITE:" >");
		String whole = left + name + ": " + number + unit + right;
		
		Client.getClient().getFontRenderer().drawStringWithShadow(whole,
				cx + win.getWidth()/2 - Client.getClient().getFontRenderer().getStringWidth(whole)/2,
				cy + 2,
				ColourType.TEXT.getColour(),
				true);
	}
	
	@Override
	public void mouseClicked(int x, int y, int cx, int cy) {
		if(mouseOverLeft(x, y, cx, cy)){
			Client.getClient().getMinecraft().thePlayer.playSound("random.click", 1, 1);
			
			number-=increment;
			
			if(number < min){
				number = min;
			}
			
		}else if(mouseOverRight(x, y, cx, cy)){
			Client.getClient().getMinecraft().thePlayer.playSound("random.click", 1, 1);
			
			number+=increment;
			
			if(number > max){
				number = max;
			}
		}
	}
	
	private boolean mouseOverLeft(int x, int y, int cx, int cy){
		return x > cx && x < cx+win.getWidth()/2 && y > cy && y < cy+getHeight();
	}
	
	private boolean mouseOverRight(int x, int y, int cx, int cy){
		return x > cx+win.getWidth()/2 && x < cx+win.getWidth() && y > cy && y < cy+getHeight();
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
