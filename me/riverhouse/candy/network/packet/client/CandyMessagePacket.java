package me.riverhouse.candy.network.packet.client;

import me.riverhouse.candy.network.packet.CandyPacket;

public class CandyMessagePacket extends CandyPacket {

	private String user;
	private String message;
	
	public CandyMessagePacket(String user, String message){
		super(CandyMessagePacket.class.getSimpleName());
		this.user = user;
		this.message = message;
	}
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
