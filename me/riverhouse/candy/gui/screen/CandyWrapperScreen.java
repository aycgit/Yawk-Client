package me.riverhouse.candy.gui.screen;

import me.riverhouse.candy.utils.Wrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;


public class CandyWrapperScreen extends GuiScreen {

	public CandyScreen currentScreen = null;

	public void updateScreen(){
		currentScreen.onUpdate();
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks){
		ScaledResolution scaledRes = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);

		float scale = scaledRes.getScaleFactor() / (float) Math.pow(scaledRes.getScaleFactor(), 2.0D);

		GlStateManager.pushMatrix();
		GlStateManager.translate(0.0F, 0.0F, 1000.0F);
		GlStateManager.scale(scale * 2, scale * 2, scale * 2);

		currentScreen.render();

		GlStateManager.popMatrix();
	}
	
	public void inputUpdate(){
		Minecraft mc = Wrapper.getMinecraft();

		
		int scale = mc.gameSettings.guiScale;

		mc.gameSettings.guiScale = 2;

		if(Keyboard.isCreated()){
			Keyboard.enableRepeatEvents(true);
			while (Keyboard.next()){
				if(Keyboard.getEventKeyState()){
					if(Keyboard.getEventKey() == 1)
						mc.displayGuiScreen(null);
				}else{
					// Gui.instance.keyRelease(Keyboard.getEventKey(),
					// Keyboard.getEventCharacter());
				}
			}
			Keyboard.enableRepeatEvents(false);
		}

		if(Mouse.isCreated()){
			while (Mouse.next()){

				ScaledResolution scaledRes = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);

				int var1 = Mouse.getEventX() * scaledRes.getScaledWidth() / mc.displayWidth;
				int var2 = scaledRes.getScaledHeight() - Mouse.getEventY() * scaledRes.getScaledHeight() / mc.displayHeight - 1;
				
				
				
				if(Mouse.getEventButton() == -1){
					
					if(Mouse.getEventDWheel() != 0){
						int x = var1;
						int y = var2;

						currentScreen.onMouseScroll((Mouse.getEventDWheel() / 100) * 3);
					}

					currentScreen.onMouseUpdate(var1, var2);

					currentScreen.mouse[0] = var1;
					currentScreen.mouse[1] = var2;
				}else if(Mouse.getEventButtonState()){
					currentScreen.onMouseClick(var1, var2, Mouse.getEventButton());
				}else{
					currentScreen.onMouseRelease(var1, var2);
				}
			}
		}

		mc.gameSettings.guiScale = scale;
	}

}
