package net.yawk.client.gui.hub;

import net.yawk.client.Client;
import net.yawk.client.gui.components.SearchableTextField;
import net.yawk.client.gui.components.TextField;
import net.yawk.client.gui.components.buttons.PluginDownloadButton;
import net.yawk.client.gui.components.buttons.TextButton;
import net.yawk.client.gui.components.scrolling.PluginScrollPane;
import net.yawk.client.gui.components.scrolling.ScrollPane;
import net.yawk.client.gui.components.selectors.SelectorButton;
import net.yawk.client.gui.components.selectors.SelectorSystem;

public class PluginSlate extends ComponentSlate{

	public PluginSlate(GuiHub hub, Client client) {
		super("Plugins", hub, client);
	}

	@Override
	public void init() {
		
		SelectorSystem<SelectorButton> system = new SelectorSystem<SelectorButton>();
		PluginScrollPane pluginsPane;
		options.addComponent(pluginsPane = new PluginScrollPane(options, 200, system, true));
		
		options.addComponent(new SearchableTextField(options, "Search", pluginsPane));
		options.addComponent(new PluginDownloadButton(options, system));
		
	}

}
