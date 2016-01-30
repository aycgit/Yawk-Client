package net.yawk.client.command.commands;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import net.yawk.client.Client;
import net.yawk.client.command.Argument;
import net.yawk.client.command.Arguments;
import net.yawk.client.command.Command;
import net.yawk.client.command.CommandManager;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.utils.ChatColor;
import net.yawk.client.utils.ClientUtils;
import net.yawk.client.utils.Colours;

public class CommandInsult extends Command {

	public CommandInsult() {
		super("Insult", "insult", "Sends a random insult");
	}

	private int fails = 0;

	@Override
	public void runCommand(CommandManager cm, Arguments args) {

		(new Thread(){
			public void run(){
				try {

					Document doc = Jsoup.connect("http://insultgenerator.org/").userAgent(ClientUtils.USER_AGENT).get();

					String msg = doc.select("div.wrap").text().trim();

					if(msg.length() > 65){
						run();
						return;
					}
					
					message(msg);
					chat("Insult Sent");
					fails = 0;
					
				} catch (IOException e) {
					
					e.printStackTrace();
					if(fails < 3){
						fails++;
						run();
					}else{
						chat("Couldn't connect!");
						fails = 0;
					}
				}
			}
		}).start();

	}

	@Override
	public Argument[] getArguments(CommandManager cm) {
		return null;
	}
}