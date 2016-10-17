/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

//TODO: Replace with SubEntity and Form once DanmakuCore can be used
public class EntityAnimalShot extends EntityThrowable {

	public EntityAnimalShot(World worldIn) {
        super(worldIn);
    }

    public EntityAnimalShot(World worldIn, EntityLivingBase throwerIn) {
        super(worldIn, throwerIn);
    }

    public EntityAnimalShot(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		doEffects();
		if(ticksInAir == 10 || ticksInAir == 20 || ticksInAir == 30 || ticksInAir == 40){
			Vec3d vec = getVectorForRotation(-rotationPitch, -rotationYaw).rotatePitch(45F).rotateYaw(45F); //These needs to be negative for some reason
			setThrowableHeading(vec.xCoord, vec.yCoord, vec.zCoord, 0.3F, 0.0F);
		}
		if(ticksInAir >= 50){
			if(!worldObj.isRemote) {
	            this.setDead();
	        }
		}
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		for (int j = 0; j < 8; ++j) {
			//FIXME: Barely visible
            this.worldObj.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX, this.posY, this.posZ, 0.0D, 0.5D, 0.0D);
        }
		if(!worldObj.isRemote) {
            this.setDead();
        }
	}
	
	@Override
	public float getGravityVelocity() {
        return 0.02F;
    }
	
	//Rotate Figure or Make Animation (Like in Ten Desires)
	private void doEffects(){
	}

}
