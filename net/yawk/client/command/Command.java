package net.yawk.client.command;

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
	
	public abstract void runCommand(String message);
	public abstract 
}