package me.riverhouse.candy.gui.screen.clickGui;

import java.util.ArrayList;

import javax.vecmath.Vector2d;

import me.riverhouse.candy.gui.screen.CandyScreen;
import me.riverhouse.candy.gui.screen.clickGui.parts.Frame;
import me.riverhouse.candy.gui.screen.clickGui.parts.Slider;
import me.riverhouse.candy.gui.screen.clickGui.themes.Simple.SimpleThemeUI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

public class ClickGui extends CandyScreen {

	public ArrayList<Frame> frames = new ArrayList<Frame>();

	private Frame currentFrame;
	private boolean dragging = false;
	private Vector2d dragOffset; 
	private Theme theme;
	
	public ClickGui(){
		theme = new SimpleThemeUI();

//		for(Frame f1 : frames){		
//			for(FrameInfo info : Windu.getInstance().getFrameInfoManager().getInfo()){
//				if(f1.getText().equalsIgnoreCase(info.getName())){
//					f1.fromFrameInfo(info);
//				}
//			}
//		}
	}

	public void renderPinned(){
		Minecraft mc = Minecraft.getMinecraft();

		ScaledResolution scaledRes = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);

		float scale = scaledRes.getScaleFactor() / (float) Math.pow(scaledRes.getScaleFactor(), 2.0D);

		GlStateManager.pushMatrix();
		GlStateManager.translate(0.0F, 0.0F, 1000.0F);
		GlStateManager.scale(scale * 2, scale * 2, scale * 2);

		for(Frame f : frames){
			if(f.isPinned()){
				f.render(mouse[0], mouse[1]);

				if(f.isMaximized()){
					ScissorUtils.enableScissoring();
					GL11.glScissor(f.getX() * 2, Display.getHeight() - ((f.getY() + 120) * 2), 200, 202);
					for(Component c : f.getComponents())
						c.render(mouse[0], mouse[1]);
					ScissorUtils.disableScissoring();
				}
			}
		}

		GlStateManager.popMatrix();
	}

	
	public void setTheme(Theme theme1){
		for(Frame f : frames){
			for(Component c : f.getComponents()){
				c.setTheme(theme1);
			}
			f.setTheme(theme1);
		}
	}

	public void onRender(){
		for(Frame f : frames){
			f.render(mouse[0], mouse[1]);

			if(f.isMaximized()){
				GL11.glEnable(GL11.GL_SCISSOR_TEST);
				GL11.glScissor(f.getX() * 2, Display.getHeight() - ((f.getY() + 120) * 2), 200, 202);
				for(Component c : f.getComponents())
					c.render(mouse[0], mouse[1]);
				GL11.glDisable(GL11.GL_SCISSOR_TEST);
			}
		}
	}

	public void onMouseRelease(int x, int y){
		for(Frame f : frames){
			if(f.isMouseOver(x, y)){
				this.currentFrame = f;

				if(f.isMouseOverBar(x, y)){
					this.dragging = false;
				}

				f.onMouseRelease(x, y, 0);
			}
		}
	}

	public void onMouseScroll(int ammount) {
		for(Frame f : frames){
			if(f.isMouseOver(mouse[0], mouse[1])) f.scrollFrame(ammount);
		}
	}	


	public void onMouseClick(int x, int y, int button){
		for(Frame f : frames){
			if(f.isMouseOver(x, y)){
				this.currentFrame = f;

				if(f.isMouseOverBar(x, y)){
					this.dragging = true;
					this.dragOffset = new Vector2d(x - f.getX(), y - f.getY());
				}

				f.onMousePress(x, y, button);
			}
		}

		//if(!frames.isEmpty()) Windu.getInstance().getSettingsManager().saveFrames();
	}


	public void onUpdate(){
		for(Frame f : frames){
			f.updateComponents();
		}
	}

	public void onMouseUpdate(int x, int y){
		for(Frame f : frames){
			for(Component c : f.getComponents()){
				if(c.isMouseOver(x, y)){
					c.onMouseDrag(x, y);
				}else{
					if(c instanceof Slider){
						Slider s = (Slider) c;

						if(s.dragging)
							s.dragging = false;
					}
				}
			}
		}

		if(dragging && this.currentFrame != null){
			int yOffset = (int) ((y - this.dragOffset.getY()) -  currentFrame.getY());

			currentFrame.setX((int) (x - this.dragOffset.getX()));
			currentFrame.setY((int) (y - this.dragOffset.getY()));

			for(Component c : currentFrame.getComponents()) c.setBaseY(c.getBaseY() + yOffset);
		}
	}

}
