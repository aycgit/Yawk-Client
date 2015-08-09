package net.yawk.client.gui.hub;

import net.yawk.client.Client;
import net.yawk.client.gui.components.TextField;
import net.yawk.client.gui.components.buttons.PluginDownloadButton;
import net.yawk.client.gui.components.buttons.TextButton;
import net.yawk.client.gui.components.scrolling.PluginScrollPane;
import net.yawk.client.gui.components.scrolling.ScrollPane;
import net.yawk.client.gui.components.selectors.KeybindButton;
import net.yawk.client.gui.components.selectors.SelectorButton;
import net.yawk.client.gui.components.selectors.SelectorSystem;
import net.yawk.client.modmanager.Mod;

public class KeybindSlate extends ComponentSlate{

	public KeybindSlate(GuiHub hub, Client client) {
		super("Keybinds", hub, client);
	}

	@Override
	public void init() {
		
		ScrollPane pane;
		options.addComponent(pane = new ScrollPane(options, 200));
		SelectorSystem<KeybindButton> system = new SelectorSystem<KeybindButton>();
		
		for(Mod m : Client.getClient().getModManager().mods){
			pane.addComponent(system.add(new KeybindButton(pane, m, system)));
		}
		
	}

}
