package me.riverhouse.candy.logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Calendar;

import me.riverhouse.candy.Candy;

public class CandyLogger {

	private static ArrayList<String> candyLog = new ArrayList<String>();

	public static void addMessage(LoggerImportance importance, String message){
	
		Calendar date = Calendar.getInstance();

		String time = date.get(Calendar.MONTH) + "." + date.get(Calendar.DAY_OF_MONTH) + "." + date.get(Calendar.YEAR) + " - " + date.get(Calendar.HOUR_OF_DAY) + "." + date.get(Calendar.MINUTE) + "." + date.get(Calendar.SECOND);
		
		candyLog.add("[ CANDY ] [ " + time + " ] [ " + importance.toString() + " ] : " + message);
		System.out.println("[ CANDY ] [ " + time + " ] [ " + importance.toString() + " ] : " + message);
		writeToLogs();
	}
	
	public static void writeToLogs(){	
		BufferedWriter writer = null;
		try {
			File logFile = Candy.getCandy().getManagers().getSettingsManager().getCandyLogFile();

			writer = new BufferedWriter(new FileWriter(logFile, true));

			for(String string : candyLog){
				writer.append(string);
				writer.newLine();
			}
		} catch (Exception e) {
			//e.printStackTrace();
		} finally {
			try {
				writer.close();
				candyLog.clear();
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}

	}
	
}
