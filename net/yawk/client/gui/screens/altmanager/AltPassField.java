package net.yawk.client.gui.screens.altmanager;
 
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.util.ResourceLocation;
import net.yawk.client.utils.ClientUtils;

public class AltPassField extends Gui {
    private final FontRenderer fontRenderer;
    private final int xPos;
    private final int yPos;
    private final int width;
    private final int height;
    private String text;
    private int maxStringLength;
    private int cursorCounter;
    public boolean isFocused = false;
    public boolean isEnabled = true;
    private GuiScreen parentGuiScreen;

    public AltPassField(GuiScreen var1, FontRenderer var2, int var3, int var4, int var5, int var6, String var7) {
        parentGuiScreen = var1;
        fontRenderer = var2;
        xPos = var3;
        yPos = var4;
        width = var5;
        height = var6;
        setText(var7);
    }

    public void setText(String var1) {
        text = var1;
    }

    public String getText() {
        return text;
    }

    public void updateCursorCounter() {
        cursorCounter++;
    }

    public void textboxKeyTyped(char var1, int var2) {
        if (isEnabled && isFocused) {
            if (var1 == 9)
               parentGuiScreen.confirmClicked(isEnabled, var2);//confirmClicked(isEnabled, var2);
            if (var1 == 22) {
                String var3 = GuiScreen.getClipboardString();
                if (var3 == null)
                    var3 = "";
                int var4 = 32 - text.length();
                if (var4 > var3.length())
                    var4 = var3.length();
                if (var4 > 0)
                    text = text + var3.substring(0, var4);
            }
            
            if (var2 == 14 && text.length() > 0){
                text = text.substring(0, text.length() - 1);
            }
            
            if ( ( ( ClientUtils.allowedCharacters().indexOf( var1 ) >= 0 ) || ( var1 > ' ' ) ) 
            		&& ( text.length( ) < 35 ) && ( var1 != "`".charAt( 0 ) ) ){
            	text += var1;
            }
        }
    }

    public void mouseClicked(int var1, int var2, int var3) {
        boolean var4 = isEnabled && var1 >= xPos && var1 < xPos + width && var2 >= yPos && var2 < yPos + height;
        setFocused(var4);
    }

    public void setFocused(boolean var1) {
        if (var1 && !isFocused)
            cursorCounter = 0;
        isFocused = var1;
    }

    public void drawTextBox() {
        drawRect(xPos - 1, yPos - 1, xPos + width + 1, yPos + height + 1, -6250336);
        drawRect(xPos, yPos, xPos + width, yPos + height, -16777216);
        String s = text.replaceAll(".", "*");
        if (isEnabled) {
            boolean var1 = isFocused && cursorCounter / 6 % 2 == 0;
            drawString(fontRenderer, s + (var1 ? "_" : ""), xPos + 4, yPos + (height - 8) / 2, 14737632);
        } else {
            drawString(fontRenderer, s, xPos + 4, yPos + (height - 8) / 2, 7368816);
        }
    }

    public void setMaxStringLength(int var1) {
        maxStringLength = var1;
    }
}
