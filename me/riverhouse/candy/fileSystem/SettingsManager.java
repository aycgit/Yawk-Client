package me.riverhouse.candy.fileSystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import me.riverhouse.candy.Candy;
import me.riverhouse.candy.event.EventSystem;
import me.riverhouse.candy.gui.screen.clickGui.FrameInfo;
import me.riverhouse.candy.gui.screen.clickGui.FrameInfoManager;
import me.riverhouse.candy.gui.screen.clickGui.parts.Frame;
import me.riverhouse.candy.module.ModData;
import me.riverhouse.candy.module.Module;
import me.riverhouse.candy.utils.Wrapper;
import me.riverhouse.candy.utils.managers.ModDataManager;
import net.minecraft.src.Config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SettingsManager {

	public File CandyDir;

	private File CandyFriends;
	private File CandyBinds;
	private File CandyFrames;
	private File CandyPluginsFolder;

	private File CandyLogDir;
	private File CandyLogFile;

	public SettingsManager(String clientName){
		File p_i46326_2_ = Wrapper.getMinecraft().mcDataDir;

		this.CandyDir = new File(p_i46326_2_, "/" + clientName);

		this.CandyBinds = new File(p_i46326_2_, clientName + "/modules.json");
		this.CandyFriends = new File(p_i46326_2_, clientName + "/friends.json");
		this.CandyFrames = new File(p_i46326_2_, clientName + "/frames.json");

		this.CandyLogDir = new File(CandyDir, "/Logs");

		Calendar date = Calendar.getInstance();

		String time = date.get(Calendar.MONTH) + "." + date.get(Calendar.DAY_OF_MONTH) + "." + date.get(Calendar.YEAR) + " - " + date.get(Calendar.HOUR_OF_DAY) + "." + date.get(Calendar.MINUTE) + "." + date.get(Calendar.SECOND);

		this.CandyLogFile = new File(CandyLogDir, time + ".txt");
		this.CandyPluginsFolder = new File(CandyDir, "/plugins");

		checkDir(this.CandyDir);
		checkDir(this.CandyLogDir);
		checkDir(this.CandyPluginsFolder);

		checkFile(this.CandyLogFile);
		checkFile(this.CandyFriends);
		checkFile(this.CandyBinds);
		checkFile(this.CandyFrames);
	}

	public void checkDir(File dir){
		if(!dir.exists()){
			try{
				dir.mkdir();
			}catch(SecurityException se){

			}
		}
	}
	
	public void checkFile(File f){
		if(!f.exists()){
			try{
				f.createNewFile();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}

	public void loadSettings(){
		this.loadMods();
		this.loadFrames();
	}

	public void loadMods(){
		try{
			File exception = this.CandyBinds;

			if(!exception.exists()){
				this.CandyBinds.createNewFile();
				exception = this.CandyBinds;
				return;
			}

			BufferedReader bufferedreader = new BufferedReader(new FileReader(exception));

			ModDataManager manager = new GsonBuilder().setPrettyPrinting().create().fromJson(bufferedreader, ModDataManager.class);

			if(manager == null)
				return;

			Candy.getCandy().getManagers().setModDataManager(manager);

			for(ModData d : manager.getData()){
				if(Candy.getCandy().getManagers().getModuleManager() == null)
					break;

				Module m = Candy.getCandy().getManagers().getModuleManager().getModuleFromName(d.getName());

				if(m != null){	
					m.setState(d.getState());
					m.setModData(d);
					
//					if(m.getState()){
//            			EventSystem.register(m);
//            		}else{
//            			EventSystem.unregister(m);
//            		}
				}
			}

			bufferedreader.close();
		}catch(Exception var6){

		}
	}

	public void loadFrames(){
		try{
			File exception = this.CandyFrames;

			if(!exception.exists()){
				this.CandyBinds.createNewFile();
				exception = this.CandyBinds;
				return;
			}

			BufferedReader bufferedreader = new BufferedReader(new FileReader(exception));

			FrameInfoManager manager = new GsonBuilder().setPrettyPrinting().create().fromJson(bufferedreader, FrameInfoManager.class);

			if(manager == null)
				return;

			Candy.getCandy().getManagers().getFrameInfoManager().getInfo().clear();

			for(FrameInfo info : manager.getInfo()){
				Candy.getCandy().getManagers().getFrameInfoManager().getInfo().add(info);
			}

			bufferedreader.close();
		}catch(Exception var6){

		}
	}

	public void saveSettings(){
		saveMods();
	}

	public void saveMods(){
		try{
			PrintWriter exception = new PrintWriter(new FileWriter(this.CandyBinds));

			if(Candy.getCandy().getManagers().getModDataManager() == null) Candy.getCandy().getManagers().setModDataManager(new ModDataManager());

			Gson gson = new GsonBuilder().setPrettyPrinting().create();

			for(Module m : Candy.getCandy().getManagers().getModuleManager().getModules()){
				if(!Candy.getCandy().getManagers().getModDataManager().hasData(m.getModData()))
					Candy.getCandy().getManagers().getModDataManager().getData().add(m.getModData());
			}

			String json = gson.toJson(Candy.getCandy().getManagers().getModDataManager());

			exception.println(json);

			exception.close();
		}catch(Exception var2){
			Config.warn("Failed to save options");
			var2.printStackTrace();
		}
	}

	public void saveFrames(){
		if( Candy.getCandy().getClickGui() == null) return;

		try{
			PrintWriter exception = new PrintWriter(new FileWriter(this.CandyFrames));

			Gson gson = new GsonBuilder().setPrettyPrinting().create();

			Candy.getCandy().getManagers().getFrameInfoManager().getInfo().clear();

			for(Frame f : Candy.getCandy().getClickGui().frames) Candy.getCandy().getManagers().getFrameInfoManager().getInfo().add(f.toFrameInfo());

			String json = gson.toJson(Candy.getCandy().getManagers().getFrameInfoManager());

			exception.println(json);

			exception.close();
		}catch(Exception var2){
			Config.warn("Failed to save options");
			var2.printStackTrace();
		}
	}

	public File getCandyFriends() {
		return CandyFriends;
	}

	public File getCandyBinds() {
		return CandyBinds;
	}

	public File getCandyFrames() {
		return CandyFrames;
	}

	public File getCandyPluginsFolder() {
		return CandyPluginsFolder;
	}

	public File getCandyLogFile() {
		return CandyLogFile;
	}

}
