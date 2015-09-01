package net.yawk.client.command;

import java.util.List;
import java.util.Map;

public class Arguments {
	
	private CommandManager cm;
	private Map<Argument,String> values;
	
	public Arguments(CommandManager cm, Map<Argument,String> values) {
		this.cm = cm;
		this.values = values;
	}
	
	public Map<Argument,String> getValues() {
		return values;
	}
	
	public String get(String name){
		return values.get(name);
	}
	
	public int getInteger(String name){
		return cm.INTEGER.getValue(values.get(name));
	}
	
	public boolean getBoolean(String name){
		return cm.BOOLEAN.getValue(values.get(name));
	}
}
