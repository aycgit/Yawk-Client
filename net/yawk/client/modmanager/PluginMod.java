package net.yawk.client.modmanager;

public abstract class PluginMod implements Mod{
	
	public final ModType getType(){
		return ModType.PLUGIN;
	}
	
}
