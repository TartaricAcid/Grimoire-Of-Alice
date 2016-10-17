package arekkuusu.grimoireofalice.entity;

import arekkuusu.grimoireofalice.item.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.*;

public class EntityStopWatch extends Entity {

	private EntityPlayer user;
	private ArrayList<UUID> players = new ArrayList<>();
	private HashMap<UUID, double[]> dataEntities = new HashMap<>();

	public EntityStopWatch(World worldIn) {
		super(worldIn);
	}

	public EntityStopWatch(World worldIn, EntityPlayer player) {
		super(worldIn);
		user = player;
		ignoreFrustumCheck = true;
		preventEntitySpawning = true;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if(user != null) {
			if (ticksExisted > 500 || (user.isSneaking() && user.isSwingInProgress)) {
				stopEntity();
			} else {
				ItemStack stack = user.getHeldItem(user.getActiveHand());
				if ((user.isHandActive() && stack != null && stack.getItem() == ModItems.stopWatch)) {
					stopEntity();
				}
			}

			Vec3d look = user.getLookVec();
			float distance = 1F;
			double dx = user.posX + (look.xCoord * distance);
			double dy = user.posY + user.getEyeHeight() - 1;
			double dz = user.posZ + (look.zCoord * distance);
			setPosition(dx, dy, dz);

			if (!worldObj.isRemote) {
				List<Entity> list = worldObj.getEntitiesWithinAABBExcludingEntity(user, user.getEntityBoundingBox().expandXyz(40));
				if (!list.isEmpty()) {
					list.forEach(this::setIgnoredPlayers);
					list.forEach(this::inRange);
				}
			}
		} else {
			stopEntity();
		}
		if(ticksExisted % 8 == 0) {
			worldObj.playSound(user, new BlockPos(posX + 0.5D, posY + 0.5D, posZ + 0.5D),
					SoundEvents.BLOCK_METAL_PRESSPLATE_CLICK_OFF, SoundCategory.NEUTRAL, 1.0F, 1.0F + 0.8F);
		}
	}

	//Gets the player other Watches have and adds them to a list of UUID.
	private void setIgnoredPlayers(Entity entity){
		if(entity instanceof EntityStopWatch){//If the entity is an instance of EntityStopWatch
			UUID id = ((EntityStopWatch)entity).getPlayer().getUniqueID(); //Get the UUID of the player the Watch contains
			if(!players.contains(id)) {// Check the player list does not contain the UUID
				players.add(id);//Add the UUID
			}
		}
	}

	private void inRange(Entity entity) {
		if (entity instanceof EntityStopWatch
				|| entity instanceof  EntityCameraSquare
				|| entity instanceof EntityMagicCircle
				|| entity instanceof EntityGrimoireSpell) {
			return;
		}
		if(!worldObj.isRemote) {
			if (entity instanceof EntityPlayerMP) {
				if (!players.isEmpty() && players.contains(entity.getUniqueID())) { //If the player is in the list, it wont be affected
					return;
				}
			}
			if (!dataEntities.containsKey(entity.getUniqueID())) {
				double x = entity.motionX;
				double y = entity.motionY;
				double z = entity.motionZ;
				dataEntities.put(entity.getUniqueID(), new double[]{x, y, z});
			}
		}
		if (entity.ticksExisted >= 2) {
			entity.setPosition(entity.prevPosX, entity.prevPosY, entity.prevPosZ);
			entity.rotationYaw = entity.prevRotationYaw;
			entity.rotationPitch = entity.prevRotationPitch;
			entity.motionX = 0;

			if (!entity.onGround) {
				entity.motionY = 0;
			}
			entity.motionZ = 0;

			entity.setAir(0);
			entity.ticksExisted--;
			entity.fallDistance = 0;
			if(entity instanceof EntityThrowable){
				++((EntityThrowable)entity).throwableShake;
			} else if(entity instanceof EntityArrow){
				++((EntityArrow)entity).arrowShake;
			}
			if (entity instanceof EntityLivingBase) {
				EntityLivingBase living = (EntityLivingBase) entity;
				living.rotationYawHead = living.prevRotationYawHead;
				if (living instanceof EntityCreeper) {
					EntityCreeper entityCreeper = (EntityCreeper) living;
					entityCreeper.setCreeperState(-1);
				} else if (living instanceof EntityGhast) {
					EntityGhast entityGhast = (EntityGhast) living;
					entityGhast.setAttacking(false);
				}
				if (living instanceof EntityTameable) {
					living.motionY -= 0.000001D;
				}
				if (living instanceof EntityPlayerMP) {
					EntityPlayerMP player = (EntityPlayerMP) living;
					player.setPositionAndRotation(player.prevPosX, player.prevPosY, player.prevPosZ, player.rotationYaw, player.rotationPitch);
				}
			}
		}
	}

	private void stopEntity() {
		if(!worldObj.isRemote) {
			if (user != null) {
				List<Entity> list = worldObj.getEntitiesWithinAABBExcludingEntity(user, user.getEntityBoundingBox().expandXyz(40));
				if (!list.isEmpty()) {
					list.stream().filter(entity -> dataEntities.containsKey(entity.getUniqueID())).forEach(entity -> {
						double[] motion = dataEntities.get(entity.getUniqueID());
						entity.motionX = motion[0];
						entity.motionY = motion[1];
						entity.motionZ = motion[2];
					});
				}
				if (user.capabilities.isCreativeMode) {
					setDead();
					return;
				}
				if (!user.inventory.addItemStackToInventory(new ItemStack(ModItems.stopWatch, 1))) {
					user.dropItem(ModItems.stopWatch, 1);
				}
			} else {
				dropItem(ModItems.stopWatch, 1);
			}
			setDead();
		}
	}

	public EntityPlayer getPlayer(){
		return user;
	}

	public int getTicksAlive(){
		return ticksExisted;
	}

	@Override
	protected void entityInit() {

	}

	@Override
	public boolean canBePushed() {
		return false;
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {

	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {

	}
}