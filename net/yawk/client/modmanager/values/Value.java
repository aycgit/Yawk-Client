package net.yawk.client.modmanager.values;

public class Value<T> {
	
	protected T value;
	private String name;
	private ValuesRegistry registry;
	
	public Value(String name, ValuesRegistry registry, T defaultValue){
		
		if(registry.hasValue(name)){
			this.value = (T) registry.get(name);
		}else{
			this.value = defaultValue;
		}
		
		this.registry = registry;
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
		registry.set(name, value);
	}
}
