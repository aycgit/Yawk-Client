package me.riverhouse.candy.utils.value;

public class Value {

	private String name;
	protected Object value;

	public Value(String name, Object value) {
		this.name = name;
		this.value = value;
	}
	
	public String getName(){
		return this.name;
	}
	
}
