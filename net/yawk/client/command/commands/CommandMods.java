package net.yawk.client.command.commands;

import net.yawk.client.Client;
import net.yawk.client.command.Argument;
import net.yawk.client.command.Arguments;
import net.yawk.client.command.Command;
import net.yawk.client.command.CommandManager;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.utils.ChatColor;
import net.yawk.client.utils.Colours;

public class CommandMods extends Command {
	
	public CommandMods() {
		super("Mods", "mods", "Shows you a list of mods");
	}
	
	@Override
	public void runCommand(CommandManager cm, Arguments args) {
		
		StringBuilder builder = new StringBuilder();
		boolean first = true;
		
		for(Mod m : Client.getClient().getModManager().mods){
			
			if(first){
				builder.append(m.getName());
				first = false;
			}else{
				builder.append(", "+m.getName());
			}
		}
		
		chat(builder.toString());
	}

	@Override
	public Argument[] getArguments(CommandManager cm) {
		return null;
	}
}