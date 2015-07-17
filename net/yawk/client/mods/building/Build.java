package net.yawk.client.mods.building;

import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C09PacketHeldItemChange;
import net.minecraft.network.play.client.C0APacketAnimation;
import net.minecraft.network.play.client.C0BPacketEntityAction;
import net.minecraft.network.play.client.C0DPacketCloseWindow;
import net.minecraft.network.play.client.C12PacketUpdateSign;
import net.minecraft.network.play.client.C16PacketClientStatus;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.yawk.client.Client;
import net.yawk.client.events.EventClickBlock;
import net.yawk.client.events.EventPlaceBlock;
import net.yawk.client.events.EventSendPacket;
import net.yawk.client.events.EventTick;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.ModDetails;
import net.yawk.client.utils.ClientUtils;

import com.darkmagician6.eventapi.EventTarget;

@ModDetails(name = "Build", desc = "Automatically build structures", type = Mod.Type.BUILDING)
public class Build extends Mod{
	
	@EventTarget
	public void onSend(EventPlaceBlock e){
		//place(e.getPos().getX(), e.getPos().getY()+1, e.getPos().getZ());
		//place(e.getPos().getX(), e.getPos().getY()+2, e.getPos().getZ());
		
		place(e.getPos().getX(), e.getPos().getY()+1, e.getPos().getZ(), e.getFacing());
		place(e.getPos().getX(), e.getPos().getY()+2, e.getPos().getZ(), e.getFacing());
		
		place(e.getPos().getX()-1, e.getPos().getY(), e.getPos().getZ(), e.getFacing());
		place(e.getPos().getX()-1, e.getPos().getY()+2, e.getPos().getZ(), e.getFacing());
		place(e.getPos().getX()-2, e.getPos().getY()+2, e.getPos().getZ(), e.getFacing());
		place(e.getPos().getX()-2, e.getPos().getY()+3, e.getPos().getZ(), e.getFacing());
		place(e.getPos().getX(), e.getPos().getY()+3, e.getPos().getZ(), e.getFacing());
		place(e.getPos().getX(), e.getPos().getY()+4, e.getPos().getZ(), e.getFacing());
		place(e.getPos().getX()+1, e.getPos().getY()+4, e.getPos().getZ(), e.getFacing());
		
		place(e.getPos().getX()+1, e.getPos().getY()+2, e.getPos().getZ(), e.getFacing());
		place(e.getPos().getX()+2, e.getPos().getY()+2, e.getPos().getZ(), e.getFacing());
		place(e.getPos().getX()+2, e.getPos().getY()+1, e.getPos().getZ(), e.getFacing());
	}
	
	private void place(int x, int y, int z, EnumFacing facing){
		
		ClientUtils.sendPacket(new C08PacketPlayerBlockPlacement(new BlockPos(x, y, z),
				facing.getIndex(),
				Client.getClient().getPlayer().getHeldItem(),
				x,
				y,
				z));
	}
	
	@Override
	public void onEnable() {
		
	}
	
	@Override
	public void onDisable() {
		
	}
}
