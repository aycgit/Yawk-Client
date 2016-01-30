package net.yawk.client.mods.world;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.yawk.client.Client;
import net.yawk.client.events.EventRender;
import net.yawk.client.modmanager.Mod;
import net.yawk.client.modmanager.RegisterMod;
import net.yawk.client.utils.ClientUtils;

import com.darkmagician6.eventapi.EventTarget;

@RegisterMod(name = "ArmorViewer", desc = "Look @ peoples armor.", type = Mod.Type.WORLD)
public class ArmorViewer extends Mod {
	
	@EventTarget
	public void onRender(EventRender event) {
		if(Client.getClient().getMinecraft().objectMouseOver != null && Client.getClient().getMinecraft().objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY) {
			Entity e = Client.getClient().getMinecraft().objectMouseOver.entityHit;
			if(e instanceof EntityPlayer) {
				this.renderArmour(e.getInventory());
			}
		}
	}
	
	public void renderArmour(ItemStack[] items) {
		ItemStack boots = items[0];
		ItemStack leggings = items[1];
		ItemStack body = items[2];
		ItemStack helm = items[3];
		
		ItemStack[] armor = new ItemStack[]{boots,leggings,body,helm};
		
		int x = (int) (ClientUtils.getScreenWidth() / 2 - 16 * (armor.length/2));
		
		for(ItemStack i : armor){
			RenderHelper.enableGUIStandardItemLighting();
			GlStateManager.enableRescaleNormal();
			Client.getClient().getMinecraft().getRenderItem().func_175042_a(i, x, 7);
			RenderHelper.disableStandardItemLighting();
			GlStateManager.disableRescaleNormal();
			
			x += 16;
		}	
   	}
}