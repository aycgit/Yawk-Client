package me.riverhouse.candy.utils.value;

import java.util.ArrayList;

public class SliderValue extends Value {

	private double min,max;
	
	public SliderValue(String name, double value, double min, double max) {
		super(name, value);
	}

	public double getValue(){
		return ((Double) this.value).doubleValue();
	}
	
	public void setValue(double value){
		this.value = value;
	}

	public double getMin() {
		return min;
	}

	public double getMax() {
		return max;
	}
	
	public enum DisplayType {
		
		PERCENT,
		DOUBLE
		
	}
	
}
