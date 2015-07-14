package net.yawk.client.modmanager;

public interface Mod {
	public String getName();
	public String getDescription();
	public ModType getType();
	public void onEnable();
	public void onDisable();
}
