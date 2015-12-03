package net.yawk.client.gui.components.buttons;

import net.yawk.client.Client;
import net.yawk.client.gui.IRectangle;

public class PrivatePluginDownloadButton extends Button{

	private PrivatePluginInformationButton information;
	
	public PrivatePluginDownloadButton(PrivatePluginInformationButton information) {
		super();
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
