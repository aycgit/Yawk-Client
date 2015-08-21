package net.yawk.client.modmanager.values;

import net.yawk.client.gui.AbstractComponent;
import net.yawk.client.gui.IRectangle;
import net.yawk.client.gui.components.buttons.BooleanButton;

public class BooleanValue extends AbstractValue<Boolean>{

	public BooleanValue(String name, String saveName, ValuesRegistry registry, Boolean defaultValue) {
		super(name, saveName, registry, defaultValue);
	}

	@Override
	public AbstractComponent getComponent(IRectangle panel) {
		return new BooleanButton(this);
	}
}
