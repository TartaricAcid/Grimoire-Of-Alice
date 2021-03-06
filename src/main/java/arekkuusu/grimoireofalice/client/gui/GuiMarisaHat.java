package arekkuusu.grimoireofalice.client.gui;

import arekkuusu.grimoireofalice.common.item.HatContainer;
import arekkuusu.grimoireofalice.common.lib.LibMod;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiMarisaHat extends GuiContainer {

	private static final ResourceLocation ICON_LOCATION = new ResourceLocation(LibMod.MODID.toLowerCase(), "textures/gui/hat.png");

	public GuiMarisaHat(InventoryPlayer playerInv, ItemStack stack) {
		super(new HatContainer(playerInv, stack));
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		String s = I18n.format("grimoire.gui.hat.name");
		fontRendererObj.drawString(s, xSize / 2 - fontRendererObj.getStringWidth(s) / 2, 0, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(ICON_LOCATION);
		int k = (width - xSize) / 2;
		int l = (height - ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, xSize, ySize);
	}
}
