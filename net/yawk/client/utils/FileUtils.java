package net.yawk.client.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtils {
	
	public static void writeFile(File file, String text){
		try {
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(text);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String[] readFile(String s){
		try {
				String sCurrentLine;
				BufferedReader br = new BufferedReader(new FileReader(s));
				String list = "";
				while ((sCurrentLine = br.readLine()) != null) {
					list = list+","+(sCurrentLine);
				}
				
				br.close();
				
				return list.split(",");
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		return null;
	}
	
	public static String[] readFile(File file){
		try {
				String sCurrentLine;
				BufferedReader br = new BufferedReader(new FileReader(file));
				String list = "";
				while ((sCurrentLine = br.readLine()) != null) {
					list = list+","+(sCurrentLine);
				}
				
				br.close();
				
				return list.split(",");
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		return null;
	}
	
	public static String readFileFull(File file){
		try {
				String sCurrentLine;
				BufferedReader br = new BufferedReader(new FileReader(file));
				String list = "";
				while ((sCurrentLine = br.readLine()) != null) {
					list = list+"\r\n"+(sCurrentLine);
				}
				
				br.close();
				
				return list;
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		return null;
	}
}
