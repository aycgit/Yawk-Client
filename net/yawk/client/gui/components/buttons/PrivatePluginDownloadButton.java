package net.yawk.client.gui.components.buttons;

import net.yawk.client.Client;
import net.yawk.client.gui.IPanel;

public class PrivatePluginDownloadButton extends Button{

	private PrivatePluginInformationButton information;
	
	public PrivatePluginDownloadButton(IPanel win, PrivatePluginInformationButton information) {
		super(win);
		this.information = information;
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
		
		if(isPluginSelected()){
			Client.getClient().getPluginManager().downloadPlugin(information.getDownloadThread().getPlugin());
		}
		
	}

	@Override
	public String getText() {
		return isPluginSelected()? "Download plugin":"";
	}
	
	private boolean isPluginSelected(){
		return information.getDownloadThread() != null && !information.getDownloadThread().isRunning() && information.getDownloadThread().getPlugin() != null;
	}

}
