package arekkuusu.grimoireofalice.event;

import arekkuusu.grimoireofalice.entity.EntityMagicCircle;
import arekkuusu.grimoireofalice.item.ModItems;
import arekkuusu.grimoireofalice.potion.ModPotions;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class YukkuriEvent {

	@SubscribeEvent
	public void LivingDeathEvent(LivingDeathEvent event) {
		EntityLivingBase living = event.getEntityLiving();
		World world = living.worldObj;

		if(!world.isRemote && living instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)living;
			if(player.getEntityData().getBoolean("Eternal")) {
				player.worldObj.playSound(null, new BlockPos(player.posX + 0.5D, player.posY + 0.5D, player.posZ + 0.5D),
						SoundEvents.ENTITY_ELDER_GUARDIAN_CURSE, SoundCategory.HOSTILE, 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);

				player.hurtResistantTime = 50;

				EntityMagicCircle circle = new EntityMagicCircle(world, player, EntityMagicCircle.EnumTextures.RED_PENTAGRAM, player.hurtResistantTime);
				world.spawnEntityInWorld(circle);

				player.isDead = false;
				player.setHealth(player.getMaxHealth());
				event.setCanceled(true);
			}
			else {
				@SuppressWarnings("ConstantConditions")
				boolean potion = player.isPotionActive(ModPotions.ELIXIR);
				if(potion) {
					player.hurtResistantTime = 100;
					player.isDead = false;
					player.setHealth(player.getMaxHealth());
					event.setCanceled(true);
				}
			}
		}
	}

	@SubscribeEvent
	public void LivingHurtEvent(LivingHurtEvent event) {
		if(event.getEntityLiving() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)event.getEntityLiving();
			if(player.inventory.hasItemStack(new ItemStack(ModItems.GHASTLY_SEND_OFF_LANTERN))) {
				if(player.getCooldownTracker().hasCooldown(ModItems.GHASTLY_SEND_OFF_LANTERN)) {
					event.setCanceled(true);
					return;
				}
			}

			if(player.inventory.hasItemStack(new ItemStack(ModItems.SUBSTITUTE_JIZO))) {

				@SuppressWarnings("ConstantConditions") //Liar
				IItemHandler capability = player.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);

				for(int i = 0; i < capability.getSlots(); i++) {
					ItemStack stack = capability.getStackInSlot(i);
					if(stack != null && stack.getItem() == ModItems.SUBSTITUTE_JIZO) {
						capability.extractItem(i, 1, false);
						player.worldObj.playSound(null, new BlockPos(player.posX + 0.5D, player.posY + 0.5D, player.posZ + 0.5D),
								SoundEvents.BLOCK_GRASS_BREAK, SoundCategory.HOSTILE, 0.5F, player.worldObj.rand.nextFloat() * 0.1F + 0.9F);
						event.setCanceled(true);
						break;
					}
				}
			}
		}
	}
}
