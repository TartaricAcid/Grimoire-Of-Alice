package arekkuusu.grimoireofalice.item;

import arekkuusu.grimoireofalice.entity.EntityCursedDecoyDoll;
import arekkuusu.grimoireofalice.lib.LibItemName;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemCursedDecoyDoll extends ItemMod {

	public ItemCursedDecoyDoll() {
		super(LibItemName.DECOY_DOLL);
		setMaxStackSize(1);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.UNCOMMON;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.DARK_PURPLE + "Doll that takes damage for the player");
	}

	private void spawnDoll(ItemStack stack, World world, EntityPlayer player) {
		if(!world.isRemote) {
			EntityCursedDecoyDoll doll = new EntityCursedDecoyDoll(world, player);
			Vec3d look = player.getLookVec();
			float distance = 2F;
			double dx = player.posX + (look.xCoord * distance);
			double dy = player.posY;
			double dz = player.posZ + (look.zCoord * distance);
			doll.setPosition(dx, dy, dz);
			world.spawnEntityInWorld(doll);
		}
		player.playSound(SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 1F, itemRand.nextFloat() * 0.4F + 0.8F);
		--stack.stackSize;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		spawnDoll(itemStackIn, worldIn, playerIn);
		playerIn.setActiveHand(hand);
		return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing,
			float hitX, float hitY, float hitZ) {
		spawnDoll(stack, worldIn, playerIn);
		return EnumActionResult.SUCCESS;
	}
}
