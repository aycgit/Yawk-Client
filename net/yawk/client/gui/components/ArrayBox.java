package net.yawk.client.gui.components;

import net.yawk.client.gui.Window;

public class ArrayBox<T> extends Button{
	
	private T[] options;
	private int index;
	
	public ArrayBox(Window win, T[] options) {
		super(win);
		this.options = options;
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
	
	@Override
	public boolean isEnabled() {
		return false;
	}
	
	@Override
	public void toggle() {
		
		index++;
		
		if(index >= options.length){
			index = 0;
		}
	}
	
	@Override
	public String getText() {
		return "< " + options[index].toString() + " >";
	}
	
	@Override
	public boolean isCentered() {
		return true;
	}
}
