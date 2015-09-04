package net.yawk.client.command.datatypes;

import net.yawk.client.utils.ClientUtils;

public class BooleanDataType extends DataType<Boolean>{

	public BooleanDataType() {
		super("Boolean");
	}

	@Override
	public boolean isValid(String text) {
		return text.equalsIgnoreCase("true") || text.equalsIgnoreCase("false");
	}

	@Override
	public Boolean getValue(String text) {
		return Boolean.parseBoolean(text);
	}

	@Override
	public Boolean getDefault() {
		return false;
	}

	@Override
	public String getError() {
		return "%s should be a boolean";
	}
	
}
