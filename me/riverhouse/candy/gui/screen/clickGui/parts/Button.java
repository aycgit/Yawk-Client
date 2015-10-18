package me.riverhouse.candy.gui.screen.clickGui.parts;

import java.util.ArrayList;

import me.riverhouse.candy.gui.screen.clickGui.Component;
import me.riverhouse.candy.gui.screen.clickGui.ComponentType;
import me.riverhouse.candy.gui.screen.clickGui.Theme;
import me.riverhouse.candy.gui.screen.clickGui.listeners.ButtonClickListener;

public class Button extends Component {

	public ArrayList<ButtonClickListener> listeners = new ArrayList<ButtonClickListener>();
	private boolean enabled = false;

	public Button(int xPos, int yPos, int width, int height, Theme theme, Component parent, String text){
		super(xPos, yPos, width, height, ComponentType.BUTTON, theme, parent, text);
	}

	public boolean isEnabled(){
		return enabled;
	}

	public void setEnabled(boolean enabled){
		this.enabled = enabled;
	}

	public void setListeners(ArrayList<ButtonClickListener> listeners){
		this.listeners = listeners;
	}

	public ArrayList<ButtonClickListener> getListeners(){
		return listeners;
	}

	public void addListener(ButtonClickListener listener){
		listeners.add(listener);
	}

	public void onMousePress(int x, int y, int button){
		this.enabled = !this.enabled;
		for(ButtonClickListener listener : listeners)
			listener.onButtonClick(this);
	}

}
