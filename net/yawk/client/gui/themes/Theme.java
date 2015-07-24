package net.yawk.client.gui.themes;

import net.yawk.client.Client;
import net.yawk.client.gui.ColourType;
import net.yawk.client.gui.Window;
import net.yawk.client.gui.components.Component;
import net.yawk.client.gui.components.ModButton;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.ModManager;
import net.yawk.client.utils.GuiUtils;

public abstract class Theme {
	
	public abstract WindowRenderer getWindowRenderer();
	public abstract ThemeFontRenderer getFontRenderer();
	public abstract ButtonRenderer getButtonRenderer();
}
