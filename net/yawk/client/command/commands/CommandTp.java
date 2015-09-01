package net.yawk.client.command.commands;

import net.minecraft.client.Minecraft;
import net.yawk.client.Client;
import net.yawk.client.command.Argument;
import net.yawk.client.command.Arguments;
import net.yawk.client.command.Command;
import net.yawk.client.command.CommandManager;
import net.yawk.client.utils.ChatColor;
import net.yawk.client.utils.Colours;

public class CommandTp extends Command {
	
	private Minecraft mc;
	
	public CommandTp() {
		super("Tp", "tp", "Teleport to the requested coordinates");
		mc = Minecraft.getMinecraft();
	}
	
	@Override
	public void runCommand(CommandManager cm, Arguments args) {
		
		int x = args.getInteger("x");
		int y = args.getInteger("y");
		int z = args.getInteger("z");
		
		mc.thePlayer.setPosition(x, y, z);
		chat("Teleported to "+x+", "+y+", "+z);
	}

	@Override
	public Argument[] getArguments(CommandManager cm) {
		return new Argument[]{
				new Argument("x", false, cm.INTEGER),
				new Argument("y", false, cm.INTEGER),
				new Argument("z", false, cm.INTEGER),
		};
	}
}