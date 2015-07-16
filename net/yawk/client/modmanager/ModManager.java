package net.yawk.client.modmanager;

import java.util.ArrayList;
import java.util.HashMap;

import net.yawk.client.Client;
import net.yawk.client.api.PluginData;
import net.yawk.client.events.EventDisabled;
import net.yawk.client.events.EventEnabled;
import net.yawk.client.gui.Window;
import net.yawk.client.gui.components.ModButton;
import net.yawk.client.mods.building.*;
import net.yawk.client.mods.combat.*;
import net.yawk.client.mods.movement.*;
import net.yawk.client.mods.world.*;

import com.darkmagician6.eventapi.EventManager;

public class ModManager {
	
	public ArrayList<Mod> mods = new ArrayList<Mod>();
	public HashMap<Mod, ModData> dataMap = new HashMap<Mod, ModData>();
	
	public ModManager(){
		mods.add(new Flight());
		mods.add(new Nuker());
		mods.add(new Paralyze());
		mods.add(new SpeedMine());
		mods.add(new PlayerESP());
		mods.add(new Climb());
		mods.add(new Speed());
		mods.add(new SafeWalk());
		mods.add(new FullBright());
		mods.add(new Day());
		mods.add(new Freecam());
		mods.add(new Tracers());
		mods.add(new AntiPotion());
		mods.add(new Sneak());
		mods.add(new Step());
		mods.add(new KillAura());
		mods.add(new HighJump());
		mods.add(new Glide());
		mods.add(new FastFall());
		mods.add(new Parkour());
		mods.add(new ArrowSense());
		mods.add(new Projectiles());
		mods.add(new ItemSpoof());
		mods.add(new Sprint());
		mods.add(new Zoom());
		mods.add(new Phase());
		mods.add(new AutoSoup());
		mods.add(new NoKnock());
		mods.add(new RapidFire());
		mods.add(new AutoBlock());
		mods.add(new DropAll());
		mods.add(new Retard());
		mods.add(new SafeBlocks());
		mods.add(new Backstab());
		mods.add(new HideClient());
		mods.add(new NoSwing());
		mods.add(new FakeHacker());
		mods.add(new Jesus());
		mods.add(new FastPlace());
		mods.add(new Weather());
		mods.add(new Looksie());
		mods.add(new NoFall());
		mods.add(new LightsOut());
		mods.add(new ArmorStandKick());
		mods.add(new ChestESP());
		mods.add(new NoFlinch());
		
		for(Mod m : mods){
			dataMap.put(m, new ModData(-1));
		}
	}
	
	public void addMod(Mod m){
		
		mods.add(m);
		dataMap.put(m, new ModData(-1));
		
		for(Window w : Client.getClient().getGui().windows){
			if(w.title == m.getType().getName()){
				w.components.add(new ModButton(w, m));
			}
		}
	}
	
	public void addMod(Mod m, PluginData data){
		mods.add(m);
		dataMap.put(m, new ModData(-1, data));
	}
	
	public void toggle(Mod m){
		
		ModData data = dataMap.get(m);
		
		if(!data.isEnabled){
			EventManager.register(m);
			data.isEnabled = true;
			EventManager.call(new EventEnabled(m));
			m.onEnable();
		}else{
			data.isEnabled = false;
			EventManager.unregister(m);
			EventManager.call(new EventDisabled(m));
			m.onDisable();
		}
	}
	
	public Mod getMod(Class clazz){
		for(Mod m : mods){
			if(m.getClass().equals(clazz)){
				return m;
			}
		}
		
		return null;
	}
	
	public Mod getModByName(String name){
		for(Mod m : mods){
			if(m.getName().equals(name)){
				return m;
			}
		}
		
		return null;
	}
}
