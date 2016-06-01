/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireOfAlice.client.render;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import arekkuusu.grimoireOfAlice.client.model.ModelOnbashira;
import arekkuusu.grimoireOfAlice.lib.LibMod;

public class RenderOnbashira extends TileEntitySpecialRenderer {

	private static final ResourceLocation TEXTURE = new ResourceLocation(LibMod.MODID, "textures/models/Onbashira.png");
	private static final ModelOnbashira MODEL = new ModelOnbashira();

	@Override
	public void renderTileEntityAt(TileEntity p_147500_1_, double p_147500_2_, double p_147500_4_, double p_147500_6_, float p_147500_8_) {

		GL11.glPushMatrix();
		GL11.glTranslatef((float)p_147500_2_ + 0.5F, (float)p_147500_4_ + 1.5F, (float)p_147500_6_ + 0.5F);
		GL11.glRotatef(180, 0F, 0f, 1f);
		bindTexture(TEXTURE);

		GL11.glPushMatrix();
		MODEL.renderModel(0.0625F);
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}
}