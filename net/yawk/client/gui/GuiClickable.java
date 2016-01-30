package net.yawk.client.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.yawk.client.Client;
import net.yawk.client.cameras.*;
import net.yawk.client.gui.components.*;
import net.yawk.client.gui.components.buttons.*;
import net.yawk.client.gui.components.scrolling.*;
import net.yawk.client.gui.components.selectors.*;
import net.yawk.client.modmanager.*;

public class GuiClickable extends GuiScreen {

	public List<Window> windows = new CopyOnWriteArrayList<Window>();
	public boolean opened;

	public GuiClickable(ModManager modManager){

		initModWindows(modManager);

		initEnabledModsWindow(modManager);

		initPluginsWindow(modManager);

		initColoursWindow(modManager);

		initFriendsWindow(modManager);

		initCameraWindows(modManager);

		moveWindows();
	}

	private void initModWindows(ModManager modManager){

		for(Mod.Type type : Mod.Type.values()){

			if(type != Mod.Type.PLUGIN && type != Mod.Type.NONE){

				Window win;
				windows.add(win = new Window(type.getName(), modManager, 85));

				for(Mod m : modManager.mods){
					if(m.getType() == type){

						if(m.hasOptions()){
							win.addComponent(new OptionsModButton(m));
						}else{
							win.addComponent(new ModButton(m));
						}
					}
				}
			}
		}
	}

	private void initCameraWindows(ModManager modManager){

		Window rearview = new Window("Rearview", modManager, 85);
		rearview.addComponent(new CameraDisplay(new RearviewCamera(rearview)));
		windows.add(rearview);

		Window arrowview = new Window("ArrowView", modManager, 85);
		arrowview.addComponent(new CameraDisplay(new ArrowCamera(arrowview)));
		windows.add(arrowview);

		Window periscope = new Window("Periscope", modManager, 85);
		periscope.addComponent(new CameraDisplay(new PeriscopeCamera(periscope)));
		windows.add(periscope);

		Window players = new Window("Players", modManager, 85);
		SelectorSystem<SelectorButton> playersSystem = new SelectorSystem<SelectorButton>();

		players.addComponent(new CameraDisplay(new PlayerCamera(players, playersSystem)));

		SpoilerButton spoiler = new SpoilerButton("Select Player");
		players.addComponent(spoiler);
		spoiler.addComponent(new PlayerEntityScrollPane(50, playersSystem));

		windows.add(players);
	}

	private void initPluginsWindow(ModManager modManager){

		Window plugins = new Window("Get Plugins", modManager, 120);
		windows.add(plugins);

		SelectorSystem<SelectorButton> pluginSystem = new SelectorSystem<SelectorButton>();
		plugins.addComponent(new PluginScrollPane(72, pluginSystem, false));
		plugins.addComponent(new PluginDownloadButton(pluginSystem));
	}

	private void initColoursWindow(ModManager modManager){

		Window colours = new Window("Colours", modManager, 85);

		for(ColourType colourType : ColourType.values()){
			colours.addComponent(new ColourPicker(colourType, this));
		}

		windows.add(colours);
	}

	private void initFriendsWindow(ModManager modManager){

		SelectorSystem<SelectorButton> friendsSystem = new SelectorSystem<SelectorButton>();
		FriendScrollPane friendsPane;
		Window friends = new Window("Friends", modManager, 120);
		
		FriendChangerButton changerButton = new FriendChangerButton(friendsSystem);
		
		friends.addComponent(friendsPane = new FriendScrollPane(72, friendsSystem, changerButton));
		friends.addComponent(changerButton);

		windows.add(friends);
	}

	private void initEnabledModsWindow(ModManager modManager){

		Window enabledMods = new Window("Enabled", modManager, 85);
		enabledMods.addComponent(new EnabledModsDisplay());
		windows.add(enabledMods);
	}

	private void moveWindows(){

		int line = 0;

		for(Window win : windows){
			win.posX = 3;
			win.posY = line++ * 20;
		}
	}

	public void onModManagerChange(){
		
		for(Window win : windows){
			win.onModManagerChange();
		}
	}
	
	@Override
	public void drawScreen(int x, int y, float f){

		for(Window w : windows){
			w.renderWindow(x, y, true);
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
					250,
					sr.getScaledWidth(),
					sr.getScaledHeight());
			
			popup.addComponent(new LinkButton("Yawk Forums", "http://yawk.net/forums"));
			
			popup.addComponent(new TextDisplay("Type .help in chat to get a list of all commands"));
			popup.addComponent(new TextDisplay("Press X to open the Yawk Hub"));
			
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
