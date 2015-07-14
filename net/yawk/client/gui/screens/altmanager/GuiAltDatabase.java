package net.yawk.client.gui.screens.altmanager;
 
import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Session;
import net.yawk.client.Client;
import net.yawk.client.utils.ChatColours;
import net.yawk.client.utils.ClientUtils;
import net.yawk.client.utils.YawkWebsiteUtils;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.lwjgl.input.Keyboard;

public class GuiAltDatabase extends GuiScreen {
	
    private GuiScreen parentScreen;
    private GuiTextField usernameField;
    private AltPassField passwordField;
	private String username;
    private String password;
    
    public GuiAltDatabase(GuiScreen guiClient, String username, String password) {
        parentScreen = guiClient;
        this.username = username;
        this.password = password;
    }
    
    public FontRenderer fontRenderer = Client.getClient().getFontRenderer();
    
    public void updateScreen() {
        passwordField.updateCursorCounter();
        usernameField.updateCursorCounter();
    }
    
    public void initGui() {
    	
        Keyboard.enableRepeatEvents(true);
        buttonList.clear();
        buttonList.add(new GuiButton(2, width / 2 - 100, height / 4 + 108, ChatColours.AQUA+"Need an account? Register here"));
        buttonList.add(new GuiButton(0, width / 2 - 100, height / 4 + 132, "Get Alt Account"));
        buttonList.add(new GuiButton(1, width / 2 - 100, height / 4 + 156, "Done"));
                
        usernameField = new GuiTextField(1, Client.getClient().getFontRenderer(), width / 2 - 100, 101, 200, 20);
        passwordField = new AltPassField(parentScreen, Client.getClient().getFontRenderer(), width / 2 - 100, 141, 200, 20, "");
        
        if(username != null && username != "UNKNOWN"){
        	usernameField.setText(username);
        }
        
        if(password != null){
            passwordField.setText(password);
        }
    }
    
    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
        lastMessage = "";
        
        //Base.getSentientManager().setSentientUsername(usernameField.getText());
        //Base.getSentientManager().setSentientPassword(passwordField.getText());
        
        //Base.getSentientManager().updateFile();
    }
    
    protected void actionPerformed(GuiButton var1) {
        if (var1.enabled) {
        	if (var1.id == 0) {
        		if(!isGettingAccount){
        			getAccount();
        		}
        	}
        	
        	if (var1.id == 1) {
                mc.displayGuiScreen(parentScreen);
            }
        	
        	if (var1.id == 2) {
        		ClientUtils.openBrowserWindow("http://yawk.net/forums/ucp.php?mode=register");
        	}
        }
    }
    
    private boolean isGettingAccount;
    
    private void getAccount(){
    	(new Thread(){
			public void run(){
				
    			isGettingAccount = true;
				lastMessage = ChatColours.AQUA+"Getting account...";
				
    			try {
    				
    				Document doc = Jsoup.connect("http://yawk.net/premium/session.php?&u="+usernameField.getText()+"&p="+passwordField.getText()).userAgent(ClientUtils.USER_AGENT).get();
    				String text = doc.text();
    				
					System.out.println(text);
					
					JSONObject json = (JSONObject) JSONValue.parse(text);
					
					if(text.contains("ForbiddenOperationException")){
						isGettingAccount = false;
						lastMessage = ChatColours.RED+"Bad Account Picked - Please Retry";
						return;
					}
					
					if(json.containsKey("accessToken") && json.containsKey("selectedProfile")){	
						
						JSONObject profile = (JSONObject) json.get("selectedProfile");
						
						Client.getClient().getMinecraft().session = new Session((String)profile.get("name"), (String)profile.get("id"), (String)json.get("accessToken"), "mojang");
						
						lastMessage = ChatColours.GREEN+"Logged in!";
						
					}else if(json.containsKey("reason")){
						lastMessage = ChatColours.RED+"Failed: " + json.get("reason");
					}
					
    			} catch (Exception e) {
					e.printStackTrace();
					lastMessage = ChatColours.RED+"Error! Please retry!";
	    			isGettingAccount = false;
				}
    			
    			isGettingAccount = false;
    		}
    	}).start();
    }
    
    protected void keyTyped(char var1, int var2) {
        passwordField.textboxKeyTyped(var1, var2);
        usernameField.textboxKeyTyped(var1, var2);
    	
        if (var1 == 13)
            actionPerformed((GuiButton) buttonList.get(0));
    }

    protected void mouseClicked(int var1, int var2, int var3) throws IOException {
        super.mouseClicked(var1, var2, var3);
        usernameField.mouseClicked(var1, var2, var3);
        passwordField.mouseClicked(var1, var2, var3);
    }

    private String lastMessage = "";
    
    public void drawScreen(int var1, int var2, float var3) {
        drawDefaultBackground();
        drawCenteredString(fontRenderer, "Get a random Alt Account", width / 2, height / 4 - 40, 16777215);
        drawCenteredString(fontRenderer, "Current Username: "+mc.session.getUsername(), width / 2, height / 4 - 28, 16777215);
        
        drawCenteredString(fontRenderer, lastMessage, width / 2, 170, 16777215);
        
        drawString(fontRenderer, "Yawk Forums Username:", width / 2 - 100, 89, 10526880);
        drawString(fontRenderer, "Yawk Forums Password:", width / 2 - 100, 129, 10526880);
        passwordField.drawTextBox();
        usernameField.drawTextBox();
        super.drawScreen(var1, var2, var3);
    }
}