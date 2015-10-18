package me.riverhouse.candy.network.packet;

public class CandyPacket {
	
	private String packetType;
	
	public CandyPacket(String packetType) {
		this.packetType = packetType;
	}

	public String getPacketType() {
		return packetType;
	}

	public void setPacketType(String packetType) {
		this.packetType = packetType;
	}
	
}
