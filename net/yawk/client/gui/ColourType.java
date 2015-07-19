package net.yawk.client.gui;

import net.yawk.client.utils.Colours;

public enum ColourType {
	
	TEXT("Text", 2, 0x00C000C0, false), HIGHLIGHT("Highlight", 0, 0x00C000C0, false), BORDER("Border", 2, 0xAF000000, false), TITLE("Title", 3), TITLE_TEXT("Title Text", 0);
	
	private String name;
	private int colour;
	private int modifiedColour;
	private int modifier;
	private int index;
	private boolean combine;
	
	private ColourType(String name, int index){
		this(name, index, 0, false);
	}
	
	private ColourType(String name, int index, int modifier, boolean combine){
		this.name = name;
		this.modifier = modifier;
		this.combine = combine;
		setIndex(index);
	}
	
	public String getName() {
		return name;
	}
	
	public int getColour() {
		return colour;
	}
	
	public int getModifiedColour() {
		return modifiedColour;
	}
	
	public void setColour(int colour) {
		
		this.colour = colour;
		
		if(combine){
			this.modifiedColour = colour + modifier;
		}else{
			this.modifiedColour = colour - modifier;
		}
	}
	
	public int getIndex() {
		return index;
	}
	
	public void setIndex(int index) {
		this.index = index;
		setColour(Colours.options[index]);
	}
	
	public void cycleIndex() {
		
		index++;
		
		if(index >= Colours.options.length){
			index = 0;
		}
		
		setColour(Colours.options[index]);
	}
}
