package me.riverhouse.candy.gui.overlay.overlays.tabGui;

import java.util.ArrayList;

import me.riverhouse.candy.event.EventListener;
import me.riverhouse.candy.event.EventSystem;
import me.riverhouse.candy.event.events.EventKeyPress;
import me.riverhouse.candy.gui.overlay.CandyOverlay;
import me.riverhouse.candy.gui.overlay.overlays.tabGui.tabParts.TabPanel;

public class TabGuiOverlay extends CandyOverlay {

	public TabPanel currentPanel;
	private ArrayList<TabPanel> panels = new ArrayList<TabPanel>();
	
	public TabGuiOverlay() {
		EventSystem.register(this);
	}
	
	@EventListener
	public void onKeyPress(EventKeyPress event){
		
	}
	
}
