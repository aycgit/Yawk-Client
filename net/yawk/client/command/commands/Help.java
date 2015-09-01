package net.yawk.client.command.commands;

import net.yawk.client.Client;
import net.yawk.client.command.Argument;
import net.yawk.client.command.Arguments;
import net.yawk.client.command.Command;
import net.yawk.client.command.CommandManager;
import net.yawk.client.utils.ChatColor;
import net.yawk.client.utils.Colours;

public class Help extends Command {
	
	public Help() {
		super("Help", "help", "Shows you commands.");
	}
	
	@Override
	public void runCommand(CommandManager cm, Arguments args) {
		for(Command c : cm.commandsList) {
			if(c instanceof Help) {
				continue;
			}
			Client.getClient().addChat(ChatColor.RED + c.getName() + ChatColor.GRAY + " | " + ChatColor.GOLD + "." + c.getCallName() + ChatColor.GRAY + " | " + ChatColor.DARK_GRAY + c.getDesc());
		}
	}

	@Override
	public Argument[] getArguments(CommandManager cm) {
		return null;
	}
}