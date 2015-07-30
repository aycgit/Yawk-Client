package net.yawk.client.modmanager.values;

public class SliderValue extends Value<Double>{
	
	private double lowerBound;
	private double upperBound;
	private boolean rounded;
	
	public SliderValue(String name, double lowerBound, double upperBound, double value, boolean rounded) {
		super(name, value);
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
		this.rounded = rounded;
	}
	
	public double getLowerBound() {
		return lowerBound;
	}
	
	public void setLowerBound(double lowerBound) {
		this.lowerBound = lowerBound;
	}
	
	public double getUpperBound() {
		return upperBound;
	}
	
	public void setUpperBound(double upperBound) {
		this.upperBound = upperBound;
	}
	
	public void setValue(double value){
		
		if(rounded){
			value = (int)Math.round(value);
		}
		
		if(value > upperBound){
			value = upperBound;
		}
		
		if(value < lowerBound){
			value = lowerBound;
		}
		
		this.value = value;
	}
}
