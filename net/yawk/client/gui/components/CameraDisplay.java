package net.yawk.client.gui.components;

import net.yawk.client.cameras.Camera;
import net.yawk.client.gui.Component;

public class CameraDisplay extends Component{
	
	private Camera camera;
	
	public CameraDisplay(Camera camera) {
		super();
		this.camera = camera;
	}
	
	@Override
	public void draw(int x, int y, int cx, int cy) {
		camera.draw(cx, cy, cx+180, cy+100);
	}

	@Override
	public int getHeight() {
		return 100;
	}

}
