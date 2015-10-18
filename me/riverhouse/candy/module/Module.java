package me.riverhouse.candy.module;

import me.riverhouse.candy.Candy;
import me.riverhouse.candy.api.entity.Player;
import me.riverhouse.candy.event.EventSystem;
import me.riverhouse.candy.utils.Wrapper;
import me.riverhouse.candy.utils.bind.KeyBind;
import net.minecraft.client.Minecraft;

public class Module {

	private ModData data;
	private ModType type;
	
	public Minecraft mc;
	public Player p;
	
	private	String displayName;
	private boolean doesUseCommands = false;
	private String[] args;

	public Module(ModData data, ModType type){
		this.data = data;
		this.displayName = data.getName();
		this.type = type;
	}

	public String getDisplayName(){
		return this.displayName;
	}
	
	public void setDisplayName(String displayName){
		this.displayName = displayName;
	}

	public void error(String error){
		Wrapper.getPlayer().addChatMessage("&*" + this.data.getName() + " &7: &c" + error);
	}

	public void addChat(String msg){
		Wrapper.getPlayer().addChatMessage("&*" + this.data.getName() + " &7: " + msg);
	}

	public void setArgs(String[] args){
		this.args = args;
	}

	public String[] getArgs(){
		return this.args;
	}

	public String getName(){
		return this.data.getName();
	}

	public KeyBind getBind(){
		return this.data.getBind();
	}

	public void setModData(ModData data){
		this.data = data;
	}
	
	public ModData getModData(){
		return this.data;
	}
	
	public ModType getType(){
		return this.type;
	}

	public void onEnable(){
	}

	public void onDisable(){
	}

	public void onToggle(){
	}
	
	public boolean getState(){
		return this.data.getState();
	}

	public void setState(boolean state){		
		this.data.setState(state);
		
		if(state){
			EventSystem.register(this);
		}else{
			EventSystem.unregister(this);
		}
		
		onToggle();
		Candy.getCandy().getManagers().getSettingsManager().saveMods();
		if(state){
			onEnable();
		}else{
			onDisable();
		}
	}

	public void toggle(){
		setState(!this.getState());
	}

	public void setBind(int bind){
		this.data.setBind(new KeyBind(this.getName(), bind));
	}

	public boolean isCategory(ModType type){
		if(this.type == type){
			return true;
		}
		return false;
	}
	
}
