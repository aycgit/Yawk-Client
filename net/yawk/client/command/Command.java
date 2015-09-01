package net.yawk.client.command;

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
	
	public abstract void runCommand(Arguments args);
	public abstract Argument[] getArguments(CommandManager cm);
}