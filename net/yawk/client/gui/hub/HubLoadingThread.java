package net.yawk.client.gui.hub;

import java.io.IOException;
import java.util.ArrayList;

import net.yawk.client.utils.ClientUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class HubLoadingThread implements Runnable {
	
	private GuiHub hub;
	
	public HubLoadingThread(GuiHub hub) {
		super();
		this.hub = hub;
	}
	
	@Override
	public void run() {
				
		hub.slates.clear();
		
		try {
			
			Document doc = Jsoup.connect("http://yawk.net/news.php").userAgent(ClientUtils.USER_AGENT).get();
			JsonArray array = (JsonArray) new JsonParser().parse(doc.text());
			
			for(JsonElement el : array){
				
				JsonObject slate = el.getAsJsonObject();
				
				ArrayList<SquareCell> cells = new ArrayList<SquareCell>();
				
				for(JsonElement cellElement : slate.get("cells").getAsJsonArray()){
					
					JsonObject cell = cellElement.getAsJsonObject();
					cells.add(new SquareCell(cell.get("title").getAsString(), cell.get("colour").getAsInt(), cell.get("contents").getAsString()));
					
				}
				
				hub.slates.add(new Slate(slate.get("header").getAsString(), cells.toArray(new SquareCell[cells.size()])));
			}
			
			hub.setState(State.CONNECTED);
			
		} catch (IOException e) {
			e.printStackTrace();
			hub.setState(State.FAILED);
		}
 	}
}
