package net.yawk.client.command.commands;

import net.minecraft.client.Minecraft;
import net.yawk.client.Client;
import net.yawk.client.command.Argument;
import net.yawk.client.command.Arguments;
import net.yawk.client.command.Command;
import net.yawk.client.command.CommandManager;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.utils.ChatColor;
import net.yawk.client.utils.Colours;

public class CommandToggle extends Command {
		
	public CommandToggle() {
		super("Toggle", "toggle", "Change the enabled state of a mod");
	}
	
	@Override
	public void runCommand(CommandManager cm, Arguments args) {
		
		Mod m = Client.getClient().getModManager().getModByName(args.get("name"));
		
		if(m != null){
			Client.getClient().getModManager().toggle(m);
			chat("Mod "+m.getName()+" set to "+ (m.isEnabled()? "enabled":"disabled"));
		}else{
			chat("Mod not found!");
		}
	}

	@Override
	public Argument[] getArguments(CommandManager cm) {
		return new Argument[]{
				new Argument("name", false, cm.STRING),
		};
	}
}