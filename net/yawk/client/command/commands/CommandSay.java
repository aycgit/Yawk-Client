package net.yawk.client.command.commands;

import net.minecraft.client.Minecraft;
import net.yawk.client.Client;
import net.yawk.client.command.Argument;
import net.yawk.client.command.Arguments;
import net.yawk.client.command.Command;
import net.yawk.client.command.CommandManager;
import net.yawk.client.utils.ChatColor;
import net.yawk.client.utils.Colours;

public class CommandSay extends Command {
	
	private Minecraft mc;
	
	public CommandSay() {
		super("Say", "say", "Send a chat message");
		mc = Minecraft.getMinecraft();
	}
	
	@Override
	public void runCommand(CommandManager cm, Arguments args) {
		mc.thePlayer.sendChatMessage(args.get("message"));
	}

	@Override
	public Argument[] getArguments(CommandManager cm) {
		return new Argument[]{
				new Argument("message", false, cm.INFINITE_STRING),
		};
	}
}