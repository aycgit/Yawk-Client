package net.yawk.client.gui.components.buttons;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.yawk.client.api.PluginData;
import net.yawk.client.api.PrivatePluginInformationThread;
import net.yawk.client.gui.IPanel;
import net.yawk.client.gui.components.PluginDisplay;
import net.yawk.client.gui.components.TextField;
import net.yawk.client.utils.ClientUtils;
import net.yawk.client.utils.downloading.DownloadCallback;

public class PrivatePluginInformationButton extends Button implements DownloadCallback{

	private PluginDisplay pluginDisplay;
	private PrivatePluginInformationThread downloadThread;
	private TextField nameField, passwordField;
	
	public PrivatePluginInformationButton(IPanel win, PluginDisplay pluginDisplay, TextField nameField, TextField passwordField) {
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
			new Thread(downloadThread = new PrivatePluginInformationThread(nameField.getText(), passwordField.getText(), this)).start();
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
		}else{
			pluginDisplay.setMessage("Invalid plugin name/password!");
			pluginDisplay.setPlugin(null);
		}
	}
	
	public PrivatePluginInformationThread getDownloadThread() {
		return downloadThread;
	}

}
