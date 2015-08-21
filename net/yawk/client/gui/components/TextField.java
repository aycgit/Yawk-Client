package net.yawk.client.gui.components;

import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import org.lwjgl.input.Keyboard;

import net.minecraft.util.ChatAllowedCharacters;
import net.yawk.client.Client;
import net.yawk.client.gui.AbstractComponent;
import net.yawk.client.gui.IRectangle;
import net.yawk.client.gui.Window;
import net.yawk.client.utils.Colours;
import net.yawk.client.utils.GuiUtils;

public class TextField extends AbstractComponent{
	
	private boolean selected;
	protected String contents = "";
	private String placeholder;
	private int frames = 0;
	
	public TextField(String placeholder){
		this.placeholder = placeholder;
	}
	
	@Override
	public void draw(int x, int y) {
		
		//GuiUtils.drawRect(cx, cy, cx+win.getWidth(), cy+getHeight(), 0x2F000000);
		
		if(!selected && contents.length() == 0){
			Client.getClient().getFontRenderer().drawStringWithShadow(placeholder, getX() + 3, getY() + 2, 0xFFBFBFBF, true);
		}else if(selected){
			Client.getClient().getFontRenderer().drawStringWithShadow(contents + (frames++ % 2 == 0? "_" : ""), getX() + 3, getY() + 2, 0xFFFFFFFF, true);
		}else{
			Client.getClient().getFontRenderer().drawStringWithShadow(contents, getX() + 3, getY() + 2, 0xFFFFFFFF, true);
		}
	}
	
	@Override
	public void keyPress(int key, char c) {
		if(selected){
			if(key == Keyboard.KEY_BACK && contents.length() > 0){
				removeLastCharacter();
			}else if(key == Keyboard.KEY_V && (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) || Keyboard.isKeyDown(Keyboard.KEY_RCONTROL))){
				
				try {
					addText((String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor));
				} catch (HeadlessException e) {
					e.printStackTrace();
				} catch (UnsupportedFlavorException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}else if(ChatAllowedCharacters.isAllowedCharacter(c)){
				addCharacter(c);
			}
		}
	}
	
	protected void removeLastCharacter(){
		contents = contents.substring(0, contents.length() - 1);
	}
	
	protected void addCharacter(char c){
		contents += c;
	}
	
	protected void addText(String text){
		contents += text;
	}
	
	@Override
	public void mouseClicked(int x, int y) {
		if(mouseOverButton(x, y, getX(), getY())){
			selected = true;
		}else{
			selected = false;
		}
	}
	
	private boolean mouseOverButton(int x, int y, int cx, int cy){
		return x > cx && x < cx+rect.getWidth() && y > cy && y < cy+getHeight();
	}
	
	@Override
	public int getHeight() {
		return 12;
	}
	
	public String getText() {
		return contents;
	}

	public void setText(String contents) {
		this.contents = contents;
	}
}
