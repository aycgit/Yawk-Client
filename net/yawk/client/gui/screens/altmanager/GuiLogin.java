package net.yawk.client.gui.screens.altmanager;
 
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import net.minecraft.client.gui.*;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Session;
import net.yawk.client.Client;
import net.yawk.client.utils.yggdrasil.YggdrasilAuthenticator;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

 
public class GuiLogin extends GuiScreen {
 
        private GuiScreen parentScreen;
        private GuiTextField usernameTextField;
        private GuiTextField passwordTextField;
        private String error;
 
    	public FontRenderer fontRenderer = Client.getClient().getFontRenderer();
        
        public GuiLogin(GuiScreen guiscreen) {
                parentScreen = guiscreen;
        }
 
        public void updateScreen() {
                usernameTextField.updateCursorCounter();
                passwordTextField.updateCursorCounter();
        }
 
        public void onGuiClosed() {
                Keyboard.enableRepeatEvents(false);
        }
 
        protected void actionPerformed(GuiButton guibutton) {
                if (!guibutton.enabled) {
                        return;
                }
                if (guibutton.id == 1) {
                        mc.displayGuiScreen(parentScreen);
                }
                else if (guibutton.id == 0) {
                        if (passwordTextField.getText().length() > 0) {
                                String s = usernameTextField.getText();
                                String s1 = passwordTextField.getText();
                                if(s1.length() > 0){
                                	login(s+":"+s1);
                                }else{
                                    login(s);
                                }
                                mc.displayGuiScreen(parentScreen);
                        }else {
                                mc.session = new Session(usernameTextField.getText(), "", "", "mojang");
                        }
 
                        mc.displayGuiScreen(parentScreen);
                }
        }
 
        protected void keyTyped(char c, int i) {
                usernameTextField.textboxKeyTyped(c, i);
                passwordTextField.textboxKeyTyped(c, i);
                if (c == '\r') {
                        actionPerformed((GuiButton) buttonList.get(0));
                }
        }
 
        protected void mouseClicked(int i, int j, int k) throws IOException {
                super.mouseClicked(i, j, k);
                usernameTextField.mouseClicked(i, j, k);
                passwordTextField.mouseClicked(i, j, k);
        }
 
        public void initGui() {
                Keyboard.enableRepeatEvents(true);
                buttonList.clear();
                buttonList.add(new GuiButton(0, width / 2 - 100, height / 4 + 96 + 12, "Done"));
                buttonList.add(new GuiButton(1, width / 2 - 100, height / 4 + 120 + 12, "Cancel"));
                usernameTextField = new GuiTextField(1,fontRenderer, width / 2 - 100, 76, 200, 20);
                passwordTextField = new GuiTextField(2, fontRenderer, width / 2 - 100, 116, 200, 20);
        }
 
        public void drawScreen(int i, int j, float f) {
                drawDefaultBackground();
                drawString(fontRenderer, EnumChatFormatting.AQUA+"Username:", width / 2 - 100, 63, 0xa0a0a0);
                drawString(fontRenderer, EnumChatFormatting.AQUA+"Password (Optional):", width / 2 - 100, 104, 0xa0a0a0);
                usernameTextField.drawTextBox();
                passwordTextField.drawTextBox();
                if (error != null) {
                        drawCenteredString(fontRenderer, (new StringBuilder(EnumChatFormatting.AQUA+"Login Failed:")).append(error).toString(), width / 2, height / 4 + 72 + 12, 0xffffff);
                }
                super.drawScreen(i, j, f);
        }
 
    	public void login(String userpass) {
    		String[] info = userpass.split(":");
    		YggdrasilAuthenticator auth = new YggdrasilAuthenticator(info[0],info[1]);
    		if(auth.login()){
    			Client.getClient().getMinecraft().session = auth.getSession();
    			mc.displayGuiScreen(parentScreen);
    		}
    	}
 
        /** Reads a stream from an URL */
        private BufferedReader read(String url) throws Exception, FileNotFoundException{
                return new BufferedReader(new InputStreamReader(new URL(url).openStream()));
        }
 
}
