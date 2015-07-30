package net.yawk.client.gui.hub;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.GuiScreen;
import net.yawk.client.Client;
import net.yawk.client.gui.Canvas;
import net.yawk.client.gui.components.Slider;
import net.yawk.client.modmanager.values.SliderValue;
import net.yawk.client.utils.ClientUtils;
import net.yawk.client.utils.GuiUtils;
import net.yawk.client.utils.MillisecondTimer;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.*;

public class GuiHub extends GuiScreen {

	private int buttonPaddingX = 30;
	private int buttonWidth = 16;
	private int buttonSlateSpace = 30;
	private int cellSize = 100;
	private int cellPadding = 5;

	private int transition, slateIndex, rotation;
	private Slate lastSlate;
	private boolean direction;

	private State state = State.IDLE;
	private String trail = "";
	private MillisecondTimer timer = new MillisecondTimer(5);

	public ArrayList<Slate> slates = new ArrayList<Slate>();

	public GuiHub(){

	}

	@Override
	public void drawScreen(int x, int y, float f){

		if(this.state == State.LOADING){
			drawLoadingScreen();
		}else if(this.state == State.FAILED){
			drawFailedScreen();
		}else if(this.state == State.CONNECTED){
			renderSlates(x, y);
		}
	}

	private void updateTrail() {

		if(trail.length() >= 3){
			trail = "";
			return;
		}

		trail += ".";
	}

	public void renderSlates(int x, int y){

		if(lastSlate != null){

			if(direction){
				transition+=20;
				renderSlate(x, y, lastSlate, transition);
				renderSlate(x, y, slates.get(slateIndex), transition - width);
			}else{
				transition-=20;
				renderSlate(x, y, lastSlate, transition);
				renderSlate(x, y, slates.get(slateIndex), transition + width);
			}

			if(Math.abs(transition) >= width-20){
				transition = 0;
				lastSlate = null;
			}

		}else{

			if(needsLeft()){
				if(mouseOverLeft(x,y)){
					GuiUtils.drawBorderedRect(buttonPaddingX, this.height/2 - 20, buttonPaddingX+buttonWidth, this.height/2 + 20, 1, 0xFFFFFFFF, 0x5F000000);
					GuiUtils.drawTriangle(buttonPaddingX + buttonWidth/2, this.height/2, 270, 0xFFFFFFFF);
				}else{
					GuiUtils.drawRect(buttonPaddingX, this.height/2 - 20, buttonPaddingX+buttonWidth, this.height/2 + 20, 0x5F000000);
					GuiUtils.drawTriangle(buttonPaddingX + buttonWidth/2, this.height/2, 270, 0xFF9F9F9F);
				}
			}

			if(needsRight()){
				if(mouseOverRight(x,y)){
					GuiUtils.drawBorderedRect(this.width - buttonPaddingX - buttonWidth, this.height/2 - 20, this.width - buttonPaddingX, this.height/2 + 20, 1, 0xFFFFFFFF, 0x5F000000);
					GuiUtils.drawTriangle(this.width - buttonPaddingX - buttonWidth/2, this.height/2, 90, 0xFFFFFFFF);
				}else{
					GuiUtils.drawRect(this.width - buttonPaddingX - buttonWidth, this.height/2 - 20, this.width - buttonPaddingX, this.height/2 + 20, 0x5F000000);
					GuiUtils.drawTriangle(this.width - buttonPaddingX - buttonWidth/2, this.height/2, 90, 0xFF9F9F9F);
				}
			}

			renderSlate(x, y, slates.get(slateIndex), 0);
		}
	}

	private void drawLoadingScreen(){

		if(timer.output()){

			rotation += 2;

			if(rotation % 50 == 0){
				updateTrail();
			}
		}

		int posX = width/2;
		int posY = height/2;
		int size = 10;

		glTranslatef(posX, posY, 0);
		glRotatef(rotation, 0, 0, 1);

		GuiUtils.drawRect(-size, -size, size, size, 0xFF58B4ED);

		glRotatef(-rotation, 0, 0, 1);
		glTranslatef(-posX, -posY, 0);

		String loading = "Loading"+trail;
		Client.getClient().getFontRenderer().drawString(loading, posX - Client.getClient().getFontRenderer().getStringWidth(loading)/2, posY + 30, 0xFFFFFFFF);
	}

