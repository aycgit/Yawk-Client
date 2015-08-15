package net.yawk.client;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.util.logging.Logger;

import org.apache.commons.codec.binary.Base64;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.lwjgl.input.Keyboard;

import com.darkmagician6.eventapi.EventManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C11PacketEnchantItem;
import net.minecraft.network.play.client.C16PacketClientStatus;
import net.minecraft.network.play.client.C18PacketSpectate;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHealth;
import net.minecraft.util.ResourceLocation;
import net.yawk.client.api.PluginManager;
import net.yawk.client.events.EventKeyPress;
import net.yawk.client.gui.GuiClickable;
import net.yawk.client.gui.hub.GuiHub;
import net.yawk.client.hooks.EntityPlayerSPHook;
import net.yawk.client.hooks.RenderGlobalHook;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.ModManager;
import net.yawk.client.modmanager.values.ValuesRegistry;
import net.yawk.client.mods.world.HideClient;
import net.yawk.client.saving.FileManager;
import net.yawk.client.utils.ClientSession;
import net.yawk.client.utils.ClientUtils;

public class Client {
	
	private static Client client;
	
	public GuiClickable gui;
	public GuiHub hub;
	private FontRenderer fontRenderer;
	private Minecraft mc;
	private ClientSession session;
	private ModManager modManager;
	private PluginManager pluginManager;
	private FileManager fileManager;
	private ValuesRegistry valuesRegistry;
	private Logger logger;
	
	public Client(Minecraft mc){
		this.mc = mc;
	}
	
	public void init(){
		
		initHooks(this.mc);
		
		//TODO: READ USERNAME AND PASSWORD FROM FILE
		//TODO: AUTHENTICATION
		session = new ClientSession("Name", "348443568", false);
		
		logger = Logger.getGlobal();
		
		this.valuesRegistry = new ValuesRegistry();
		this.fileManager = new FileManager(this);
		fileManager.loadClientSettings();
		
		this.fontRenderer = mc.fontRendererObj;
		this.modManager = new ModManager();
		this.hub = new GuiHub(this);
		this.gui = new GuiClickable(modManager);
		this.pluginManager = new PluginManager();
		
		(new Thread(){
			
			public void run(){
				
				fileManager.loadSecondarySettings();
				
				try {
					pluginManager.load();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			
		}).start();
		
		Runtime.getRuntime().addShutdownHook(new Thread(){
			@Override
			public void run() {
				System.out.println("Yawk shutdown hook running");
				fileManager.save();
			}
		});
	}
	
	private void initHooks(Minecraft mc){
		
		mc.renderGlobal = new RenderGlobalHook(mc);
		
	}
	
	public void log(String print){
		logger.info(print);
	}
	
	public GuiHub getHub() {
		return hub;
	}
	
	public Mod getHideClientMod(){
		return Client.getClient().getModManager().getMod(HideClient.class);
	}
		
	public PluginManager getPluginManager() {
		return pluginManager;
	}
	
	public ModManager getModManager() {
		return modManager;
	}
	
	public static Client getClient() {
		return client;
	}
	
	public static void setClient(Client client) {
		Client.client = client;
	}
	
	public EntityPlayerSP getPlayer(){
		return mc.thePlayer;
	}
	
	public GuiClickable getGui() {
		return gui;
	}
	
	public FontRenderer getFontRenderer() {
		return fontRenderer;
	}
	
	public Minecraft getMinecraft() {
		return mc;
	}
	
	public ClientSession getSession() {
		return session;
	}
	
	public FileManager getFileManager() {
		return fileManager;
	}
		
	public void keyPressed(int key){
		
		if(key == Keyboard.KEY_Y){
			mc.displayGuiScreen(gui);
		}
		
		if(key == Keyboard.KEY_X){
			mc.displayGuiScreen(hub);
		}
		
		if(mc.currentScreen == null){
			for(Mod m : modManager.mods){
				if(m.getKeybind() == key){
					modManager.toggle(m);
				}
			}
			
			EventManager.call(new EventKeyPress(key));
		}
	}
	
	public static String getDir(){
		return Minecraft.getMinecraft().mcDataDir.getAbsolutePath();
	}
	
	public static String getFullDir() {
		
		try {
			File file = new File(getDir(), "XHysteria");
			if(!file.exists()){
				file.mkdirs();
			}
			return Minecraft.getMinecraft().mcDataDir.getCanonicalPath()+"/XHysteria";
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public ValuesRegistry getValuesRegistry() {
		return valuesRegistry;
	}
}
