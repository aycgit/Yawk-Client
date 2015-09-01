package net.yawk.client.command.commands;

import net.yawk.client.Client;
import net.yawk.client.command.Argument;
import net.yawk.client.command.Arguments;
import net.yawk.client.command.Command;
import net.yawk.client.command.CommandManager;
import net.yawk.client.utils.ChatColor;
import net.yawk.client.utils.Colours;

public class VClip extends Command {
	
	public VClip() {
		super("VClip", "vclip", "Move upwards");
	}
	
	@Override
	public void runCommand(Arguments args) {
		
	}

	@Override
	public Argument[] getArguments(CommandManager cm) {
		return new Argument[]{
				new Argument("height", false, cm.INTEGER),
		};
	}
}