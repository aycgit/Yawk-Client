package net.yawk.client.gui.themes.huzuni;

import net.yawk.client.Client;
import net.yawk.client.gui.ColourType;
import net.yawk.client.gui.Window;
import net.yawk.client.gui.components.Component;
import net.yawk.client.gui.components.ModButton;
import net.yawk.client.gui.themes.ButtonRenderer;
import net.yawk.client.gui.themes.Theme;
import net.yawk.client.gui.themes.ThemeFontRenderer;
import net.yawk.client.gui.themes.WindowRenderer;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.ModManager;
import net.yawk.client.utils.GuiUtils;

public class ThemeHuzuni extends Theme{
	
	private HuzuniWindowRenderer huzuniRenderer = new HuzuniWindowRenderer();
	private HuzuniFontRendererWrapper huzuniFontRenderer = new HuzuniFontRendererWrapper();
	private HuzuniButtonRenderer buttonRenderer = new HuzuniButtonRenderer();
	
	@Override
	public WindowRenderer getWindowRenderer() {
		return huzuniRenderer;
	}
	
	@Override
	public ThemeFontRenderer getFontRenderer() {
		return huzuniFontRenderer;
	}
	
	@Override
	public ButtonRenderer getButtonRenderer() {
		return buttonRenderer;
	}
}
