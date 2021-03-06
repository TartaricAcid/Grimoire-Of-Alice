/*
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.event;

import arekkuusu.grimoireofalice.common.core.capability.IMalletCapability;
import arekkuusu.grimoireofalice.common.core.capability.MalletProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.lang.reflect.Method;

public class MalletServerEvent {

	private static final String SET_SIZE = "setSize";
	private static Method setSizeMethod;

	static {
		try {
			setSizeMethod = Entity.class.getDeclaredMethod(SET_SIZE, float.class, float.class);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("ConstantConditions")
	@SubscribeEvent
	public void onLivingUpdate(TickEvent.PlayerTickEvent event) {
		EntityPlayer entity = event.player;
		if (entity.hasCapability(MalletProvider.MALLET_CAPABILITY, null)) {
			IMalletCapability capability = entity.getCapability(MalletProvider.MALLET_CAPABILITY, null);
			if (capability.doAnimation()) {
				if (capability.isSmall() && entity.eyeHeight > 0) {
					entity.eyeHeight -= 0.032F;
				} else
				if (!capability.isSmall() && entity.eyeHeight < 1.68) {
					entity.eyeHeight += 0.032F;
				}
				else {
					capability.doAnimation(false);
				}
			} else
			if(capability.isSmall() && setSizeMethod != null) {
				try {
					entity.eyeHeight = 0.2F;
					setSizeMethod.setAccessible(true);
					setSizeMethod.invoke(entity, 0.3F, 0.2F);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@SuppressWarnings("ConstantConditions")
	@SubscribeEvent
	public void onPlayerClone(net.minecraftforge.event.entity.player.PlayerEvent.Clone event) {
		EntityPlayer oldPlayer = event.getOriginal();
		EntityPlayer newPlayer = event.getEntityPlayer();

		if (event.isWasDeath() && oldPlayer.hasCapability(MalletProvider.MALLET_CAPABILITY, null) && newPlayer.hasCapability(MalletProvider.MALLET_CAPABILITY, null)) {
			IMalletCapability oldCap = oldPlayer.getCapability(MalletProvider.MALLET_CAPABILITY, null);
			IMalletCapability newCap = oldPlayer.getCapability(MalletProvider.MALLET_CAPABILITY, null);
			newCap.doAnimation(oldCap.doAnimation());
			newCap.setScaled(oldCap.getScaled());
			newCap.setSmall(oldCap.isSmall());
		}
	}
}
