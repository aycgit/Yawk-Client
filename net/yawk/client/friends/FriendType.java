package net.yawk.client.friends;

import net.minecraft.util.EnumChatFormatting;

public enum FriendType {
	
	NEUTRAL("Neutral", null), FRIEND("Friend", EnumChatFormatting.GREEN.toString()), ENEMY("Enemy", EnumChatFormatting.RED.toString()), TEAM_MEMBER("Team", EnumChatFormatting.YELLOW.toString());
	
	private String name;
	private String colour;
	
	private FriendType(String name, String colour){
		this.name = name;
		this.colour = colour;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	public String getColour() {
		return colour;
	}
}
