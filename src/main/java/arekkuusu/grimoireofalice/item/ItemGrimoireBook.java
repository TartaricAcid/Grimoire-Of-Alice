package arekkuusu.grimoireofalice.item;

import arekkuusu.grimoireofalice.entity.EntityMagicCircle;
import arekkuusu.grimoireofalice.lib.LibItemName;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemGrimoireBook extends ItemMod {

	public EntityMagicCircle circle;
	
	public ItemGrimoireBook() {
		super(LibItemName.GRIMOIREBOOK);
		setMaxStackSize(1);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
    	if(!worldIn.isRemote) {
    		circle = new EntityMagicCircle(worldIn, playerIn, 100);
    		worldIn.spawnEntityInWorld(circle);
    	}
		playerIn.setActiveHand(hand);
		return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
	}
		
	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.BLOCK;
    }

	@Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 72000;
    }
	
}