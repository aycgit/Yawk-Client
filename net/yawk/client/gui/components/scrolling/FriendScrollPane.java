package net.yawk.client.gui.components.scrolling;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

import net.yawk.client.gui.Window;
import net.yawk.client.gui.components.TextDisplay;
import net.yawk.client.gui.components.selectors.SelectorButton;
import net.yawk.client.gui.components.selectors.SelectorSystem;

public class FriendScrollPane extends ScrollPane{

	private ArrayList<String> friends = new ArrayList<String>();
	private SelectorSystem system;
	
	public FriendScrollPane(int height, SelectorSystem system) {
		super(height);
		this.system = system;
	}
	
	public void addFriend(String name){
		SelectorButton button = new SelectorButton(name, system);
		components.add(button);
		friends.add(name);
		system.add(button);
	}
	
	public void removeFriend(String name){
		
		Iterator it = components.iterator();
		
		while(it.hasNext()){
			
			TextDisplay display = (TextDisplay) it.next();
			
			if(display.getText().equals(name)){
				components.remove(display);
				return;
			}
		}
		
		friends.remove(name);
	}
}
