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
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

@Optional.Interface(iface = "net.katsstuff.danmakucore.item.IOwnedBy", modid = "danmakucore")
public class ItemGhastlySendOffLantern extends ItemMod implements IOwnedBy {

	private static final String TAG = "timer";

	public ItemGhastlySendOffLantern() {
		super(LibItemName.SEND_OFF_LANTERN);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return getTimer(stack) <= 0;
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return getTimer(stack) <= 0 ? EnumRarity.RARE : EnumRarity.COMMON;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.send_off_lantern_header.name"));
		list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.send_off_lantern_description.name"));
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		int timer = getTimer(stack);
		if (timer > 0) {
			setTimer(stack, timer - 1);
		}
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer player, EnumHand hand) {
		if (getTimer(itemStackIn) > 0) return new ActionResult<>(EnumActionResult.FAIL, itemStackIn);
		player.getCooldownTracker().setCooldown(this, 50);
		setAllIventory(player, 600);
		if (!player.capabilities.isCreativeMode) {
			--itemStackIn.stackSize;
		}
		return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
	}

	@SuppressWarnings ("ConstantConditions")
	private void setAllIventory(EntityPlayer player, int time) {
		if (player.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)) {
			IItemHandler capability = player.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);

			for (int i = 0; i < capability.getSlots(); i++) {
				ItemStack stack = capability.getStackInSlot(i);
				if (stack != null && stack.getItem() == this) {
					setTimer(stack, time);
				}
			}
		}
	}

	private void setTimer(ItemStack stack, int time) {
		getNBT(stack).setInteger(TAG, time);
	}

	private int getTimer(ItemStack stack) {
		return getNBT(stack).getInteger(TAG);
	}

	private NBTTagCompound getNBT(ItemStack stack) {
		NBTTagCompound tag = stack.getTagCompound();
		if (tag == null) {
			tag = new NBTTagCompound();
			stack.setTagCompound(tag);
		}
		return tag;
	}

	@Optional.Method(modid = "danmakucore")
	@Override
	public net.katsstuff.danmakucore.entity.living.boss.EnumTouhouCharacters character(ItemStack stack) {
		return net.katsstuff.danmakucore.entity.living.boss.EnumTouhouCharacters.SEIJA_KIJIN;
	}
}
