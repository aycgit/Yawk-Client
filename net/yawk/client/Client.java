package net.yawk.client;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ChatComponentTranslation;
import net.yawk.client.api.PluginManager;
import net.yawk.client.cameras.Camera;
import net.yawk.client.command.CommandManager;
import net.yawk.client.events.EventKeyPress;
import net.yawk.client.friends.FriendManager;
import net.yawk.client.gui.GuiClickable;
import net.yawk.client.gui.hub.GuiHub;
import net.yawk.client.hooks.EntityRendererHook;
import net.yawk.client.hooks.ItemRendererHook;
import net.yawk.client.hooks.RenderGlobalHook;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.ModManager;
import net.yawk.client.modmanager.values.ValuesRegistry;
import net.yawk.client.mods.combat.NoFlinch;
import net.yawk.client.mods.world.HideClient;
import net.yawk.client.mods.world.Perception;
import net.yawk.client.saving.FileManager;
import net.yawk.client.utils.ClientSession;

import org.lwjgl.input.Keyboard;

import com.darkmagician6.eventapi.EventManager;

public class Client {
	
	public static String Version = "v2.5";
	
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
	private List<Camera> cameras;
	private CommandManager commandManager;
	private FriendManager friendManager;
	
	public Client(Minecraft mc){
		this.mc = mc;
		cameras = new ArrayList<Camera>();
	}
	
	public void init(){
		
		logger = Logger.getGlobal();
		
		RenderGlobalHook renderGlobalHook = new RenderGlobalHook(mc);
		mc.renderGlobal = renderGlobalHook;
		
		//This NEEDS to go before entityRenderer because entityRenderer caches the value of Minecraft.getItemRenderer
		mc.itemRenderer = new ItemRendererHook(mc);
		
		EntityRendererHook entityRendererHook = new EntityRendererHook(mc, mc.getResourceManager());
		mc.entityRenderer = entityRendererHook;
		
		authenticateUser();
		
		loadPrimaryFiles();
		
		createClient();
		
		loadFiles();
		
		addShutdownHook();
		
		entityRendererHook.noFlinch = modManager.getMod(NoFlinch.class);
		renderGlobalHook.perception = (Perception)Client.getClient().getModManager().getMod(Perception.class);
	}
	
	private void authenticateUser(){
		
		//TODO: READ USERNAME AND PASSWORD FROM FILE
		//TODO: AUTHENTICATION
		session = new ClientSession("Name", "348443568", false);
	}
	
	private void loadPrimaryFiles(){
		
		valuesRegistry = new ValuesRegistry();
		fileManager = new FileManager(this);
		fileManager.loadClientSettings();
	}
	
	private void createClient(){
		
		fontRenderer = mc.fontRendererObj;
		modManager = new ModManager();
		hub = new GuiHub(this);
		gui = new GuiClickable(modManager);
		pluginManager = new PluginManager();
		commandManager = new CommandManager();
		friendManager = new FriendManager();
	}
	
	private void addShutdownHook(){
		
		Runtime.getRuntime().addShutdownHook(new Thread(){
			@Override
			public void run() {
				System.out.println("Yawk shutdown hook running");
				fileManager.save();
			}
		});
	}
	
	private void loadFiles(){
		
		(new Thread(){
			
			public void run(){
				
				fileManager.loadSecondarySettings();
				
				try {
					pluginManager.load();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}).start();
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
	
	public FriendManager getFriendManager() {
		return friendManager;
	}
	
	public void addChat(String text){
		Client.getClient().getPlayer().addChatComponentMessage(new ChatComponentTranslation("[Yawk] " + text));
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
	
	public List<Camera> getCameras() {
		return cameras;
	}
	
	public CommandManager getCommandManager() {
		return commandManager;
	}
	
	public void registerCamera(Camera camera){
		cameras.add(camera);
	}
}
