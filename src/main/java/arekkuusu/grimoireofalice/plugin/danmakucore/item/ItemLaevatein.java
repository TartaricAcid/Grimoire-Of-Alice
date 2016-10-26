/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.plugin.danmakucore.item;

import java.util.List;

import arekkuusu.grimoireofalice.entity.EntityMagicCircle;
import arekkuusu.grimoireofalice.item.ItemModSword;
import arekkuusu.grimoireofalice.lib.LibItemName;
import net.katsstuff.danmakucore.entity.danmaku.DanmakuBuilder;
import net.katsstuff.danmakucore.helper.DanmakuCreationHelper;
import net.katsstuff.danmakucore.lib.LibColor;
import net.katsstuff.danmakucore.lib.data.LibShotData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;

public class ItemLaevatein extends ItemModSword {

	@CapabilityInject(IItemHandler.class)
	private static final Capability<IItemHandler> ITEM_HANDLER_CAPABILITY = null;

	public ItemLaevatein(ToolMaterial material) {
		super(material, LibItemName.LAEVATEIN);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.UNCOMMON;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.GOLD + "Magic staff gambantein, Lævateinn of fire and chaos");
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		if(entity instanceof EntityPlayer && isSelected) {
			EntityPlayer player = (EntityPlayer)entity;
			player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 0, 0));
		}
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase user) {
		target.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 128, 0));
		stack.damageItem(1, user);
		return true;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		boolean isCreative = playerIn.capabilities.isCreativeMode;

		if(isCreative || playerIn.experienceLevel > 30) {
			playerIn.playSound(SoundEvents.ENTITY_ELDER_GUARDIAN_CURSE, 1F, itemRand.nextFloat() * 0.1F + 0.8F);
			itemStackIn.damageItem(10, playerIn);
			if(!worldIn.isRemote) {
				ItemStack fireCharge = new ItemStack(Items.FIRE_CHARGE);
				if(isCreative || playerIn.inventory.hasItemStack(fireCharge)) {
					DanmakuBuilder danmaku = DanmakuBuilder.builder()
							.setUser(playerIn)
							.setShot(LibShotData.SHOT_SPHERE_DARK.setColor(LibColor.COLOR_SATURATED_RED).setSize(2F))
							.build();

					DanmakuCreationHelper.createRandomRingShot(danmaku, 1 , 10, 5);

					if(!isCreative) {
						//noinspection ConstantConditions
						if(playerIn.hasCapability(ITEM_HANDLER_CAPABILITY, null)) {
							//noinspection ConstantConditions
							playerIn.getCapability(ITEM_HANDLER_CAPABILITY, null).extractItem(getSlotFor(playerIn, fireCharge), 1, false);
						}
					}

					EntityMagicCircle circle = new EntityMagicCircle(worldIn, playerIn, EntityMagicCircle.EnumTextures.RED_NORMAL, 15);
					worldIn.spawnEntityInWorld(circle);
					playerIn.getCooldownTracker().setCooldown(this, 10);
				}
			}
		}
		else {
			playerIn.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 256, 0));
			playerIn.playSound(SoundEvents.ENTITY_GHAST_SCREAM, 1F, itemRand.nextFloat() * 0.1F + 0.8F);
			playerIn.getCooldownTracker().setCooldown(this, 500);
		}
		playerIn.setActiveHand(hand);
		return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
	}

	private int getSlotFor(EntityPlayer player, ItemStack stack) {
		for (int i = 0; i < player.inventory.mainInventory.length; ++i) {
			if (player.inventory.mainInventory[i] != null
					&& ItemStack.areItemStacksEqual(stack, player.inventory.mainInventory[i])
					&& ItemStack.areItemStackTagsEqual(stack, player.inventory.mainInventory[i])) {
				return i;
			}
		}

		return -1;
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float x, float y, float z) {
		BlockPos block = pos.offset(facing);

		if(!player.canPlayerEdit(block, facing, stack)) {
			return EnumActionResult.PASS;
		}
		else {
			boolean success = false;
			for(int i = -1; i <= 1; i++) {
				for(int j = -1; j <= 1; j++) {
					BlockPos newPos = block.add(i, 0, j);
					if(world.isAirBlock(newPos)) {
						world.setBlockState(newPos, Blocks.FIRE.getDefaultState());
						success = true;
					}
				}
			}

			if(success) {
				player.playSound(SoundEvents.ENTITY_BLAZE_DEATH, 1F, itemRand.nextFloat() * 0.4F + 0.8F);
			}
			stack.damageItem(1, player);
			return success ? EnumActionResult.SUCCESS : EnumActionResult.FAIL;
		}
	}

	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return repair.getItem() == Items.BLAZE_ROD;
	}

	@Override
	public boolean isBookEnchantable(ItemStack itemstack1, ItemStack itemstack2) {
		return false;
	}

	@Override
	public int getItemEnchantability() {
		return 0;
	}
}
