package net.yawk.client.gui.hub;

import net.yawk.client.gui.Canvas;
import net.yawk.client.gui.components.Slider;
import net.yawk.client.gui.maps.LargeMap;
import net.yawk.client.modmanager.values.SliderValue;

public class MapSlate extends Slate{
	
	private LargeMap map;
	private Canvas options;
	private SliderValue scale;
	
	public MapSlate(GuiHub hub) {
		super("Map", null, hub);
		
		map = new LargeMap(hub.colourModifier);
		options = new Canvas(hub.width/2 - 50, 3, 100, 50);
		scale = new SliderValue("Scale", 1, 7, 2, false);
		options.components.add(new Slider(options, scale));
	}
	
	@Override
	public void renderSlate(int x, int y) {
		
		map.draw(hub.width/2, hub.height/2, scale.getValue());
		
		options.draw(x, y);
	}
	
	@Override
	public void init() {
		System.out.println("MAP INIT");
		map.registerFactionsListener();
	}

	@Override
	public void close() {
		System.out.println("MAP CLOSE");
		map.unregisterFactionsListener();
	}

	@Override
	public void mouseClicked(int x, int y) {
		options.mouseClicked(x, y);
	}
	
	@Override
	public void mouseReleased(int x, int y, int state) {
		options.mouseReleased(x, y, state);
	}
}
