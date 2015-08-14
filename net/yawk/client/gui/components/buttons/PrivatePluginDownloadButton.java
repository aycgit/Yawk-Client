package net.yawk.client.gui.components.buttons;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.yawk.client.api.PluginData;
import net.yawk.client.api.PrivatePluginDownloadThread;
import net.yawk.client.gui.IPanel;
import net.yawk.client.gui.components.PluginDisplay;
import net.yawk.client.gui.components.TextField;
import net.yawk.client.utils.ClientUtils;
import net.yawk.client.utils.downloading.DownloadCallback;

public class PrivatePluginDownloadButton extends Button implements DownloadCallback{

	private PluginDisplay pluginDisplay;
	private PrivatePluginDownloadThread downloadThread;
	private TextField nameField, passwordField;
	
	public PrivatePluginDownloadButton(IPanel win, PluginDisplay pluginDisplay, TextField nameField, TextField passwordField) {
		super(win);
		this.pluginDisplay = pluginDisplay;
		this.nameField = nameField;
		this.passwordField = passwordField;
	}

	@Override
	public boolean isCentered() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}

	@Override
	public void toggle() {
		
		if(downloadThread == null || (downloadThread != null && !downloadThread.isRunning())){
			new Thread(downloadThread = new PrivatePluginDownloadThread(nameField.getText(), passwordField.getText())).start();
		}
		
	}

	@Override
	public String getText() {
		return "Get plugin";
	}

	@Override
	public void finished(Object download) {
		
		if(downloadThread.isSuccessful()){
			pluginDisplay.setPlugin(downloadThread.getPlugin());
		}
	}

}
