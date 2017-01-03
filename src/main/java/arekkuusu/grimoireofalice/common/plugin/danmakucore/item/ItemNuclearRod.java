/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.plugin.danmakucore.item;

import arekkuusu.grimoireofalice.api.sound.GrimoireSoundEvents;
import arekkuusu.grimoireofalice.common.item.ItemMod;
import arekkuusu.grimoireofalice.common.item.ModItems;
import arekkuusu.grimoireofalice.common.lib.LibItemName;
import arekkuusu.grimoireofalice.common.plugin.danmakucore.LibGOAShotData;
import arekkuusu.grimoireofalice.common.potion.ModPotions;
import net.katsstuff.danmakucore.entity.danmaku.DanmakuBuilder;
import net.katsstuff.danmakucore.entity.danmaku.EntityDanmaku;
import net.katsstuff.danmakucore.lib.LibColor;
import net.katsstuff.danmakucore.lib.data.LibShotData;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemNuclearRod extends ItemMod {

	public ItemNuclearRod() {
		super(LibItemName.NUCLEAR_ROD);
		setMaxStackSize(1);
		addPropertyOverride(new ResourceLocation("using"),
				(stack, world, entity) -> entity != null && entity.isHandActive() && entity.getActiveItemStack() == stack ? 1F : 0F);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.RARE;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.nuclear_rod_header.name"));
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		playerIn.setActiveHand(hand);
		return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
	}

	@Override
	public void onUsingTick(ItemStack stack, EntityLivingBase livingBase, int count) {
		List<EntityLivingBase> list = livingBase.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, livingBase.getEntityBoundingBox().expandXyz(5));
		for (EntityLivingBase living : list) {
			if (living instanceof EntityPlayer) {
				if(!hasBoots((EntityPlayer) living)) {
					living.addPotionEffect(new PotionEffect(ModPotions.RADIATION_POISONING, 100));
				}
			}
			else {
				living.addPotionEffect(new PotionEffect(ModPotions.RADIATION_POISONING, 100));
			}
		}
		if (count % 50 == 0) {
			livingBase.playSound(GrimoireSoundEvents.CAUTION, 0.2F, 0F);
		}
	}

	private boolean hasBoots(EntityPlayer player) {
		return player.inventory.armorInventory[0] != null && player.inventory.armorInventory[0].getItem() == ModItems.NUCLEAR_BOOTS;
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
		if (entityLiving instanceof EntityPlayer) {
			((EntityPlayer) entityLiving).getCooldownTracker().setCooldown(this, 10);
		}
		int timeUsed = getMaxItemUseDuration(stack) - timeLeft;
		if (timeUsed < 40) return;
		entityLiving.playSound(GrimoireSoundEvents.WAVE, 0.2F, 1F);
		if (!worldIn.isRemote) {
			EntityDanmaku danmaku = DanmakuBuilder.builder()
					.setUser(entityLiving)
					.setShot(LibGOAShotData.SUN.setColor(LibColor.COLOR_SATURATED_RED).setSize(5))
					.setMovementData(1F)
					.build().asEntity();
			worldIn.spawnEntityInWorld(danmaku);
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
}