	private void drawFailedScreen(){

		int posX = width/2;
		int posY = height/2;
		int size = 10;

		glTranslatef(posX, posY, 0);

		GuiUtils.drawRect(-size, -size, size, size, 0xFFFF0000);

		glTranslatef(-posX, -posY, 0);

		String failed = "Failed";
		Client.getClient().getFontRenderer().drawString(failed, posX - Client.getClient().getFontRenderer().getStringWidth(failed)/2, posY + 30, 0xFFFFFFFF);
	}

	@Override
	public void initGui() {

		if(this.state == State.IDLE && slates.size() == 0){
			this.state = State.LOADING;
			Thread thread = new Thread(new HubLoadingThread(this));
			thread.start();
		}
	}

	@Override
	protected void mouseClicked(int x, int y, int b) throws IOException {
		if(needsLeft() && mouseOverLeft(x,y)){

			lastSlate = slates.get(slateIndex);

			slateIndex++;

			if(slateIndex >= slates.size()){
				slateIndex = slates.size() - 1;
				lastSlate = null;
			}

			direction = true;
			transition = 0;

		}else if(needsRight() && mouseOverRight(x,y)){

			lastSlate = slates.get(slateIndex);

			slateIndex--;

			if(slateIndex < 0){
				slateIndex = 0;
				lastSlate = null;
			}

			direction = false;
			transition = 0;
		}
	}

	@Override
	protected void mouseReleased(int x, int y, int state) {

	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	private void renderSlate(int x, int y, Slate slate, int xOffset){

		int alignXMiddle = width/2 - (5*cellSize)/2;

		int lineNum = slates.size() / 5;
		int alignYMiddle = height/2 - ((lineNum+2)*cellSize)/2;

		int num = 0;

		int w = cellSize - cellPadding;
		int h = cellSize - cellPadding;

		for(SquareCell s : slate.getCells()){

			int column = num % 5;
			int row = num / 5;

			int xPos = xOffset + column*cellSize + alignXMiddle + cellPadding;
			int yPos = alignYMiddle + row*cellSize + cellPadding;

			renderCell(s, x, y, xPos, yPos, w, h);

			num++;
		}
	}

	private void renderCell(SquareCell cell, int x, int y, int xPos, int yPos, int w, int h){

		if(mouseOverButton(x, y, xPos, yPos, xPos+w, yPos+h)){
			GuiUtils.drawRect(xPos, yPos, xPos+w, yPos+h, cell.getColour() + 0x00303030);
		}else{
			GuiUtils.drawRect(xPos, yPos, xPos+w, yPos+h, cell.getColour());
		}

		//GuiUtils.drawRect(xPos, yPos, xPos+w, yPos+11, 0x7F2B2B2B);

		Client.getClient().getFontRenderer().drawStringWithShadow(cell.getTitle(), xPos + w/2 - Client.getClient().getFontRenderer().getStringWidth(cell.getTitle())/2, yPos + 2, 0xFFFFFFFF, true);

		Client.getClient().getFontRenderer().drawSplitString(cell.getContents(), xPos + 3, yPos + 14, w-3, 0xFFFFFFFF);
	}

	public boolean mouseOverButton(int x, int y, int bx, int by, int bx1, int by1){
		return x >= bx && x <= bx1 && y >= by && y <= by1;
	}

	public boolean mouseOverLeft(int x, int y){
		return x >= buttonPaddingX && x <= buttonPaddingX+buttonWidth && y >= this.height/2 - 20 && y <= this.height/2 + 20;
	}

	public boolean mouseOverRight(int x, int y){
		return x >= this.width - buttonPaddingX - buttonWidth && x <= this.width - buttonPaddingX && y >= this.height/2 - 20 && y <= this.height/2 + 20;
	}

	private boolean needsLeft(){
		return slateIndex < slates.size()-1;
	}

	private boolean needsRight(){
		return slateIndex > 0;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
}

enum State{
	IDLE, CONNECTED, LOADING, FAILED;
}
