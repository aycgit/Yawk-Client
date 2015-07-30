package net.yawk.client.gui;

import com.google.gson.JsonObject;

import net.yawk.client.utils.Colours;

public enum ColourType {
	
	TEXT("Text", 2, 0x00C000C0, false), HIGHLIGHT("Highlight", 0, 0x00C000C0, false), BODY("Body", 0, 0x00C000C0, false), BORDER("Border", 2, 0x8F000000, false), TITLE("Title", 0), TITLE_TEXT("Title Text", 0);
	
	private String name;
	private int colour;
	private int modifiedColour;
	private int modifier;
	private int index;
	private int defaultIndex;
	private boolean combine;
	
	private ColourType(String name, int index){
		this(name, index, 0, false);
	}
	
	private ColourType(String name, int index, int modifier, boolean combine){
		this.name = name;
		this.modifier = modifier;
		this.combine = combine;
		this.defaultIndex = index;
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
	
	public int getOverlayColour() {
		return colour - 0x5F000000;
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
	
	public int getDefaultIndex() {
		return defaultIndex;
	}
	
	public void cycleIndex() {
		
		index++;
		
		if(index >= Colours.options.length){
			index = 0;
		}
		
		setColour(Colours.options[index]);
	}
	
	public static ColourType getTypeByName(String name){
		
		for(ColourType col : values()){
			if(col.getName().equalsIgnoreCase(name)){
				return col;
			}
		}
		
		return null;
	}
}
