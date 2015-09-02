package net.yawk.client.command.commands;

import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.yawk.client.Client;
import net.yawk.client.command.Argument;
import net.yawk.client.command.Arguments;
import net.yawk.client.command.Command;
import net.yawk.client.command.CommandManager;
import net.yawk.client.utils.ChatColor;
import net.yawk.client.utils.Colours;
import net.yawk.client.utils.MinecraftUtils;

/**
 * This is taken from direkt so we can have nice things
 * @author ceanko
 */
public class CommandDamage extends Command {
	
	public CommandDamage() {
		super("Damage", "damage", "Damages your player");
	}
	
	@Override
	public void runCommand(CommandManager cm, Arguments args) {

		for (int i = 0; i < 64; i++) {
			MinecraftUtils.sendDirectPacket(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + 0.05, mc.thePlayer.posZ, false));
			MinecraftUtils.sendDirectPacket(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, false));
		}

		MinecraftUtils.sendPacket(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX + Integer.MAX_VALUE, mc.thePlayer.posY + Integer.MAX_VALUE, mc.thePlayer.posZ + Integer.MAX_VALUE, mc.thePlayer.onGround));

		if(args.getBoolean("suicide")) {

			for (int i = 0; i < 1024; i++) {
				MinecraftUtils.sendDirectPacket(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + 0.05, mc.thePlayer.posZ, false));
				MinecraftUtils.sendDirectPacket(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, false));
			}

			MinecraftUtils.sendPacket(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX + Integer.MAX_VALUE, mc.thePlayer.posY + Integer.MAX_VALUE, mc.thePlayer.posZ + Integer.MAX_VALUE, mc.thePlayer.onGround));
		}
	}

	@Override
	public Argument[] getArguments(CommandManager cm) {
		return new Argument[]{
				new Argument("suicide", true, cm.BOOLEAN),
		};
	}
}