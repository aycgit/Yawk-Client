package net.yawk.client.command;

import java.util.ArrayList;

import net.yawk.client.command.datatypes.IntegerDataType;
import net.yawk.client.utils.ReflectionUtils;

public class CommandManager {
	
	public static ArrayList<Command> command = new ArrayList<Command>();
	public static String preifx = ".";
	
	public IntegerDataType INTEGER = new IntegerDataType();
	public IntegerDataType STRING = new IntegerDataType();
	
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
	
	public void run(String cmd){
		
		String[] parts = cmd.split(" ");
		Argument[] args = null;
		Command command;
		
		for(int i = 0; i < parts.length; i++){
			
			if(i == 0){
				
				command = getCommandByName(parts[0]);
				
				if(command == null){
					
				}else{
					args = command.getArguments(null);
				}
				
				continue;
			}
			
			Argument arg = args[i];
			String input = parts[i];
			
			if(!arg.getType().isValid(input)){
				
			}
		}
	}
	
	private Command getCommandByName(String name){
		
		for(Command cmd : command){
			if(cmd.getCallName().equalsIgnoreCase(name)){
				return cmd;
			}
		}
		
		return null;
	}
	
	public static ArrayList<Command> getCommands() {
		return command;
	}
}