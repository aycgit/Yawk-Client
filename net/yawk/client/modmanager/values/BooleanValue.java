package net.yawk.client.modmanager.values;

import net.yawk.client.gui.Component;
import net.yawk.client.gui.IPanel;
import net.yawk.client.gui.components.buttons.BooleanButton;

public class BooleanValue extends Value<Boolean>{

	public BooleanValue(String name, ValuesRegistry registry, Boolean defaultValue) {
		super(name, registry, defaultValue);
	}

	@Override
	public Component getComponent(IPanel panel) {
		return new BooleanButton(panel, this);
	}
}
