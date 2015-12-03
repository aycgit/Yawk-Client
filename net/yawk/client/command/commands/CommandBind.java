package net.yawk.client.command.commands;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.yawk.client.Client;
import net.yawk.client.command.Argument;
import net.yawk.client.command.Arguments;
import net.yawk.client.command.Command;
import net.yawk.client.command.CommandManager;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.utils.ChatColor;
import net.yawk.client.utils.Colours;

public class CommandBind extends Command {
	
	public CommandBind() {
		super("Bind", "bind", "Change the keybind of a mod");
	}
	
	@Override
	public void runCommand(CommandManager cm, Arguments args) {
		
		Mod m = Client.getClient().getModManager().getModByName(args.get("name"));
		
		if(m != null){
			
			m.setKeybind(Keyboard.getKeyIndex(args.get("key").toUpperCase()));
			chat("Mod "+m.getName()+" binded to "+ m.getKeyName());
		}else{
			chat("Mod not found!");
		}
	}

	@Override
	public Argument[] getArguments(CommandManager cm) {
		return new Argument[]{
				new Argument("name", false, cm.STRING),
				new Argument("key", false, cm.STRING),
		};
	}
}