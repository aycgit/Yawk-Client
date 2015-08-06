package net.yawk.client.gui.hub;

import net.yawk.client.gui.maps.Map;

public class MapSlate extends Slate{

	public MapSlate(GuiHub hub) {
		super("Map", null, hub);
	}
	
	private Map map = new Map();
	
	@Override
	public void renderSlate(int x, int y, int xOffset) {
		map.draw(hub.width/2 + xOffset, hub.height/2);
	}
}
