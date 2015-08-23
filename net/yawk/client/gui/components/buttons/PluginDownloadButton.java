package net.yawk.client.gui.components.buttons;

import java.net.MalformedURLException;

import net.minecraft.util.ChatComponentTranslation;
import net.yawk.client.Client;
import net.yawk.client.api.DependancyNotFoundException;
import net.yawk.client.api.DependancyNotInstalledException;
import net.yawk.client.api.PluginData;
import net.yawk.client.gui.IRectangle;
import net.yawk.client.gui.Window;
import net.yawk.client.gui.components.selectors.SelectorButton;
import net.yawk.client.gui.components.selectors.SelectorSystem;

public class PluginDownloadButton extends Button{
	
	private SelectorSystem<SelectorButton> system;
	
	public PluginDownloadButton(SelectorSystem<SelectorButton> system) {
		super();
		this.system = system;
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
		
		if(system.selectedButton != null){
			
			String name = system.selectedButton.getStaticText();
			
			PluginData selected = getSelected();
			
			if(selected != null){
				
				if(Client.getClient().getPluginManager().pluginEnabled(selected)){
					Client.getClient().getPluginManager().removePlugin(selected);
				}else{
					try {
						Client.getClient().getPluginManager().addPlugin(selected);
					} catch (MalformedURLException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (DependancyNotInstalledException e) {
						Client.getClient().addChat("Error - Plugin dependancy not installed: "+e.getPlugin().getName());
					} catch (DependancyNotFoundException e) {
						Client.getClient().addChat("Error - Plugin dependancy not found or installed: "+e.getDependancy().name());
					}
				}
			}
		}
	}
	
	@Override
	public String getText() {
		
		PluginData selected = getSelected();
		
		if(selected == null){
			return "No Plugin Selected";
		}else{
			return Client.getClient().getPluginManager().pluginEnabled(selected) ? "Remove Plugin":"Add Plugin";
		}
	}
	
	private PluginData getSelected(){
		
		if(system.selectedButton != null){
			
			String name = system.selectedButton.getStaticText();
			
			for(PluginData plugin : Client.getClient().getPluginManager().pluginData){
				if(plugin.getName().equals(name)){
					return plugin;
				}
			}
		}
		
		return null;
	}
}
