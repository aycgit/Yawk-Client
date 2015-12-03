package net.yawk.client.utils.timing;

import net.yawk.client.modmanager.values.SliderValue;

public class ValueTimer extends MillisecondTimer{
	
	private SliderValue value;
	
	public ValueTimer(SliderValue value){
		this.value = value;
	}

	@Override
	public int getDelay() {
		return value.getValue().intValue();
	}
}
