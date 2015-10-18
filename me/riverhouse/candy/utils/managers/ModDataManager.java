package me.riverhouse.candy.utils.managers;

import java.util.ArrayList;

import me.riverhouse.candy.module.ModData;
import me.riverhouse.candy.module.Module;

public class ModDataManager {

	private ArrayList<ModData> data = new ArrayList<ModData>();

	public boolean hasMod(Module m){
		for(ModData d : data){
			if(m.getName() == d.getName()) return true;
		}
		
		return false;
	}

	public ArrayList<ModData> getData() {
		return data;
	}
	
	public boolean hasData(ModData md){
		for(ModData d : data){
			if(d.getName().equalsIgnoreCase(md.getName())) return true;
		}
		
		return false;
	}
	
}
