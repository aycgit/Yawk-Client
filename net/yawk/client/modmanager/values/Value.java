package net.yawk.client.modmanager.values;

public class Value<T> {
	
	protected T value;
	private String name;
	
	public Value(String name, T value){
		this.value = value;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public T getValue() {
		return value;
	}
	
	public void setValue(T value) {
		this.value = value;
	}
}
