package net.yawk.client.gui.themes;

public class ThemeDefault extends Theme{
	
	private WindowRenderer defaultRenderer = new WindowRenderer();
	private ThemeFontRenderer defaultFont = new ThemeFontRenderer();
	private ButtonRenderer buttonRenderer = new ButtonRenderer();
	
	@Override
	public WindowRenderer getWindowRenderer() {
		return defaultRenderer;
	}
	
	@Override
	public ThemeFontRenderer getFontRenderer() {
		return defaultFont;
	}
	
	@Override
	public ButtonRenderer getButtonRenderer() {
		return buttonRenderer;
	}
}
