package me.riverhouse.candy;

import me.riverhouse.candy.fileSystem.SettingsManager;
import me.riverhouse.candy.gui.overlay.OverlayManager;
import me.riverhouse.candy.gui.screen.clickGui.FrameInfoManager;
import me.riverhouse.candy.module.ModuleManager;
import me.riverhouse.candy.utils.managers.ModDataManager;

public class Managers {

	private ModuleManager moduleManager;
	private OverlayManager overlayManager;
	private ModDataManager modDataManager;
	private FrameInfoManager frameInfoManager;
	private SettingsManager settingsManager;
	
	public OverlayManager getOverlayManager(){
		return this.overlayManager;
	}
	
	public void setOverlayManager(OverlayManager overlayManager){
		this.overlayManager = overlayManager;
	}
	
	public ModuleManager getModuleManager() {
		return moduleManager;
	}

	public void setModuleManager(ModuleManager moduleManager) {
		this.moduleManager = moduleManager;
	}

	public ModDataManager getModDataManager() {
		return modDataManager;
	}

	public void setModDataManager(ModDataManager modDataManager) {
		this.modDataManager = modDataManager;
	}

	public FrameInfoManager getFrameInfoManager() {
		return frameInfoManager;
	}

	public void setFrameInfoManager(FrameInfoManager frameInfoManager) {
		this.frameInfoManager = frameInfoManager;
	}

	public SettingsManager getSettingsManager() {
		return settingsManager;
	}

	public void setSettingsManager(SettingsManager settingsManager) {
		this.settingsManager = settingsManager;
	}
	
}
