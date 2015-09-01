package net.yawk.client.command.datatypes;

import net.yawk.client.utils.ClientUtils;

public class IntegerDataType extends DataType<Integer>{

	public IntegerDataType() {
		super("Integer");
	}

	@Override
	public boolean isValid(String text) {
		return ClientUtils.isInteger(text);
	}

	@Override
	public Integer getValue(String text) {
		return Integer.parseInt(text);
	}

	@Override
	public Integer getDefault() {
		return 0;
	}
	
}
