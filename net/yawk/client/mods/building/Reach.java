package net.yawk.client.mods.building;

import com.darkmagician6.eventapi.EventTarget;
import com.darkmagician6.eventapi.types.EventType;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C0APacketAnimation;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.yawk.client.events.EventSendPacket;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.RegisterMod;

/**
 * Created by 10askinsw on 28/08/2015.
 */
@RegisterMod(name = "Reach", desc = "Break blocks further away", type = Mod.Type.BUILDING)
public class Reach extends Mod {

    private Minecraft mc;

    public Reach() {
        mc = Minecraft.getMinecraft();
    }

    @EventTarget
    public void onPacketSend(EventSendPacket e) {
        if (e.packet instanceof C07PacketPlayerDigging) {
        	
        	C07PacketPlayerDigging packet = (C07PacketPlayerDigging)e.packet;
        	
            if (e.type == EventType.PRE && packet.func_180762_c() == C07PacketPlayerDigging.Action.START_DESTROY_BLOCK) {
                setToReachPosition();
            }else if(e.type == EventType.POST && packet.func_180762_c() == C07PacketPlayerDigging.Action.STOP_DESTROY_BLOCK) {
                resetPosition();
            }

        }
    }

    private void resetPosition() {
        mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, mc.thePlayer.onGround));
    }

    private void setToReachPosition(){

        MovingObjectPosition trace = mc.objectMouseOver;

        if(trace.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK){
        	        	
            mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(Math.floor(trace.hitVec.xCoord) - 0.5,
            		trace.hitVec.yCoord,
            		Math.floor(trace.hitVec.zCoord) - 0.5,
            		mc.thePlayer.onGround));
        }
    }

    private void sendPosition(){
        mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, mc.thePlayer.onGround));
    }
}
