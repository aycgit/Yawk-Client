package net.yawk.client.gui.components.scrolling;

import net.yawk.client.Client;
import net.yawk.client.gui.IPanel;
import net.yawk.client.gui.components.selectors.KeybindButton;
import net.yawk.client.gui.components.selectors.SelectorSystem;
import net.yawk.client.gui.hub.KeybindSetButton;
import net.yawk.client.modmanager.Mod;

public class ModScrollPane extends FilterableScrollPane{

	private KeybindSetButton keybindButton;
	
	public ModScrollPane(IPanel win, int height, SelectorSystem<KeybindButton> system) {
		super(win, height);
		
		for(Mod m : Client.getClient().getModManager().mods){
			addFilterableComponent(system.add(new KeybindButton(this, m, system)));
		}
	}
}
