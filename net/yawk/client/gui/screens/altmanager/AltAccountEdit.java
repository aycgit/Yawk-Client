package net.yawk.client.gui.screens.altmanager;

import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.util.ResourceLocation;
import net.yawk.client.Client;

import org.lwjgl.input.Keyboard;

public class AltAccountEdit extends GuiScreen {
    private AltAccountSwitch parentScreen;
    private GuiTextField usernameField;
    private GuiTextField passwordField;
    private int account;

    public AltAccountEdit(AltAccountSwitch var1, int var2) {
        parentScreen = var1;
        account = var2;
    }
    
    public FontRenderer fontRenderer = Client.getClient().getFontRenderer();

    public void updateScreen() {
        passwordField.updateCursorCounter();
        usernameField.updateCursorCounter();
    }

    public void initGui() {
        Keyboard.enableRepeatEvents(true);
        buttonList.clear();
        buttonList.add(new GuiButton(0, width / 2 - 100, height / 4 + 108, "Done"));
        buttonList.add(new GuiButton(1, width / 2 - 100, height / 4 + 132, "Cancel"));
        String user = "";
        String pass = "";
        if (account >= 0) {
            String[] info = parentScreen.getAccountList().get(account).split(":");
            user = info[0];
            if (info.length > 1)
                pass = info[1];
        }
        usernameField = new GuiTextField(1,Client.getClient().getFontRenderer(), width / 2 - 100, 76, 200, 20);
        passwordField = new GuiTextField(2, Client.getClient().getFontRenderer(), width / 2 - 100, 116, 200, 20);
    }

    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
    }

    protected void actionPerformed(GuiButton var1) {
        if (var1.enabled) {
            if (var1.id == 0) {
                String s = usernameField.getText();
                if (passwordField.getText().length() > 0)
                    s += ":" + passwordField.getText();
                parentScreen.editAccount(s, account);
                mc.displayGuiScreen(parentScreen);
            }
            if (var1.id == 1) {
                mc.displayGuiScreen(parentScreen);
            }
        }
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

    public void drawScreen(int var1, int var2, float var3) {
        drawDefaultBackground();
        drawCenteredString(fontRenderer, "Edit Account Info", width / 2, height / 4 - 40, 16777215);
        drawString(fontRenderer, "Username:", width / 2 - 100, 63, 10526880);
        drawString(fontRenderer, "Password (optional):", width / 2 - 100, 104, 10526880);
        passwordField.drawTextBox();
        usernameField.drawTextBox();
        super.drawScreen(var1, var2, var3);
    }
}