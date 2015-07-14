package net.yawk.client.api;

import java.util.ArrayList;



import net.yawk.client.Client;
import net.yawk.client.gui.Window;
import net.yawk.client.modmanager.Mod;
/**
 * The main class used by any plugins
 * @author RandomAmazingGuy
 */
public abstract class PluginRegister {
	
	/**
	 * Used for the plugin to add components to the plugin's window
	 * @param client
	 * @param win
	 */
	public abstract void addElements(Client client, Window win);
	
	/**
	 * Used for the plugin to add mods
	 * @param client
	 * @param list
	 */
	public abstract void init(Client client, ArrayList<Mod> list);
	
	/**
	 * The name of the plugin. This is used as the window name and is how the plugin will be identified in the GUI
	 * @return
	 */
	public abstract String getName();
	
	/**
	 * The description of the plugin. Currently unused.
	 * @return
	 */
	public abstract String getDescription();
	
	/**
	 * Returns the width which the plugin window should have, defaulting to 85
	 * @return
	 */
	public int getWidth(){
		return 85;
	}
}
