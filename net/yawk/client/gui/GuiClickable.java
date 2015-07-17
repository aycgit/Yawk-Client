package net.yawk.client.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.gui.GuiScreen;
import net.yawk.client.Client;
import net.yawk.client.gui.components.*;
import net.yawk.client.modmanager.*;

public class GuiClickable extends GuiScreen {
	
	public CopyOnWriteArrayList<Window> windows = new CopyOnWriteArrayList<Window>();
	
	public GuiClickable(ModManager modManager){
		
		for(ModType type : ModType.values()){
			
			if(type != ModType.PLUGIN){
				
				Window win;
				windows.add(win = new Window(type.getName(), modManager));
				
				for(Mod m : modManager.mods){
					if(modManager.getModType(m) == type){
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
			pane.components.add(system.add(new KeybindButton(keybindWindow, m, system)));
		}
		
		Window enabledMods = new Window("Enabled", modManager);
		windows.add(enabledMods);
		enabledMods.components.add(new EnabledModsDisplay(enabledMods));
		
		//PLUGIN DOWNLOAD WINDOW
		
		Window plugins = new Window("Get Plugins", modManager, 120, 12);
		windows.add(plugins);
		
		SelectorSystem<SelectorButton> pluginSystem = new SelectorSystem<SelectorButton>();
		plugins.components.add(new PluginScrollPane(plugins, 72, pluginSystem));
		plugins.components.add(new PluginDownloadButton(plugins, pluginSystem));
		
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
			w.draw(x, y);
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
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
}
