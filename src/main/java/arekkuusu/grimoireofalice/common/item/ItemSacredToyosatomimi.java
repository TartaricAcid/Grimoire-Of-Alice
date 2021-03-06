/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item;

import arekkuusu.grimoireofalice.common.lib.LibItemName;
import net.katsstuff.danmakucore.item.IOwnedBy;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

@Optional.Interface(iface = "net.katsstuff.danmakucore.item.IOwnedBy", modid = "danmakucore")
public class ItemSacredToyosatomimi extends ItemSwordOwner implements IOwnedBy {

	public ItemSacredToyosatomimi(ToolMaterial material) {
		super(material, LibItemName.SACRED_TOYOSATOMIMI);
		setNoRepair();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.EPIC;
	}

	@SuppressWarnings("ConstantConditions")
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		if(GuiScreen.isShiftKeyDown()) {
			list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.sacred_sword_of_toyosatomimi_description_top.name"));
			list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.sacred_sword_of_toyosatomimi_description_middle.name"));
			list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.sacred_sword_of_toyosatomimi_description_bottom.name"));
		}
		else {
			list.add(TextFormatting.DARK_GRAY + "" + TextFormatting.ITALIC + I18n.format("grimoire.tooltip.sacred_sword_of_toyosatomimi_shift.name"));
		}
		super.addInformation(stack, player, list, p_77624_4_);
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		target.motionX = -MathHelper.sin((float)Math.toRadians(attacker.rotationYaw));
		target.motionZ = MathHelper.cos((float)Math.toRadians(attacker.rotationYaw));
		return true;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		playerIn.setActiveHand(hand);
		return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
	}

	@SuppressWarnings("ConstantConditions")
	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
		if(!worldIn.isRemote) {
			int timeUsed = getMaxItemUseDuration(stack) - timeLeft;
			if(timeUsed > 50) return;

			if(entityLiving instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer)entityLiving;
				if(isOwner(stack, player)) {
					List<EntityLivingBase> list = worldIn.getEntitiesWithinAABB(EntityLivingBase.class, player.getEntityBoundingBox().expandXyz(timeUsed), livingBase -> livingBase != player);
					if(!list.isEmpty()) {
						player.addChatComponentMessage(
								new TextComponentString(TextFormatting.GOLD + "- - - - - - - - - - - - - - - - - - - - - - - - -"));
						list.forEach(mob -> player.addChatComponentMessage(new TextComponentString(
								getColorForEntity(mob) + "- " + mob.getName() + TextFormatting.RESET + TextFormatting.ITALIC + " : {" + (int)mob.posX + ", " + (int)mob.posY + ", " + (int)mob.posZ + "}")));
						player.addChatComponentMessage(
								new TextComponentString(TextFormatting.GOLD + "- - - - - - - - - - - - - - - - - - - - - - - - -"));
					}
					else {
						player.addChatComponentMessage(new TextComponentString(TextFormatting.GOLD + ""
								+ new TextComponentTranslation("item.toyosatomimi.empty").getFormattedText()));
					}
				}
				player.getCooldownTracker().setCooldown(this, timeLeft);
				stack.damageItem(1, player);
			}
		}
	}

	private TextFormatting getColorForEntity(EntityLivingBase livingBase) {
		if (livingBase instanceof EntityMob) {
			return TextFormatting.DARK_RED;
		}
		else if (livingBase instanceof EntityAnimal) {
			return TextFormatting.GOLD;
		}
		else if (livingBase instanceof EntityWaterMob) {
			return TextFormatting.DARK_AQUA;
		}

		return TextFormatting.DARK_GRAY;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.BOW;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 50;
	}

	@Override
	public int getItemEnchantability() {
		return 0;
	}

	@Optional.Method(modid = "danmakucore")
	@Override
	public net.katsstuff.danmakucore.entity.living.boss.EnumTouhouCharacters character(ItemStack stack) {
		return net.katsstuff.danmakucore.entity.living.boss.EnumTouhouCharacters.TOYOSATOMIMI_NO_MIKO;
	}
}
