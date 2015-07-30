package net.yawk.client.modmanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import net.yawk.client.Client;
import net.yawk.client.api.PluginData;
import net.yawk.client.events.EventDisabled;
import net.yawk.client.events.EventEnabled;
import net.yawk.client.gui.Window;
import net.yawk.client.gui.components.buttons.ModButton;
import net.yawk.client.mods.building.*;
import net.yawk.client.mods.combat.*;
import net.yawk.client.mods.movement.*;
import net.yawk.client.mods.world.*;
import net.yawk.client.utils.ReflectionUtils;

import com.darkmagician6.eventapi.EventManager;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public class ModManager {
	
	public ArrayList<Mod> mods = new ArrayList<Mod>();
	public BiMap<String, Mod> nameMap = HashBiMap.create();
	
	public ModManager(){
		
		addClassesFromPackage("net.yawk.client.mods");
		
		for(Mod m : mods){
			initMod(m);
			EventManager.register(m);
		}
	}
	
    public void addClassesFromPackage(String packageName){
    	
        for (Class<?> clazz : ReflectionUtils.getClasses(packageName)) {
            try {
                Object obj = clazz.newInstance();
                if (obj instanceof Mod) {
                    mods.add((Mod) obj);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
	public void addPluginMod(Mod m, PluginData data){
		initMod(m);
		mods.add(m);
		EventManager.register(m);
	}
	
	private void initMod(Mod m){
		if(m.getClass().isAnnotationPresent(RegisterMod.class)){
			RegisterMod details = m.getClass().getAnnotation(RegisterMod.class);
			nameMap.put(details.name(), m);
			m.setKeybind(details.defaultKey());
			m.setName(details.name());
			m.setType(details.type());
		}else{
			Client.getClient().log("Error - RegisterMod class not found: "+m.getClass().getName());
		}
	}
	
	public void removePluginMods(PluginData data){
		
		Iterator<Mod> it = mods.iterator();
		
		while(it.hasNext()){
			
			Mod m = it.next();
			
			if(m instanceof PluginMod && ((PluginMod) m).getPluginData() == data){
				Client.getClient().getModManager().mods.remove(m);
				Client.getClient().getModManager().nameMap.remove(m.getName());
				EventManager.unregister(m);
			}
		}
	}
	
	public void toggle(Mod m){
		
		if(m.isEnabled()){
			m.setEnabled(false);
			EventManager.call(new EventDisabled(m));
			m.onDisable();
		}else{
			m.setEnabled(true);
			EventManager.call(new EventEnabled(m));
			m.onEnable();
		}
	}
	
	public Mod getMod(Class modClass){
		for(Mod m : mods){
			if(m.getClass().equals(modClass)){
				return m;
			}
		}
		
		return null;
	}
	
	public Mod getModByName(String name){
		return this.nameMap.get(name);
	}
}
