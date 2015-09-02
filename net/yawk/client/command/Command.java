package net.yawk.client.command;

import net.minecraft.network.play.client.C01PacketChatMessage;
import net.yawk.client.Client;

public abstract class Command {
	
	private String name;
	private String call;
	private String desc;
	
	public Command(String name, String call, String desc) {
		this.name = name;
		this.call = call;
		this.desc = desc;
	}
	
	public String getName() {
		return name;
	}
	
	public String getCallName() {
		return call;
	}
	
	public String getDesc() {
		return desc;
	}
	
	protected void chat(String msg){
		Client.getClient().addChat(msg);
	}
	
	protected void message(String msg){
		Client.getClient().getPlayer().sendQueue.addToSendQueue(new C01PacketChatMessage(msg));
	}
	
	public abstract void runCommand(CommandManager cm, Arguments args);
	public abstract Argument[] getArguments(CommandManager cm);
}