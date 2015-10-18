package me.riverhouse.candy.javascript;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Engine {

	public static void runJS(String code){
		ScriptEngineManager engineManager = 
				new ScriptEngineManager();
		ScriptEngine engine = 
				engineManager.getEngineByName("rhino");
		
		try {
			engine.eval(code);
		} catch (ScriptException e) {
			System.out.println("JavaScript code Error : Caused by, " + e.getCause() + ", at line " + e.getLineNumber());
		}
	}

}
