/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.entity;

import java.lang.reflect.Field;

import arekkuusu.grimoireofalice.common.item.ModItems;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

public class EntityNeedle extends EntityArrow {

	private Field ticksInAir;

	public EntityNeedle(World world) {
		super(world);
	}

	public EntityNeedle(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	public EntityNeedle(World world, EntityLivingBase thrower) {
		super(world, thrower);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		worldObj.playSound(null, new BlockPos(posX + 0.5D, posY + 0.5D, posZ + 0.5D), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.NEUTRAL, 1.0F,
				0.4F + 0.8F);
		if(getTicksInAir() >= 15 && !worldObj.isRemote) {
			setDead();
		}
	}

	public int getTicksInAir() {
		if(ticksInAir == null) {
			ticksInAir = ReflectionHelper.findField(EntityArrow.class, "ticksInAir", "field_70257_an");
			ticksInAir.setAccessible(true);
		}

		try {
			return ticksInAir.getInt(this);
		}
		catch(IllegalAccessException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	protected ItemStack getArrowStack() {
		return new ItemStack(ModItems.SHINMYOUMARU_NEEDLE);
	}
}
