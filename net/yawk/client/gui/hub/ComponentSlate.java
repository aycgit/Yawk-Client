package net.yawk.client.gui.hub;

import net.yawk.client.Client;
import net.yawk.client.gui.Canvas;
import net.yawk.client.gui.ScalerPosition;

public abstract class ComponentSlate extends Slate{

	private Client client;
	private Canvas options;
	private GuiHub hub;
	
	public ComponentSlate(String name, final GuiHub hub, Client client) {
		super(name, null, hub);
		this.client = client;
		this.hub = hub;
		
		ScalerPosition pos = new ScalerPosition(){
			
			@Override
			public int getX() {
				return hub.width/2 - 100;
			}
			
			@Override
			public int getY() {
				return 12;
			}
			
		};
		
		options = new Canvas(pos, 200, 300);
	}

	@Override
	public void renderSlate(int x, int y) {
		
		options.draw(x, y);
	}
	
	public abstract void init();
}
