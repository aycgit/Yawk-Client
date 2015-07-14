package net.yawk.client.gui.components;

import java.util.ArrayList;

public class SelectorSystem<T extends Component & ISelector> {
	
	public ArrayList<T> buttons = new ArrayList<T>();
	public T selectedButton;
	
	public void setOnly(T button){
		
		for(T b : buttons){
			if(b != button){
				b.setSelected(false);
			}
		}
		
		button.setSelected(true);
		selectedButton = button;
	}
	
	public T add(T b){
		buttons.add(b);
		return b;
	}
}
