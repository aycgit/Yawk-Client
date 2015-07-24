package net.yawk.client.gui.hub;

import java.io.IOException;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.GuiScreen;
import net.yawk.client.Client;
import net.yawk.client.utils.GuiUtils;

public class GuiHub extends GuiScreen {
	
	private int buttonPaddingX = 30;
	private int buttonWidth = 16;
	private int buttonSlateSpace = 30;
	private int cellSize = 100;
	private int cellPadding = 5;
	
	private int transition;
	private Slate lastSlate;
	private boolean direction;
	
	private int slateIndex;
	
	private SquareCell[] example = new SquareCell[]{
			new SquareCell("Admin Mode", 0x5FCF0000, "Add, remove or modify plugins and files"),
			new SquareCell("Blog", 0x5F00CF00, "Currently overhauling the Yawk Client GUI"),
			new SquareCell("News", 0x5F0000CF, "Nothing happened"),
			new SquareCell("ESPXL", 0x5FC0C000, "Better ESP!"),
			new SquareCell("Throwables", 0x5F00C0C0, "Throw stuff"),
			new SquareCell("Website Down", 0x5F5050C0, "Currently fixing a few problems with it"),
			new SquareCell("Plugins", 0x5FC000C0, "Over 30 brand new plugins have been approved for Yawk Client. Install them in the \"Get Plugins\" window of the Yawk GUI."),
			new SquareCell("Flying", 0x5F50C050, "Up in the air"),
			new SquareCell("NCP NoFall", 0x5F000050, "It exists now"),
			new SquareCell("YawkBot", 0x5F500050, "Spam a server with tons of accounts at the same time. Great if you want to get revenge on some people."),
	};
	
	private SquareCell[] example1 = new SquareCell[]{
			new SquareCell("Throwables", 0x5F50C0C0, "Throw stuff"),
			new SquareCell("Test", 0x5FCF0000, "This is a test guys"),
			new SquareCell("Website Down", 0x5F5C50C0, "(Maintainence)"),
			new SquareCell("Flying", 0x5F55C055, "Up in the air"),
			new SquareCell("Plugins", 0x5FC500C0, "They're nice"),
			new SquareCell("Blog", 0x5F05CF00, "Stuff happened"),
			new SquareCell("News", 0x5F0500CF, "Nothing happened"),
			new SquareCell("JSON.simple", 0x5FC0C000, "It's gone"),
	};
	
	private SquareCell[] example2 = new SquareCell[]{
			new SquareCell("Plugins", 0x5F00C0C0, "They're nice"),
			new SquareCell("Website Down", 0x5F5050C0, "(Maintainence)"),
			new SquareCell("Blog", 0x5F00CF00, "Stuff happened"),
			new SquareCell("News", 0x5F5000CF, "Grexit oh noes"),
			new SquareCell("Google GSON", 0x5FCF50C0, "Fancy"),
			new SquareCell("Flying", 0x5FC0C050, "Up in the air"),
			new SquareCell("JSON.simple", 0x5FC0C000, "It's gone"),
			new SquareCell("YawkBot", 0x5FC0C000, "It's nice"),
			new SquareCell("Throwables", 0x5FC0C0C0, "Throw stuff"),
	};
	
	private Slate[] slates = new Slate[]{
			new Slate(example),
			new Slate(example1),
			new Slate(example2),
	};
	
	@Override
	public void drawScreen(int x, int y, float f){
		
		if(lastSlate != null){
			
			//int padding = buttonPaddingX+buttonWidth+buttonSlateSpace;
			
			if(direction){
				transition+=20;
				renderSlate(x, y, lastSlate, transition);
				renderSlate(x, y, slates[slateIndex], transition - width);
			}else{
				transition-=20;
				renderSlate(x, y, lastSlate, transition);
				renderSlate(x, y, slates[slateIndex], transition + width);
			}
			
			if(Math.abs(transition) >= width-20){
				transition = 0;
				lastSlate = null;
			}
			
		}else{
			
			//GuiUtils.drawBorderedRect(buttonPaddingX+buttonWidth+buttonSlateSpace, this.height/4, this.width-buttonPaddingX-buttonWidth-buttonSlateSpace, this.height/4 * 3, 1, 0xFFFFFFFF, 0x5F000000);
			
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
			
			renderSlate(x, y, slates[slateIndex], 0);
		}
		
		String name = "Yawk Media Center";
		Client.getClient().getFontRenderer().drawStringWithShadow(name, width/2 - Client.getClient().getFontRenderer().getStringWidth(name)/2, 2, 0xFFFFFFFF, true);
	}
	
	@Override
	protected void mouseClicked(int x, int y, int b) throws IOException {
		if(needsLeft() && mouseOverLeft(x,y)){
			
			lastSlate = slates[slateIndex];
			
			slateIndex++;
			
			if(slateIndex >= slates.length){
				slateIndex = slates.length - 1;
				lastSlate = null;
			}
			
			direction = true;
			transition = 0;
			
		}else if(needsRight() && mouseOverRight(x,y)){
			
			lastSlate = slates[slateIndex];
			
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
	public boolean doesGuiPauseGame() {
		return false;
	}
	
	private void renderSlate(int x, int y, Slate slate, int xOffset){
		
		int alignXMiddle = width/2 - (5*cellSize)/2;
		
		int lineNum = slates.length / 5;
		int alignYMiddle = height/2 - ((lineNum+2)*cellSize)/2;
		
		int num = 0;
		
		for(SquareCell s : slate.getCells()){
			
			int onLine = num % 5;
			int line = num / 5;
			
			int xPos = xOffset + onLine*cellSize + alignXMiddle + cellPadding;
			int yPos = alignYMiddle + line*cellSize + cellPadding;
			int w = cellSize - cellPadding;
			int h = cellSize - cellPadding;
			
			renderCell(s, x, y, xPos, yPos, w, h);
			
			num++;
		}
	}
	
	private void renderCell(SquareCell cell, int x, int y, int xPos, int yPos, int w, int h){
		
		if(mouseOverButton(x, y, xPos, yPos, xPos+w, yPos+h)){
			GuiUtils.drawRect(xPos, yPos, xPos+w, yPos+h, cell.getColour() + 0x30303030);
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
		return slateIndex < slates.length-1;
	}
	
	private boolean needsRight(){
		return slateIndex > 0;
	}
}
