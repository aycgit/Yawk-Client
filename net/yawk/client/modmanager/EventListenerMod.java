package net.yawk.client.modmanager;

public class EventListenerMod extends Mod{

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public Type getType() {
		return Type.NONE;
	}

	@Override
	public String getName() {
		return "Listener";
	}
}
