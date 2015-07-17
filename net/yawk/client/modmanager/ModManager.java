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
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public class ModManager {
	
	public ArrayList<Mod> mods = new ArrayList<Mod>();
	public BiMap<String, Mod> nameMap = HashBiMap.create();
	
	public ModManager(){
		
		//TODO: Maybe scan the mod packages or something to get rid of this massive list
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
		mods.add(new Zoom()); //TODO: move to meta
		mods.add(new Phase());
		mods.add(new AutoSoup());
		mods.add(new NoKnock());
		mods.add(new RapidFire());
		mods.add(new AutoBlock());
		mods.add(new DropAll());
		mods.add(new Derp());
		mods.add(new SafeBlocks());
		mods.add(new Backstab());
		mods.add(new HideClient()); //TODO: move to meta
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
		mods.add(new Build());
		mods.add(new Triggerbot());
		//mods.add(new Description()); //TODO: make this and move it to meta
		
		for(Mod m : mods){
			ModDetails details = m.getClass().getAnnotation(ModDetails.class);
			nameMap.put(details.name(), m);
			m.setKeybind(details.defaultKey());
			EventManager.register(m);
		}
	}
	
	public void addMod(Mod m){
		
		mods.add(m);
		nameMap.put(m.getClass().getAnnotation(ModDetails.class).name(), m);
		EventManager.register(m);
		
		String type = getModType(m).getName();
		
		for(Window w : Client.getClient().getGui().windows){
			if(w.title == type){
				w.components.add(new ModButton(w, m));
			}
		}
	}
	
	public void addMod(Mod m, PluginData data){
		mods.add(m);
		nameMap.put(m.getClass().getAnnotation(ModDetails.class).name(), m);
		EventManager.register(m);
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
	
	public Mod.Type getModType(Mod m){
		//System.out.println("CHECKING TYPE: "+m.getClass().getName());
		return m.getClass().getAnnotation(ModDetails.class).type();
	}
	
	public String getModName(Mod m){
		//System.out.println("CHECKING NAME: "+m.getClass().getName());
		return this.nameMap.inverse().get(m);
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
			if(getModName(m).equals(name)){
				return m;
			}
		}
		
		return null;
	}
}
