package net.yawk.client.command.commands;

import net.yawk.client.Client;
import net.yawk.client.command.Argument;
import net.yawk.client.command.Arguments;
import net.yawk.client.command.Command;
import net.yawk.client.command.CommandManager;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.utils.ChatColor;
import net.yawk.client.utils.Colours;

public class CommandAllOff extends Command {
	
	public CommandAllOff() {
		super("AllOff", "alloff", "Switches all mods off");
	}
	
	@Override
	public void runCommand(CommandManager cm, Arguments args) {
		
		for(Mod m : Client.getClient().getModManager().mods){
			if(m.isEnabled()){
				Client.getClient().getModManager().toggle(m);
			}
		}
		
		chat("All hacks switched off");
	}

	@Override
	public Argument[] getArguments(CommandManager cm) {
		return null;
	}
}