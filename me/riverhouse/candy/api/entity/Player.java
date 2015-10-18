package me.riverhouse.candy.api.entity;

import me.riverhouse.candy.api.world.Location;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.stats.StatFileWriter;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class Player extends EntityPlayerSP {

	public Player(Minecraft mcIn, World worldIn, NetHandlerPlayClient p_i46278_3_, StatFileWriter p_i46278_4_) {
		super(mcIn, worldIn, p_i46278_3_, p_i46278_4_);
	}
	
	public Location getLocation(){
		return new Location(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
	}
	
	public void addChatMessage(String string){
		this.addChatMessage(new ChatComponentText(string.replaceAll("&", "§")));
	}
	
}
