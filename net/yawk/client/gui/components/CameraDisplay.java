package net.yawk.client.gui.components;

import net.yawk.client.Client;
import net.yawk.client.cameras.Camera;
import net.yawk.client.gui.Component;
import net.yawk.client.gui.IPanel;
import net.yawk.client.utils.GuiUtils;

public class CameraDisplay extends Component{
	
	private IPanel panel;
	private Camera camera;
	private boolean draggingHeight;
	
	public CameraDisplay(IPanel panel, Camera camera) {
		super();
		this.panel = panel;
		this.camera = camera;
	}
	
	@Override
	public void draw(int x, int y, int cx, int cy) {
		
		if(draggingHeight){
			
			camera.setHeight((y - cy) * 2);
			
			if(camera.getHeight() > 250){
				camera.setHeight(250);
			}
			
			if(camera.getHeight() < 30){
				camera.setHeight(30);
			}
		}
		
		if(camera.isFrameBufferUpdated()){
			camera.draw(cx, cy, cx+camera.getWidth()/2, cy+camera.getHeight()/2);
		}else{
			
			String closeMessage = "Close GUI to update";
			
			Client.getClient().getFontRenderer().drawString(closeMessage,
					cx+camera.getWidth()/4 - Client.getClient().getFontRenderer().getStringWidth(closeMessage)/2,
					cy+camera.getHeight()/4 - Client.getClient().getFontRenderer().FONT_HEIGHT/2,
					0xFFFFFFFF);
		}
		
		GuiUtils.drawRect(cx+camera.getWidth()/4 - 15, cy+camera.getHeight()/2, cx+camera.getWidth()/4 + 15, cy+camera.getHeight()/2 + 4, 0x5F000000);
		
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
		return mouseX > cx+camera.getWidth()/4-15 && mouseX <= cx+camera.getWidth()/4+15 && mouseY > cy+camera.getHeight()/2 && mouseY <= cy+camera.getHeight()/2+5;
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
