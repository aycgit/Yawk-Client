package net.yawk.client.command;

import net.yawk.client.command.datatypes.DataType;

public class Argument {
	
	private String name;
	private boolean optional;
	private DataType type;
	
	public Argument(String name, boolean optional, DataType type) {
		this.name = name;
		this.optional = optional;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public boolean isOptional() {
		return optional;
	}

	public DataType getType() {
		return type;
	}
		
}
