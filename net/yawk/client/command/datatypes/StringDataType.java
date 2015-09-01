package net.yawk.client.command.datatypes;

import com.google.common.base.Strings;

public class StringDataType extends DataType{

	public StringDataType() {
		super("String");
	}

	@Override
	public boolean isValid(String text) {
		return true;
	}

	@Override
	public Object getValue(String text) {
		return text;
	}

	@Override
	public Object getDefault() {
		return "";
	}

}
