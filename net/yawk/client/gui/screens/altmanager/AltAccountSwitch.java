package net.yawk.client.gui.screens.altmanager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import javax.swing.JFileChooser;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.*;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Session;
import net.yawk.client.Client;
import net.yawk.client.utils.yggdrasil.YggdrasilAuthenticator;

public class AltAccountSwitch extends GuiScreen {

	private GuiScreen parentScreen;
	private AltAccountSlot accountSlotContainer;
	private GuiButton buttonEdit;
	private GuiButton buttonSelect;
	private GuiButton buttonDelete;
	private List<String> accountList = new ArrayList<String>();
	private List<String> currentAccountList = accountList;
	private String prevSearchTerm = "";
	private int selectedAccount = 0;
	private File altsFile = new File(Client.getClient().getFullDir(), "alts");
    public GuiTextField searchField;
    private boolean isOpen;
    
	public AltAccountSwitch(GuiScreen gui) {
		parentScreen = gui;
	}
	
	public void initGui() {
        Keyboard.enableRepeatEvents(true);
		loadAccountList();
		accountSlotContainer = new AltAccountSlot(this);
		buttonList.clear();
		buttonList.add(new GuiButton(0, width / 2 + 4 + 76, height - 28, 75,
				20, "Back"));
		buttonList.add(buttonSelect = new GuiButton(1, width / 2 - 154,
				height - 52, 100, 20, "Login"));
		buttonList.add(buttonDelete = new GuiButton(2, width / 2 - 74,
				height - 28, 70, 20, "Delete"));
		buttonList.add(new GuiButton(3, width / 2 + 4 + 50, height - 52, 100,
				20, "Add"));
		buttonList.add(new GuiButton(4, width / 2 - 50, height - 52, 100, 20,
				"Direct Login"));
		buttonList.add(buttonEdit = new GuiButton(5, width / 2 - 154,
				height - 28, 70, 20, "Edit"));
		buttonList.add(new GuiButton(6, width / 2 + 4, height - 28, 70, 20,
				"From File"));
		
		searchField = new GuiTextField(1, Client.getClient().getFontRenderer(), 5, height - 28, 75, 20);
		
		buttonList.add(new GuiButton(7, width - 75, height - 28, 70, 20,
				EnumChatFormatting.GREEN+"Alt Database"));
		
		boolean flag = selectedAccount >= 0
				&& selectedAccount < accountSlotContainer.getSize();
		buttonSelect.enabled = flag;
		buttonEdit.enabled = flag;
		buttonDelete.enabled = flag;
		
		isOpen = true;
	}

	public void drawScreen(int i, int j, float f) {
		drawDefaultBackground();
		accountSlotContainer.drawScreen(i, j, f);
		drawCenteredString(Client.getClient().getFontRenderer(), "Account Manager", width / 2, 20,
				0xffffff);
		mc.fontRendererObj.drawString("Current Account: " + mc.session.getUsername(), 4, 4, 0xffffff);
		
		mc.fontRendererObj.drawString("Search:",  5, height - 45, 0xffffff);
		searchField.drawTextBox();
		
		if(searchField.getText() != prevSearchTerm){
			currentAccountList = getSearchedList(searchField.getText());
			prevSearchTerm = searchField.getText();
		}
		
		super.drawScreen(i, j, f);
        
		/*
		GL11.glScalef(1F, 0.5F, 0.5F);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 255.0F);
		
		GuiUtils.downloadSkin(mc.session.getUsername());
		GuiUtils.drawDefaultTexturedModalRect(width - (2*(width/20)), 0, 32, 64, 32, 64);
		GL11.glScalef(1, 2, 2);
		*/
	}
	
	private List<String> getSearchedList(String search){
		List<String> accounts = new ArrayList<String>();
		
		for(String account : accountList){
			if(account.split(":")[0].toLowerCase().contains(search.toLowerCase())){
				accounts.add(account);
			}
		}
		
		return accounts;
	}
	
