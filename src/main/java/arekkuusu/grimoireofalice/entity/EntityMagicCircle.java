package arekkuusu.grimoireofalice.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;

public class EntityMagicCircle extends Entity {

	private static final DataParameter<Float> SIZE = EntityDataManager.createKey(EntityMagicCircle.class, DataSerializers.FLOAT);
	private static final DataParameter<Integer> TIME = EntityDataManager.createKey(EntityMagicCircle.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> ANIMATION = EntityDataManager.createKey(EntityMagicCircle.class, DataSerializers.VARINT);
	private EntityLivingBase host;
	protected int last;
	
	public EntityMagicCircle(World worldIn, EntityLivingBase entityLiving, int end) {
		super(worldIn);
		setSize(0.5F, 0.5F);
		setEndTime(end);
		host = entityLiving; //This is mostly an experiment...
		posX = host.posX;
    	posY = host.posY + 0.1D;
    	posZ = host.posZ;
    	this.setPositionAndRotation(posX, posY, posZ, host.rotationYaw, host.rotationPitch);
	}

	@Override
    public void onUpdate() {
    	super.onUpdate();
    	if(!worldObj.isRemote || host == null ) {
    		setDead();
    		return;
    	}
    	
    	setAnimationCount(last);
    	
    	if(getEndTime() < ticksExisted && getEndTime() >= 0) {
    		if(!worldObj.isRemote) {
    			setDead();
    		}
    	}
    	if(getAnimationCount() < 5) {
    		setCircleSize(((float)getAnimationCount()) / 5.0F);
    	}
    	else {
    		float end2 = (float)getEndTime();
    		setCircleSize((end2 - (float)getAnimationCount()) / end2);
    	}
    	posX = host.posX;
        posY = host.posY + 0.1D;
    	posZ = host.posZ;
    	rotationYaw = host.rotationYawHead;
    	rotationPitch = host.rotationPitch;
    	setPosition(posX, posY, posZ);
    	
    	if(rotationYaw >  180F)rotationYaw -= 360F;
    	if(rotationYaw < -180F)rotationYaw += 360F;
    	if(rotationPitch >  180F)rotationPitch -= 360F;
    	if(rotationPitch < -180F)rotationPitch += 360F;

    	setRotation(rotationYaw, rotationPitch);
    	
    	if(ticksExisted > last) {
    		last = ticksExisted;
    	}
    }
	
	@Override
	protected void entityInit() {
		dataManager.register(SIZE, new Float(0));
		dataManager.register(TIME, new Integer(0));
		dataManager.register(ANIMATION, new Integer(0));
	}
	
	public void setCircleSize(float size) {
		dataManager.set(SIZE, Float.valueOf(size));
	}

	public float getCircleSize() {
		return dataManager.get(SIZE);
	}
	
	public void setEndTime(int time) {
		dataManager.set(TIME, Integer.valueOf(time));
	}

	public int getEndTime() {
		return dataManager.get(TIME);
	}
	
	public void setAnimationCount(int time) {
		dataManager.set(ANIMATION, Integer.valueOf(time));
	}
	
	public int getAnimationCount() {
		return dataManager.get(ANIMATION);
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {
		
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
		
	}

}