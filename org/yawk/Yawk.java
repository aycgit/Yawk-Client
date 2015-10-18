package org.yawk;

import org.yawk.overlays.YawkWatermarkOverlay;

import me.riverhouse.candy.Candy;
import me.riverhouse.candy.Client;
import me.riverhouse.candy.gui.overlay.OverlayManager;

public class Yawk extends Client {
	
	public Yawk() {
		super("Yawk");
		this.addModulePath("org.yawk.modules");
	}
	
	@Override
	public void onClientLaunch() {
		OverlayManager overlays = Candy.getCandy().getManagers().getOverlayManager();
		overlays.addOverlay(new YawkWatermarkOverlay());
	}
}