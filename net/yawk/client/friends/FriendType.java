package net.yawk.client.friends;

import net.minecraft.util.EnumChatFormatting;

public enum FriendType {
	
	NEUTRAL("Neutral", null, false),
	FRIEND("Friend", EnumChatFormatting.GREEN.toString(), true),
	ENEMY("Enemy", EnumChatFormatting.RED.toString(), false),
	TEAM_MEMBER("Team", EnumChatFormatting.YELLOW.toString(), true);
	
	private String name;
	private String colour;
	private boolean protection;
	
	private FriendType(String name, String colour, boolean protection){
		this.name = name;
		this.colour = colour;
		this.protection = protection;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	public String getColour() {
		return colour;
	}
	
	public boolean isProtected() {
		return protection;
	}
}
