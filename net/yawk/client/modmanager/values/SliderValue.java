package net.yawk.client.modmanager.values;

public class SliderValue extends Value<Integer>{
	
	private int lowerBound;
	private int upperBound;
	
	public SliderValue(int lowerBound, int upperBound, int value) {
		super(value);
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
	}
	
	public int getLowerBound() {
		return lowerBound;
	}

	public void setLowerBound(int lowerBound) {
		this.lowerBound = lowerBound;
	}

	public int getUpperBound() {
		return upperBound;
	}

	public void setUpperBound(int upperBound) {
		this.upperBound = upperBound;
	}
}
