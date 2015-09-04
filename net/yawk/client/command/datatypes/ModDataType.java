package net.yawk.client.command.datatypes;

import net.yawk.client.Client;

import com.google.common.base.Strings;

public class ModDataType extends DataType{

	public ModDataType() {
		super("Mod");
	}

	@Override
	public boolean isValid(String text) {
		return Client.getClient().getModManager().getModByName(text) != null;
	}

	@Override
	public Object getValue(String text) {
		return Client.getClient().getModManager().getModByName(text);
	}

	@Override
	public Object getDefault() {
		return null;
	}

	@Override
	public String getError() {
		return "The mod %s was not found";
	}

}
