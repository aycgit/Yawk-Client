package net.yawk.client.command.commands;

import net.minecraft.client.Minecraft;
import net.yawk.client.Client;
import net.yawk.client.command.Argument;
import net.yawk.client.command.Arguments;
import net.yawk.client.command.Command;
import net.yawk.client.command.CommandManager;
import net.yawk.client.utils.ChatColor;
import net.yawk.client.utils.Colours;

public class CommandVClip extends Command {
	
	public CommandVClip() {
		super("VClip", "vclip", "Move upwards");
	}
	
	@Override
	public void runCommand(CommandManager cm, Arguments args) {
		
		if(args.getBoolean("pitch")){
			mc.thePlayer.rotationPitch = -90;
		}
		
		mc.thePlayer.setPosition(mc.thePlayer.posX, mc.thePlayer.posY+args.getInteger("height"), mc.thePlayer.posX);
	}

	@Override
	public Argument[] getArguments(CommandManager cm) {
		return new Argument[]{
				new Argument("height", false, cm.INTEGER),
				new Argument("pitch", true, cm.BOOLEAN),
		};
	}
}