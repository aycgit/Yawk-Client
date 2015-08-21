package net.yawk.client.gui.hub;

import java.io.IOException;

import net.yawk.client.Client;
import net.yawk.client.gui.components.PluginDisplay;
import net.yawk.client.gui.components.SearchableTextField;
import net.yawk.client.gui.components.TextField;
import net.yawk.client.gui.components.buttons.PluginDownloadButton;
import net.yawk.client.gui.components.buttons.PrivatePluginDownloadButton;
import net.yawk.client.gui.components.buttons.PrivatePluginInformationButton;
import net.yawk.client.gui.components.buttons.TextButton;
import net.yawk.client.gui.components.scrolling.ModScrollPane;
import net.yawk.client.gui.components.scrolling.PluginScrollPane;
import net.yawk.client.gui.components.scrolling.ScrollPane;
import net.yawk.client.gui.components.selectors.KeybindButton;
import net.yawk.client.gui.components.selectors.SelectorButton;
import net.yawk.client.gui.components.selectors.SelectorSystem;
import net.yawk.client.modmanager.Mod;

public class PrivatePluginSlate extends ComponentSlate{

	public PrivatePluginSlate(GuiHub hub, Client client) {
		super("Private Plugins", hub, client);
	}

	@Override
	public void init() {
		
		PluginDisplay pluginDisplay;
		TextField nameField;
		TextField passwordField;
		PrivatePluginInformationButton informationButton;
		
		options.addComponent(nameField = new TextField("Plugin Name"));
		options.addComponent(passwordField = new TextField("Plugin Password"));
		
		options.addComponent(pluginDisplay = new PluginDisplay());
		
		options.addComponent(informationButton = new PrivatePluginInformationButton(pluginDisplay, nameField, passwordField));
		options.addComponent(new PrivatePluginDownloadButton(informationButton));
	}

}
