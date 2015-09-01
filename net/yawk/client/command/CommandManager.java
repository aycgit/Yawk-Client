package net.yawk.client.command;

import java.util.ArrayList;
import java.util.List;

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

		Command command = getCommandByName(parts[0]);

		if(command == null){
			return "Command not found. Type " + Chars.QUOTE + prefix + "help" + Chars.QUOTE + " for a list of all commands";
		}else{
			args = command.getArguments(this);
		}
		
		if(args != null){
			
			if(parts.length-1 < getRequiredArguments(args).size()){
				return "Not enough arguments specified!";
			}
			
			for(int i = 1; i < args.length; i++){

				Argument arg = args[i];

				String input = parts[i];

				if(!arg.getType().isValid(input)){
					return "Invalid argument type " + Chars.QUOTE + arg.getName() + Chars.QUOTE + " needs to be in " + arg.getType().getName() +" form";
				}else{
					//TODO: Parse argument
				}
			}
		}

		command.runCommand(parts);

		return null;
	}

	private List<Argument> getRequiredArguments(Argument[] args){
		
		List<Argument> required = new ArrayList<Argument>();
		
		for(Argument arg : args){
			
			if(arg.isOptional()){
				return required;
			}
			
			required.add(arg);
		}
		
		return required;
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