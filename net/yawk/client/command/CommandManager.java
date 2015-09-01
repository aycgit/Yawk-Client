package net.yawk.client.command;

import java.util.ArrayList;

import net.yawk.client.utils.ReflectionUtils;

public class CommandManager {
	
	public static ArrayList<Command> command = new ArrayList<Command>();
	public static String preifx = ".";
	
	public CommandManager() {
		this.addClassesFromPackage("net.yawk.client.command.commands");
	}
	
	public void addClassesFromPackage(String packageName) {
        for (Class<?> clazz : ReflectionUtils.getClasses(packageName)) {
            try {
                Object obj = clazz.newInstance();
                if (obj instanceof Command) {
                    command.add((Command) obj);
                }
            } catch (Exception e) {}
        }
    }
	
	public static ArrayList<Command> getCommands() {
		return command;
	}
}