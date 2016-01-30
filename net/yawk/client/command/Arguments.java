package net.yawk.client.command;

import java.util.List;
import java.util.Map;

import net.yawk.client.modmanager.Mod;

public class Arguments {
	
	private CommandManager cm;
	private Map<String,Object> values;
	
	public Arguments(CommandManager cm, Map<String,Object> values) {
		this.cm = cm;
		this.values = values;
	}
	
	public String get(String name){
		return (String) values.get(name);
	}
	
	public int getInteger(String name){
		return (Integer) values.get(name);
	}
	
	public boolean getBoolean(String name){
		return (Boolean) values.get(name);
	}
	
	public Mod getMod(String name){
		return (Mod) values.get(name);
	}
}
