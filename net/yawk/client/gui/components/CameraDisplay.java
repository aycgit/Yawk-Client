package net.yawk.client.gui.components;

import net.yawk.client.cameras.Camera;
import net.yawk.client.gui.Component;
import net.yawk.client.gui.IPanel;

public class CameraDisplay extends Component{
	
	private IPanel panel;
	private Camera camera;
	private boolean draggingHeight, changed;
	
	public CameraDisplay(IPanel panel, Camera camera) {
		super();
		this.panel = panel;
		this.camera = camera;
	}
	
	@Override
	public void draw(int x, int y, int cx, int cy) {
		
		if(draggingHeight){
			
			camera.setHeight((y - cy) * 2);
			
			if(camera.getHeight() > 200){
				camera.setHeight(200);
			}
			
			if(camera.getHeight() < 0){
				camera.setHeight(0);
			}
		}
		
		if(camera.isFrameBufferUpdated()){
			camera.draw(cx, cy, cx+camera.getWidth()/2, cy+camera.getHeight()/2);
		}
		
	}
	
	@Override
	public void mouseClicked(int x, int y, int cx, int cy) {
		
		if(mouseOverHeightSlider(x, y, cx, cy)){
			draggingHeight = true;
		}
	}

	@Override
	public void mouseReleased(int mouseX, int mouseY, int state) {
		
		if(draggingHeight){
			camera.makeNewFrameBuffer();
		}
		
		draggingHeight = false;
	}
	
	private boolean mouseOverHeightSlider(int mouseX, int mouseY, int cx, int cy){
		return mouseX > cx && mouseX < cx+panel.getWidth() && mouseY > cy+camera.getHeight()/2 && mouseY < cy+camera.getHeight()/2+10;
	}

	@Override
	public int getWidth() {
		return camera.getWidth()/2;
	}
	
	@Override
	public int getHeight() {
		return camera.getHeight()/2;
	}

}
