package net.yawk.client.command.datatypes;

import net.yawk.client.utils.ClientUtils;

public class BooleanDataType extends DataType{

	public BooleanDataType() {
		super("Boolean");
	}

	@Override
	public boolean isValid(String text) {
		return text.equalsIgnoreCase("true") || text.equalsIgnoreCase("false");
	}

	@Override
	public Object getValue(String text) {
		return Boolean.parseBoolean(text);
	}
	
}
