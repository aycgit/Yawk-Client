package net.yawk.client.command.datatypes;

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

}
