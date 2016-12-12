package arekkuusu.grimoireofalice.client.fx;

import net.minecraft.util.IStringSerializable;

import java.util.Locale;

public enum ParticleFX implements IStringSerializable {
	SHINMYOUMARU_SPARKLE,
	RED_MIST,
	NEEDLE_SWING,
	RED_GAS,
	NETHER_FIRE;

	@Override
	public String getName() {
		return name().toLowerCase(Locale.ROOT);
	}
}