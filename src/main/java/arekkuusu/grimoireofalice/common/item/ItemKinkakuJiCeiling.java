package arekkuusu.grimoireofalice.common.item;

import arekkuusu.grimoireofalice.common.entity.EntityKinkakuJiCeiling;
import arekkuusu.grimoireofalice.common.lib.LibItemName;
import net.katsstuff.danmakucore.item.IOwnedBy;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

@Optional.Interface(iface = "net.katsstuff.danmakucore.item.IOwnedBy", modid = "danmakucore")
public class ItemKinkakuJiCeiling extends ItemMod implements IOwnedBy {

	public ItemKinkakuJiCeiling() {
		super(LibItemName.SEAMLESS_CEILING_KINKAKU_JI);
		setMaxStackSize(1);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.EPIC;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		tooltip.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.seamless_ceiling_of_kinkaku_ji_header.name"));
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		if(!worldIn.isRemote) {
			double range = 50.0D;
			Vec3d look = playerIn.getLookVec();
			Vec3d vec3d = new Vec3d(playerIn.posX, playerIn.posY + playerIn.getEyeHeight(), playerIn.posZ);
			Vec3d vec3d1 = new Vec3d(playerIn.posX + look.xCoord * range, playerIn.posY + look.yCoord * range, playerIn.posZ + look.zCoord * range);
			RayTraceResult traceResult = playerIn.world.rayTraceBlocks(vec3d, vec3d1, false, true, true);
			range = traceResult != null ? traceResult.hitVec.distanceTo(vec3d) : range;

			List<Entity> list = playerIn.world.getEntitiesInAABBexcluding(playerIn
					, playerIn.getEntityBoundingBox().addCoord(look.xCoord * range, look.yCoord * range, look.zCoord * range).expandXyz(1.0D)
					, Entity::canBeCollidedWith);

			Entity entity = null;
			double d = 0.0D;
			for (Entity entity1 : list) {
				RayTraceResult movingObjectPosition1 = entity1.getEntityBoundingBox().calculateIntercept(vec3d, vec3d1);
				if (movingObjectPosition1 != null) {
					double d1 = vec3d.distanceTo(movingObjectPosition1.hitVec);
					if (d1 < d || d == 0.0D) {
						entity = entity1;
						d = d1;
					}
				}
			}

			EntityKinkakuJiCeiling ceiling;
			if(entity != null) {
				ceiling = new EntityKinkakuJiCeiling(playerIn.world, entity);
			}
			else {
				float distance = 15F;
				double dx = playerIn.posX + look.xCoord * distance;
				double dy = playerIn.posY + look.yCoord * distance + playerIn.getEyeHeight();
				double dz = playerIn.posZ + look.zCoord * distance;
                if(!isSafe(worldIn, new BlockPos(dx, dy, dz))) {
                    return new ActionResult<>(EnumActionResult.FAIL, itemStackIn);
                }
				ceiling = new EntityKinkakuJiCeiling(playerIn.world, playerIn, dx, dy, dz);
			}
			playerIn.world.spawnEntityInWorld(ceiling);
			--itemStackIn.stackSize;
		}
		return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
	}

	private boolean isSafe(World world, BlockPos pos) {
        IBlockState state = world.getBlockState(pos);
        return state.getBlock().isAir(state, world, pos);
    }

	@Override
	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
		if(!playerIn.world.isRemote) {
			EntityKinkakuJiCeiling ceiling = new EntityKinkakuJiCeiling(playerIn.world, target);
			playerIn.world.spawnEntityInWorld(ceiling);
		}
		return true;
	}

	@Optional.Method(modid = "danmakucore")
	@Override
	public net.katsstuff.danmakucore.entity.living.boss.EnumTouhouCharacters character(ItemStack stack) {
		return net.katsstuff.danmakucore.entity.living.boss.EnumTouhouCharacters.KAGUYA_HOURAISAN;
	}
}
