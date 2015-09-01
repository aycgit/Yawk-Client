package net.yawk.client.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.yawk.client.command.datatypes.*;
import net.yawk.client.utils.Chars;
import net.yawk.client.utils.ReflectionUtils;

public class CommandManager {

	public ArrayList<Command> commandsList;
	public static String PREFIX = ".";

	public BooleanDataType BOOLEAN = new BooleanDataType();
	public IntegerDataType INTEGER = new IntegerDataType();
	public StringDataType STRING = new StringDataType();
	public StringDataType MULTISPACE_STRING = new StringDataType();

	public CommandManager() {
		commandsList = new ArrayList<Command>();
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
		Map<String,Object> values = new HashMap<String,Object>();

		Command command = getCommandByName(parts[0]);

		if(command == null){
			return "Command not found. Type " + Chars.QUOTE + PREFIX + "help" + Chars.QUOTE + " for a list of all commands";
		}else{
			args = command.getArguments(this);
		}

		if(args != null){

			if(parts.length-1 < getRequiredArguments(args).size()){
				return "Not enough arguments specified!";
			}

			for(int i = 0; i < args.length; i++){

				Argument arg = args[i];

				if(i < parts.length-1){

					String input = null;

					if(arg.getType() == MULTISPACE_STRING){
						input = getStringAfterIndex(parts, i+1);
					}else{
						input = parts[i+1];
					}

					if(arg.getType().isValid(input)){
						values.put(arg.getName(), arg.getType().getValue(input));
					}else{
						return "Invalid argument type " + Chars.QUOTE + arg.getName() + Chars.QUOTE + 
								" was provided as " +Chars.QUOTE + input + Chars.QUOTE + 
								" but needs to be in " + arg.getType().getName() +" form";
					}

				}else{

					values.put(arg.getName(), arg.getType().getDefault());

				}
			}
		}

		command.runCommand(this, new Arguments(this, values));

		return null;
	}

	private String getStringAfterIndex(String[] array, int index){

		StringBuilder builder = new StringBuilder();

		for(int i = index; i < array.length; i++){
			builder.append(array[i]);
			builder.append(" ");
		}

		return builder.toString().trim();
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
}