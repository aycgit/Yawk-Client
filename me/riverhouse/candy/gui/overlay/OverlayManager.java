package me.riverhouse.candy.gui.overlay;

import java.util.ArrayList;

public class OverlayManager {
	
	private ArrayList<CandyOverlay> overlays = new ArrayList<CandyOverlay>();
	
	public ArrayList<CandyOverlay> getOverlays(){
		return this.overlays;
	}
	
	public void addOverlay(CandyOverlay overlay){
		this.overlays.add(overlay);
	}
	
	public void removeOverlay(CandyOverlay overlay){
		this.overlays.remove(overlay);
	}
	
}
