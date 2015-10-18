package me.riverhouse.candy.module;

import java.awt.Color;
import java.util.ArrayList;

import me.riverhouse.candy.utils.bind.KeyBind;
import me.riverhouse.candy.utils.value.Value;

public class ModData {

	private String name;
	private KeyBind bind;
	private Color color;
	private boolean state = false;

	private ArrayList<Value> values = new ArrayList<Value>();
	
	public ModData(String name, int bind, Color color){
		this.name = name;
		this.bind = new KeyBind(this.name, bind);
		this.color = color;
	}

	public KeyBind getBind() {
		return bind;
	}

	public void setBind(KeyBind bind) {
		this.bind = bind;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public String getName() {
		return name;
	}
	
	public boolean getState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}
	
}
