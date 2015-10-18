package me.riverhouse.candy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLClassLoader;

import me.riverhouse.candy.event.EventListener;
import me.riverhouse.candy.event.EventSystem;
import me.riverhouse.candy.event.events.EventClientLaunch;
import me.riverhouse.candy.fileSystem.SettingsManager;
import me.riverhouse.candy.gui.overlay.OverlayManager;
import me.riverhouse.candy.gui.screen.CandyWrapperScreen;
import me.riverhouse.candy.gui.screen.clickGui.ClickGui;
import me.riverhouse.candy.logger.CandyLogger;
import me.riverhouse.candy.logger.LoggerImportance;
import me.riverhouse.candy.module.Module;
import me.riverhouse.candy.module.ModuleManager;
import me.riverhouse.candy.utils.Wrapper;

public class Candy {

	private static Candy candy;
	private Client client;
	private ClickGui clickGui;
	private CandyWrapperScreen wrapperScreen;
	
	private Managers managers = new Managers();
	
	public Candy() {
		EventSystem.register(this);
		Candy.candy = this;
		File file = null;
		
		try {
			ClassLoader authorizedLoader = URLClassLoader.newInstance(new URL[]{});
			Client object = (Client) authorizedLoader.loadClass(getClientPath()).newInstance();
			
			this.client = object;
			CandyLogger.addMessage(LoggerImportance.INFO, "Launching client, " + this.client.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.managers.setOverlayManager(new OverlayManager());
		this.managers.setModuleManager(new ModuleManager(this.client.getModulePaths()));
	}
	
	@EventListener
	public void onClientLaunch(EventClientLaunch event){
		this.wrapperScreen = new CandyWrapperScreen();
		this.managers.setSettingsManager(new SettingsManager(this.client.getName()));
		CandyLogger.writeToLogs();
		this.client.onClientLaunch();
		this.managers.getSettingsManager().loadSettings();
		this.managers.getSettingsManager().saveSettings();
		
		for(Module module : this.managers.getModuleManager().getModules()){
			module.mc = Wrapper.getMinecraft();
			module.p = Wrapper.getPlayer();
		}
	}

	public Client getClient(){
		return this.client;
	}
	
	public String getClientPath() throws IOException {
		InputStream in = getClass().getResourceAsStream("/client.txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String line;
		while ((line = reader.readLine()) != null){
			String[] lineParts = line.split(":");

			if(lineParts[0].equalsIgnoreCase("main")){
				return lineParts[1];
			}

		}
		reader.close();
		return "";
	}

	public static void startCandy(){
		new Candy();
	}

	public static Candy getCandy(){
		return candy;
	}

	public Managers getManagers() {
		return managers;
	}

	public ClickGui getClickGui() {
		return clickGui;
	}

	public void setClickGui(ClickGui clickGui) {
		this.clickGui = clickGui;
	}

}
