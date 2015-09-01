package net.yawk.client.command;

public abstract class DataType<T> {
	
	private String name;
	
	public DataType(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public abstract boolean isValid(Object object);
	public abstract T getValue(Object object);
}
