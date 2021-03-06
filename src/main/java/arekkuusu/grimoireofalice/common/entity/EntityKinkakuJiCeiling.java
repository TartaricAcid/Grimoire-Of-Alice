package arekkuusu.grimoireofalice.common.entity;

import arekkuusu.grimoireofalice.common.item.ModItems;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityKinkakuJiCeiling extends Entity {

	private boolean doSound;

	public EntityKinkakuJiCeiling(World worldIn) {
		super(worldIn);
	}

	public EntityKinkakuJiCeiling(World worldIn, Entity entity) {
		super(worldIn);
		setPosition(entity.posX, entity.posY + 5, entity.posZ);
		rotationYaw = entity.rotationYaw % 360.0F;
	}

	public EntityKinkakuJiCeiling(World worldIn, EntityLivingBase livingBase, double x, double y, double z) {
		super(worldIn);
		setPosition(x, y, z);
		rotationYaw = livingBase.rotationYaw % 360.0F;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!world.isRemote) {
			if (!onGround && motionY < 0) {
				world.getEntitiesWithinAABBExcludingEntity(this, getEntityBoundingBox().offset(0, -1, 0)).forEach(entity -> entity.attackEntityFrom(DamageSource.fallingBlock, 50));
			}
			if (ticksExisted > 2000) {
				dropItem(ModItems.SEAMLESS_CEILING_KINKAKU_JI, 1);
				setDead();
			}
		}

		Vec3d vec3d = new Vec3d(this.posX, this.posY, this.posZ);
		Vec3d vec3d1 = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
		RayTraceResult raytraceresult = this.world.rayTraceBlocks(vec3d, vec3d1);

		onGround = raytraceresult != null && raytraceresult.typeOfHit == RayTraceResult.Type.BLOCK
				&& world.getBlockState(raytraceresult.getBlockPos()).getMaterial() != Material.PLANTS;

		if (!onGround) {
			motionY -= 0.025;
			posX += motionX;
			posY += motionY;
			posZ += motionZ;
			doSound = true;
		}
		else if (doSound) {
			playSound(SoundEvents.BLOCK_ANVIL_LAND, 1F, 1F);
			doSound = false;
		}

		setPosition(posX, posY, posZ);
	}

	@Override
	public boolean processInitialInteract(EntityPlayer player, @Nullable ItemStack stack, EnumHand hand) {
		if(!world.isRemote && !isDead && onGround) {
            if(!player.capabilities.isCreativeMode) {
                dropItem(ModItems.SEAMLESS_CEILING_KINKAKU_JI, 1);
            }
			setDead();
		}
		return true;
	}

	@Override
	public AxisAlignedBB getEntityBoundingBox() {
		AxisAlignedBB alignedBB = super.getEntityBoundingBox();
		return new AxisAlignedBB(alignedBB.minX - 1.7, alignedBB.minY, alignedBB.minZ - 1.7, alignedBB.minX + 2.3, alignedBB.minY + 0.1, alignedBB.minZ + 2.3);
	}

	@Override
	public boolean canBeCollidedWith() {
		return true;
	}

	@Override
	public boolean canBePushed() {
		return true;
	}

	@Override
	protected void entityInit() {

	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {

	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {

	}
}
