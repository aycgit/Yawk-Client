package net.yawk.client.hooks;

import com.darkmagician6.eventapi.EventManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.yawk.client.events.EventClickBlock;
import net.yawk.client.events.EventDamageBlock;
import net.yawk.client.events.EventGuiRender;
import net.yawk.client.events.EventUpdateController;

public class PlayerControllerMPHook extends PlayerControllerMP{

	public PlayerControllerMPHook(Minecraft mcIn, NetHandlerPlayClient p_i45062_2_) {
		super(mcIn, p_i45062_2_);
	}
	
	private EventUpdateController tick = new EventUpdateController();
	
	@Override
	public void updateController() {
		super.updateController();
		EventManager.call(tick);
	}
	
	@Override
    public boolean onPlayerDamageBlock(BlockPos pos, EnumFacing facing)
	{
		EventDamageBlock event = new EventDamageBlock(pos, facing);
		EventManager.call(event);
		
		if(!event.isCancelled()){
			return super.onPlayerDamageBlock(pos, facing);
		}else{
			return false;
		}
	}
	
	@Override
	public boolean clickBlock(BlockPos pos, EnumFacing facing) {
		EventClickBlock event = new EventClickBlock(pos, facing);
		EventManager.call(event);
		
		if(!event.isCancelled()){
			return super.clickBlock(pos, facing);
		}else{
			return false;
		}
	}
}
