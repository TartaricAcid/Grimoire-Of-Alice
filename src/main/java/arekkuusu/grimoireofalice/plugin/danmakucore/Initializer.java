/*
 * This class was created by <Katrix>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.plugin.danmakucore;

import arekkuusu.grimoireofalice.lib.LibDanmakuVariantName;
import arekkuusu.grimoireofalice.plugin.danmakucore.form.FormLeaf;
import arekkuusu.grimoireofalice.plugin.danmakucore.form.FormUfo;
import arekkuusu.grimoireofalice.plugin.danmakucore.form.FormWind;
import arekkuusu.grimoireofalice.plugin.danmakucore.subentity.SubEntityLeaf;
import arekkuusu.grimoireofalice.plugin.danmakucore.subentity.SubEntityNote;
import arekkuusu.grimoireofalice.plugin.danmakucore.subentity.SubEntityUfo;
import arekkuusu.grimoireofalice.plugin.danmakucore.subentity.SubEntityWind;
import net.katsstuff.danmakucore.data.MovementData;
import net.katsstuff.danmakucore.data.Vector3;
import net.katsstuff.danmakucore.entity.danmaku.DanmakuVariant;
import net.katsstuff.danmakucore.entity.danmaku.form.Form;
import net.katsstuff.danmakucore.entity.danmaku.subentity.SubEntityType;
import net.katsstuff.danmakucore.impl.DanmakuVariant.DanmakuVariantCoreGeneric;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class Initializer {

	@SubscribeEvent
	public static void registerForms(RegistryEvent.Register<Form> event) {
		event.getRegistry().registerAll(
				new FormWind(),
				new FormUfo(),
				new FormLeaf()
		);
	}

	@SubscribeEvent
	public static void registerSubEntities(RegistryEvent.Register<SubEntityType> event) {
		event.getRegistry().registerAll(
				new SubEntityWind(),
				new SubEntityNote(),
				new SubEntityUfo(),
				new SubEntityLeaf()
		);
	}

	@SubscribeEvent
	public static void registerVariants(RegistryEvent.Register<DanmakuVariant> event) {
		event.getRegistry().registerAll(
				new DanmakuVariantCoreGeneric(LibDanmakuVariantName.UFO, () -> LibGOAShotData.UFO,
						new MovementData(0.4D, 0.4D, 0D, Vector3.gravity(-0.02D))),
				new DanmakuVariantCoreGeneric(LibDanmakuVariantName.WIND, () -> LibGOAShotData.WIND, 0.4D),
				new DanmakuVariantCoreGeneric(LibDanmakuVariantName.LEAF, () -> LibGOAShotData.LEAF,
						new MovementData(0.4D, 0.4D, 0D, Vector3.gravity(-0.04D)))
		);
	}
}