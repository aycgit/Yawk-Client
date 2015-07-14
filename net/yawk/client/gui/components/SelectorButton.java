package net.yawk.client.gui.components;

import net.yawk.client.Client;
import net.yawk.client.gui.Window;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.utils.ChatColours;

public class SelectorButton extends Button implements ISelector{
	
	private String text;
	protected boolean isSelected;
	private SelectorSystem<SelectorButton> system;
	
	public SelectorButton(Window win, String mod, SelectorSystem system) {
		super(win);
		this.text = mod;
		this.system = system;
	}
	
	@Override
	public boolean isEnabled() {
		return isSelected;
	}
	
	@Override
	public void toggle() {
		system.setOnly(this);
	}
	
	@Override
	public String getText() 
	{
		if(isSelected){
			return ChatColours.GREEN+text;
		}else{
			return text;
		}
	}
	
	public String getStaticText() 
	{
		return text;
	}
	
	public boolean isSelected() {
		return isSelected;
	}
	
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	
	@Override
	public boolean isCentered() {
		return false;
	}
}
