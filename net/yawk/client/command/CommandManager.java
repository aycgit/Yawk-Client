package net.yawk.client.command;

import java.util.ArrayList;

import net.yawk.client.command.datatypes.*;
import net.yawk.client.utils.Chars;
import net.yawk.client.utils.ReflectionUtils;

public class CommandManager {
	
	public static ArrayList<Command> commandsList = new ArrayList<Command>();
	public static String prefix = ".";
	
	public BooleanDataType BOOLEAN = new BooleanDataType();
	public IntegerDataType INTEGER = new IntegerDataType();
	public StringDataType STRING = new StringDataType();
	
	public CommandManager() {
		this.addClassesFromPackage("net.yawk.client.command.commands");
	}
	
	public void addClassesFromPackage(String packageName) {
        for (Class<?> clazz : ReflectionUtils.getClasses(packageName)) {
            try {
                Object obj = clazz.newInstance();
                if (obj instanceof Command) {
                    commandsList.add((Command) obj);
                }
            } catch (Exception e) {}
        }
    }
	
	public String run(String[] parts){
		
		Argument[] args = null;
		Command command = null;
		
		for(int i = 0; i < parts.length; i++){
			
			if(i == 0){
				
				command = getCommandByName(parts[0]);
				
				if(command == null){
					return "Command not found. Type " + Chars.QUOTE + prefix + "help" + Chars.QUOTE + " for a list of all commands";
				}else{
					args = command.getArguments(this);
				}
				
				continue;
			}
			
			if(args != null){
				
				Argument arg = args[i];
				String input = parts[i];
				
				if(!arg.getType().isValid(input)){
					return "Invalid argument type " + Chars.QUOTE + arg.getName() + Chars.QUOTE + " needs to be in " + arg.getType().getName() +" form";
				}
			}
		}
		
		command.runCommand(parts);
		
		return null;
	}
	
	private Command getCommandByName(String name){
		
		for(Command cmd : commandsList){
			if(cmd.getCallName().equalsIgnoreCase(name)){
				return cmd;
			}
		}
		
		return null;
	}
	
	public static ArrayList<Command> getCommands() {
		return commandsList;
	}
}