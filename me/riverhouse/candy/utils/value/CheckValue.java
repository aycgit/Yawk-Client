package me.riverhouse.candy.utils.value;


public class CheckValue extends Value {

	public CheckValue(String name, boolean value) {
		super(name, value);
	}

	public boolean getState(){
		return ((Boolean) this.value).booleanValue();
	}
	
	public void setState(boolean value){
		this.value = value;
	}
	
}
