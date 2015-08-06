package net.yawk.client.modmanager;

import net.minecraft.network.play.server.S02PacketChat;
import net.yawk.client.events.EventRecievePacket;

import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.EventTarget;

public abstract class ChatListener extends EventListener{
	
	@EventTarget
	public void onPacketRecieve(EventRecievePacket e){
		
		System.out.println("CHAT LISTENER EVENT");
		
		if(e.packet instanceof S02PacketChat){
			onChat(((S02PacketChat)e.packet).func_148915_c().getUnformattedText());
		}
	}
	
	protected abstract void onChat(String msg);
}
