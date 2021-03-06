/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.plugin.danmakucore.item;

import java.util.List;

import arekkuusu.grimoireofalice.api.sound.GrimoireSoundEvents;
import arekkuusu.grimoireofalice.common.plugin.danmakucore.entity.EntityCameraSquare;
import arekkuusu.grimoireofalice.common.item.ItemMod;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemCamera extends ItemMod {

	public ItemCamera(String id) {
		super(id);
		setMaxDamage(180);
		setMaxStackSize(1);
		addPropertyOverride(new ResourceLocation("takingPhoto"),
				(stack, world, entity) -> entity != null && entity.isHandActive() && stack.isItemEqual(entity.getActiveItemStack()) ? 1F : 0F);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.UNCOMMON;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.tengu_camera_header.name"));
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
        if (!worldIn.isRemote) {
            int size = getSize();

            EntityCameraSquare camera = new EntityCameraSquare(worldIn, playerIn, size);
            Vec3d look = playerIn.getLookVec();
            double distance = size + 4D;
            double dx = playerIn.posX + look.xCoord * distance;
            double dy = playerIn.posY + 2 + look.yCoord * distance;
            double dz = playerIn.posZ + look.zCoord * distance;
            camera.setPosition(dx, dy, dz);
            worldIn.spawnEntityInWorld(camera);
        }
        worldIn.playSound(playerIn, playerIn.getPosition(), GrimoireSoundEvents.CAMERA_BEEP, SoundCategory.PLAYERS, 1F, 1F);
        playerIn.setActiveHand(hand);
        return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
    }

	@Override
	public void onUsingTick(ItemStack stack, EntityLivingBase player, int count) {
		player.motionX = 0;
		player.motionY = 0;
		player.motionZ = 0;
		if(player instanceof EntityPlayerMP) {
			EntityPlayerMP playerMP = (EntityPlayerMP) player;
			playerMP.setPositionAndUpdate(playerMP.prevPosX, playerMP.posY, playerMP.prevPosZ);
		}
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
		if (entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entityLiving;
			if(!player.capabilities.isCreativeMode)
				stack.damageItem(1, player);
			worldIn.playSound(player, player.getPosition(), GrimoireSoundEvents.CAMERA_SHOOT, SoundCategory.PLAYERS, 1F, 1F);
			player.getCooldownTracker().setCooldown(this, 100);
		}
	}

	public int getSize() {
        return 0;
    }

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 7000;
	}

	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return false;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.NONE;
	}
}
