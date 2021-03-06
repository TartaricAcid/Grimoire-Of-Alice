/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item;

import java.util.List;

import arekkuusu.grimoireofalice.common.lib.LibItemName;
import net.katsstuff.danmakucore.item.IOwnedBy;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Optional.Interface(iface = "net.katsstuff.danmakucore.item.IOwnedBy", modid = "danmakucore")
public class ItemBloodThirstyOrb extends ItemMod implements IOwnedBy {

	public ItemBloodThirstyOrb() {
		super(LibItemName.BLOOD_THIRSTY_ORB);
		setMaxStackSize(1);
		setMaxDamage(5);
		setNoRepair();
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.RARE;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.blood_thirsty_orb_description.name"));
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		playerIn.setActiveHand(hand);
		return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) { //Recycled code...
		if (entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entityLiving;
			if (player.isSneaking()) {
				moveToClosestPlayer(worldIn, player);
			}
			else {
				moveToMob(player);
			}
			stack.damageItem(1, player);
			player.attackEntityFrom(DamageSource.generic, 1);
		}
	}

	private void moveToClosestPlayer(World worldIn, EntityPlayer player) {
		EntityPlayer closest = worldIn.getClosestPlayerToEntity(player, 30D);
		if(closest != null) {
			if(player instanceof EntityPlayerMP) {
				((EntityPlayerMP) player).setPositionAndUpdate(closest.posX, closest.posY, closest.posZ);
			}
			player.playSound(SoundEvents.ENTITY_ENDERMEN_TELEPORT, 1F, itemRand.nextFloat() * 0.4F + 0.8F);
		}
	}

	private void moveToMob(EntityPlayer player) {
		double range = 32.0D;
		Vec3d look = player.getLookVec();
		Vec3d vec3d = new Vec3d(player.posX, player.posY + player.getEyeHeight(), player.posZ);
		Vec3d vec3d1 = new Vec3d(player.posX + look.xCoord * range, player.posY + look.yCoord * range, player.posZ + look.zCoord * range);
		RayTraceResult movingObjectPosition = player.world.rayTraceBlocks(vec3d, vec3d1, false, true, true);
		if(movingObjectPosition != null) {
			vec3d1 = new Vec3d(movingObjectPosition.hitVec.xCoord, movingObjectPosition.hitVec.yCoord, movingObjectPosition.hitVec.zCoord);
		}
		EntityLivingBase entity = null;
		List<EntityLivingBase> list = player.world.getEntitiesWithinAABB(EntityLivingBase.class,
				player.getEntityBoundingBox().addCoord(look.xCoord * range, look.yCoord * range, look.zCoord * range).expandXyz(1.0D),
				foundEntity -> foundEntity != player);
		double d = 0.0D;
		for(EntityLivingBase entity1 : list) {
			AxisAlignedBB axisalignedbb = entity1.getEntityBoundingBox().expandXyz(0.3F);
			RayTraceResult movingObjectPosition1 = axisalignedbb.calculateIntercept(vec3d, vec3d1);
			if(movingObjectPosition1 != null) {
				double d1 = vec3d.distanceTo(movingObjectPosition1.hitVec);
				if(d1 < d || d == 0.0D) {
					entity = entity1;
					d = d1;
				}
			}
		}

		if(entity != null) {
			player.setPosition(entity.posX, entity.posY, entity.posZ);
			player.playSound(SoundEvents.ENTITY_ENDERMEN_TELEPORT, 1F, itemRand.nextFloat() * 0.4F + 0.8F);
		}
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.BOW;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 500;
	}

	@Override
	public int getItemEnchantability() {
		return 0;
	}

	@Optional.Method(modid = "danmakucore")
	@Override
	public net.katsstuff.danmakucore.entity.living.boss.EnumTouhouCharacters character(ItemStack stack) {
		return net.katsstuff.danmakucore.entity.living.boss.EnumTouhouCharacters.REIMU_HAKUREI;
	}
}
