/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireOfAlice.block;

import java.util.Random;

import arekkuusu.grimoireOfAlice.client.tile.TileEntityHolyKeyStone;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockHolyKeyStone extends BlockGOABase implements ITileEntityProvider {

	@SideOnly(Side.CLIENT)
	private IIcon txTop;
	@SideOnly(Side.CLIENT)
	private IIcon txFront;

	public BlockHolyKeyStone(Material material) {
		super(material);
		setHardness(2.0F);
		setStepSound(Block.soundTypeStone);
		setHarvestLevel("pickaxe", 1);
		setResistance(15.0F);
		setCreativeTab(CreativeTabs.tabDecorations);
		setLightLevel(0.5F);
	}

	@Override
	public int getRenderType() {
		return -1;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityHolyKeyStone();
	}
	
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
    {
        float f = 0.125F;
        return AxisAlignedBB.getBoundingBox((double)p_149668_2_, (double)p_149668_3_, (double)p_149668_4_, (double)(p_149668_2_ + 1), (double)((float)(p_149668_3_ + 1) - f), (double)(p_149668_4_ + 1));
    }

	/*@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World w, int x, int y, int z, Random rand) {
		renderParticle(w, x, y, z);
	}

	@SideOnly(Side.CLIENT)
	private void renderParticle(World w, int x, int y, int z) {
		Random random = w.rand;
		double d0 = 0.0625D;
		for(int l = 0; l < 6; ++l) {
			double d1 = x + random.nextFloat();
			double d2 = y + random.nextFloat();
			double d3 = z + random.nextFloat();
			if(l == 0 && !w.getBlock(x, y + 1, z).isOpaqueCube()) d2 = y + 1 + d0;
			if(l == 1 && !w.getBlock(x, y - 1, z).isOpaqueCube()) d2 = y + 0 - d0;
			if(l == 2 && !w.getBlock(x, y, z + 1).isOpaqueCube()) d3 = z + 1 + d0;
			if(l == 3 && !w.getBlock(x, y, z - 1).isOpaqueCube()) d3 = z + 0 - d0;
			if(l == 4 && !w.getBlock(x + 1, y, z).isOpaqueCube()) d1 = x + 1 + d0;
			if(l == 5 && !w.getBlock(x - 1, y, z).isOpaqueCube()) d1 = x + 0 - d0;
			if(d1 < x || d1 > x + 1 || d2 < 0.0D || d2 > y + 1 || d3 < z || d3 > z + 1) {
				w.spawnParticle("enchantmenttable", d1, d2, d3, 0.0D, 0.0D, 0.0D);
			}
		}
	}*/

	@Override
	public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int e) {
		world.createExplosion(null, x+0.5, y, z+0.5, 2.0F, false);
	}
	
	@Override
	public Item getItemDropped(int metadata, Random random, int fortune){
		return null;
	}
	
	public boolean canSilkHarvest(World world, EntityPlayer player, int x, int y, int z, int metadata) {
        return false;
    }
	
	@Override
	public void onEntityCollidedWithBlock(World p_149670_1_, int p_149670_2_, int p_149670_3_, int p_149670_4_, Entity p_149670_5_)
    {
		if(p_149670_5_ instanceof EntityLivingBase) {
		boolean isRain = p_149670_1_.isRaining();
		if(!isRain){
		EntityLivingBase livingEntity = (EntityLivingBase)p_149670_5_;
		livingEntity.addPotionEffect(new PotionEffect(Potion.regeneration.getId(), 50, 2));
		livingEntity.addPotionEffect(new PotionEffect(Potion.digSpeed.getId(), 50, 2));
		livingEntity.addPotionEffect(new PotionEffect(Potion.moveSpeed.getId(), 50, 2));
		}}
    }

	/*@Override
	public void onEntityWalking(World par1World, int par2, int par3, int par4, Entity par5Entity) {
		if(par5Entity instanceof EntityLivingBase) {
			boolean isRain = par1World.isRaining();
			if(isRain){
			EntityLivingBase livingEntity = (EntityLivingBase)par5Entity;
			livingEntity.addPotionEffect(new PotionEffect(Potion.regeneration.getId(), 50, 3));
			livingEntity.addPotionEffect(new PotionEffect(Potion.digSpeed.getId(), 50, 3));
			livingEntity.addPotionEffect(new PotionEffect(Potion.moveSpeed.getId(), 50, 3));
			}
		}
	}*/

	@Override
	public void breakBlock(World world, int x, int y, int z, Block p_149749_5_, int p_149749_6_) {
		super.breakBlock(world, x, y, z, p_149749_5_, p_149749_6_);
		world.removeTileEntity(x, y, z);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int meta) {
		return side == 1 ? Blocks.stone.getBlockTextureFromSide(side) : side == 0 ? Blocks.stone.getBlockTextureFromSide(side) : blockIcon;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister icon) {
		blockIcon = icon.registerIcon(getTextureName() + "_side");
	}
}