	@Override
	public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
        isOpen = false;
	}
	
	protected void actionPerformed(GuiButton guibutton) {
		if (guibutton.enabled) {
			if (guibutton.id == 0) {
				mc.displayGuiScreen(parentScreen);
			}
			if (guibutton.id == 1) {
				login(selectedAccount);
			}
			if (guibutton.id == 2) {
				String name = accountList.get(selectedAccount).split(":")[0];
				String s1 = "Are you sure you want to remove this Account?";
				String s2 = "";
				mc.displayGuiScreen(new GuiYesNo(new GuiYesNoCallback(){

					@Override
					public void confirmClicked(boolean delete,
							int index) {
						if(delete){
							getAltManager().accountList.remove(selectedAccount);
							getAltManager().saveAccountList();
						}
						mc.displayGuiScreen(getAltManager());
					}
					
				}, s1, s2, selectedAccount));
			}
			if (guibutton.id == 3) {
				mc.displayGuiScreen(new AltAccountEdit(this, -1));
			}
			if (guibutton.id == 4) {
				mc.displayGuiScreen(new GuiLogin(this));
			}
			if (guibutton.id == 5) {
				mc.displayGuiScreen(new AltAccountEdit(this, selectedAccount));
			}
			if (guibutton.id == 6) {
				JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(fc);
				if (returnVal == 0) {
					StringBuilder data = new StringBuilder();
					try{
					for (String s : readFile(fc.getSelectedFile().getAbsolutePath())){
						data.append(s + "\n");
					BufferedWriter writer = new BufferedWriter(new FileWriter(altsFile));
					writer.write(data.toString().trim());
					writer.close();
					loadAccountList();
					}
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
			
			if (guibutton.id == 7) {
				mc.displayGuiScreen(new GuiAltDatabase(this, "", ""));
			}
		}
	}
	
	protected AltAccountSwitch getAltManager(){
		return this;
	}
	
	@Override
	protected void keyTyped(char par1, int par2) {
		searchField.textboxKeyTyped(par1, par2);
	}
	
	@Override
	protected void mouseClicked(int par1, int par2, int par3) throws IOException {
		searchField.mouseClicked(par1, par2, par3);
		super.mouseClicked(par1, par2, par3);
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
	
	public void login(int i) {
		String[] info = currentAccountList.get(i).split(":");
		
		if(info.length == 1){
			mc.session = new Session(info[0], "123", "123", "mojang");
			mc.displayGuiScreen(parentScreen);
			return;
		}
		
		YggdrasilAuthenticator auth = new YggdrasilAuthenticator(info[0],info[1]);
		if(auth.login()){
			Client.getClient().getMinecraft().session = auth.getSession();
			mc.displayGuiScreen(parentScreen);
		}
	}

	public void confirmClicked(boolean flag, int i) {
		if (flag && i >= 0) {
			accountList.remove(i);
			currentAccountList = getSearchedList(searchField.getText());
			saveAccountList();
		}
		mc.displayGuiScreen(this);
	}

	private void loadAccountList() {
		try {
			if (!altsFile.exists())
				altsFile.createNewFile();
			accountList.clear();
			Scanner scanner = new Scanner(new FileReader(altsFile));
			scanner.useDelimiter("\n");
			while (scanner.hasNext())
				accountList.add(scanner.next().trim());
			scanner.close();
		} catch (Exception e) {
		}
	}

	private void saveAccountList() {
		try {
			StringBuilder data = new StringBuilder();
			for (String s : accountList)
				data.append(s + "\n");
			BufferedWriter writer = new BufferedWriter(new FileWriter(altsFile));
			writer.write(data.toString());
			writer.close();
		} catch (Exception e) {
		}
	}

	public void editAccount(String account, int i) {
		if (i < 0)
			accountList.add(account);
		else
			accountList.set(i, account);
		currentAccountList = getSearchedList(searchField.getText());
		saveAccountList();
	}

	public List<String> getAccountList() {
		return currentAccountList;
	}

	public int setSelectedAccount(int var0) {
		return selectedAccount = var0;
	}

	public int getSelectedAccount() {
		return selectedAccount;
	}

	public GuiButton getButtonSelect() {
		return buttonSelect;
	}

	public GuiButton getButtonEdit() {
		return buttonEdit;
	}

	public GuiButton getButtonDelete() {
		return buttonDelete;
	}

	public String Login(String username, String password) {
		String resultText = "";
		String loginpage = "http://login.minecraft.net/?user=" + username
				+ "&password=" + password + "&version=13";

		try {
			BufferedReader pageReader = read(loginpage);
			for (String s = ""; (s = pageReader.readLine()) != null;) {
				resultText = s;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultText;
	}

	/** Reads a stream from an URL */
	private BufferedReader read(String url) throws Exception,
			FileNotFoundException {
		return new BufferedReader(new InputStreamReader(
				new URL(url).openStream()));
	}
}