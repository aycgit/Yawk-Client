package net.yawk.client.utils;

import java.text.DecimalFormat;
import java.util.Random;

import net.minecraft.network.Packet;
import net.minecraft.util.ChatAllowedCharacters;
import net.yawk.client.Client;

public class ClientUtils {
	
	public static Random random = new Random();
	public static String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36";
	public static DecimalFormat decimalFormat = new DecimalFormat("#0.00");
	
	public static void sendPacket(Packet p)
	{
		Client.getClient().getPlayer().sendQueue.addToSendQueue(p);
	}
	
	private static String allowedCharacters;
	
	static
	{
		allowedCharacters = ChatAllowedCharacters.allowedCharactersArray.toString()+" ";
	}
	
	public static String allowedCharacters(){
		return allowedCharacters;
	}
	
	public static void openBrowserWindow(String url)
	{
		String os = System.getProperty("os.name").toLowerCase();
		Runtime rt = Runtime.getRuntime();

		try{

			if (os.indexOf( "win" ) >= 0) {

				// this doesn't support showing urls in the form of "page.html#nameLink" 
				rt.exec( "rundll32 url.dll,FileProtocolHandler " + url);

			} else if (os.indexOf( "mac" ) >= 0) {

				rt.exec( "open " + url);

			} else if (os.indexOf( "nix") >=0 || os.indexOf( "nux") >=0) {

				// Do a best guess on unix until we get a platform independent way
				// Build a list of browsers to try, in this order.
				String[] browsers = {"epiphany", "firefox", "mozilla", "konqueror",
						"netscape","opera","links","lynx"};

				// Build a command string which looks like "browser1 "url" || browser2 "url" ||..."
				StringBuffer cmd = new StringBuffer();
				for (int i=0; i<browsers.length; i++)
					cmd.append( (i==0  ? "" : " || " ) + browsers[i] +" \"" + url + "\" ");

				rt.exec(new String[] { "sh", "-c", cmd.toString() });

			} else {
				return;
			}
		}catch (Exception e){
			return;
		}
		return;		

	}
	
    public static String stripExtension(String str) {
    	
        int pos = str.lastIndexOf(".");
        
        if (pos == -1){
        	return str;
        }else{
            return str.substring(0, pos);
        }
    }
    
    public static int roundTo16(int i){
        return i - (i % 16);
    }
}
