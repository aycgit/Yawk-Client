package net.yawk.client.command;

import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C01PacketChatMessage;
import net.minecraft.util.EnumChatFormatting;
import net.yawk.client.Client;

public abstract class Command {
	
	private String name, call, desc;
	
	protected Minecraft mc;
	
	public Command(String name, String call, String desc) {
		this.name = name;
		this.call = call;
		this.desc = desc;
		
		mc = Minecraft.getMinecraft();
	}
	
	public String getName() {
		return name;
	}
	
	public String getCallName() {
		return call;
	}
	
	public String getSummary(CommandManager cm){
		
		StringBuilder builder = new StringBuilder();
		
		builder.append(EnumChatFormatting.BLUE+getCallName());
		
		for(Argument arg : getArguments(cm)){
			
			if(arg.isOptional()){
				builder.append(EnumChatFormatting.AQUA+" ["+arg.getName()+"]");
			}else{
				builder.append(EnumChatFormatting.GOLD+" ["+arg.getName()+"]");
			}
			
		}
		
		return builder.toString();
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