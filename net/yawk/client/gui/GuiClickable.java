package net.yawk.client.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.yawk.client.Client;
import net.yawk.client.gui.components.*;
import net.yawk.client.gui.components.selectors.KeybindButton;
import net.yawk.client.gui.components.selectors.SelectorButton;
import net.yawk.client.gui.components.selectors.SelectorSystem;
import net.yawk.client.modmanager.*;

public class GuiClickable extends GuiScreen {
	
	public CopyOnWriteArrayList<Window> windows = new CopyOnWriteArrayList<Window>();
	public boolean opened;
	
	public GuiClickable(ModManager modManager){
		
		for(Mod.Type type : Mod.Type.values()){
			
			if(type != Mod.Type.PLUGIN){
				
				Window win;
				windows.add(win = new Window(type.getName(), modManager, 85, 12));
				
				for(Mod m : modManager.mods){
					if(m.getType() == type){
						win.components.add(new ModButton(win, m));
					}
				}
			}
		}
		
		Window keybindWindow = new Window("Keybinds", modManager, 100, 12);
		windows.add(keybindWindow);
		
		ScrollPane pane;
		keybindWindow.components.add(pane = new ScrollPane(keybindWindow, 72));
		SelectorSystem<KeybindButton> system = new SelectorSystem<KeybindButton>();
		
		for(Mod m : modManager.mods){
			pane.components.add(system.add(new KeybindButton(pane, m, system)));
		}
		
		Window enabledMods = new Window("Enabled", modManager, 85, 12);
		windows.add(enabledMods);
		enabledMods.components.add(new EnabledModsDisplay(enabledMods));
		
		//PLUGIN DOWNLOAD WINDOW
		
		Window plugins = new Window("Get Plugins", modManager, 120, 12);
		windows.add(plugins);
		
		SelectorSystem<SelectorButton> pluginSystem = new SelectorSystem<SelectorButton>();
		plugins.components.add(new PluginScrollPane(plugins, 72, pluginSystem));
		plugins.components.add(new PluginDownloadButton(plugins, pluginSystem));
		
		//COLOUR PICKER WINDOW
		Window colours = new Window("Colours", modManager, 85, 12);
		
		for(ColourType colourType : ColourType.values()){
			colours.components.add(new ColourPicker(colours, colourType, this));
		}
		
		windows.add(colours);
		
		
		
		//MOVE THE WINDOWS TO DIFFERENT POSITIONS
		moveWindows();
	}
	
	private void moveWindows(){
		
		int line = 0;
		
		for(Window win : windows){
			win.posX = 3;
			win.posY = line++ * 20;
		}
	}
	
	@Override
	public void drawScreen(int x, int y, float f){
		
		for(Window w : windows){
			w.renderWindow(x, y);
		}
		
	}
	
	@Override
	protected void keyTyped(char c, int i) throws IOException{
		
		for(Window w : windows){
			w.keyPress(c, i);
		}
		
		super.keyTyped(c, i);
	}
	
	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		for(Window w : windows){
			w.mouseClicked(mouseX, mouseY);
		}
	}
	
	@Override
	protected void mouseReleased(int mouseX, int mouseY, int state) {
		for(Window w : windows){
			w.mouseReleased(mouseX, mouseY, state);
		}
	}
	
	public Window getWindowByName(String name){
		
		for(Window w : windows){
			if(w.title.equals(name)){
				return w;
			}
		}
		
		return null;
	}
	
	@Override
	public void initGui() {
		
		Keyboard.enableRepeatEvents(true);
		
		if(!opened){
			
			ScaledResolution sr = new ScaledResolution(Client.getClient().getMinecraft(), Client.getClient().getMinecraft().displayWidth, Client.getClient().getMinecraft().displayHeight);
			
			Window popup = new WindowPopup(
					"Welcome",
					"Get help here:",
					Client.getClient().getModManager(),
					100,
					sr.getScaledWidth(),
					sr.getScaledHeight());
			
			popup.components.add(new LinkButton(popup, "Yawk Forums", "http://yawk.net/forums"));
			
			windows.add(popup);
			
			opened = true;
		}
	}
	
	@Override
	public void onGuiClosed() {
		
		for(Window w : windows){
			w.onGuiClosed();
		}
		
		Keyboard.enableRepeatEvents(false);
	}
	
	public void setDragging(Window dragging){
		
		for(Window win : windows){
			if(win != dragging){
				win.dragging = false;
			}
		}
		
		windows.remove(dragging);
		windows.add(windows.size(), dragging);
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
}
