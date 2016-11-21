package arekkuusu.grimoireofalice.jei;

import arekkuusu.grimoireofalice.block.ModBlocks;
import arekkuusu.grimoireofalice.item.ModItems;
import arekkuusu.grimoireofalice.item.crafting.RecipeAltar;
import arekkuusu.grimoireofalice.lib.LibJEI;
import mezz.jei.api.BlankModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import net.minecraft.item.ItemStack;

@JEIPlugin
public class GoAJEIPlugin extends BlankModPlugin {

	@Override
	public void register(IModRegistry registry) {
		registry.addRecipeCategories(new AltarRecipeItemsCategory(registry.getJeiHelpers().getGuiHelper()));
		registry.addRecipeHandlers(new AltarRecipeItemsHandler(registry.getJeiHelpers().getStackHelper()));
		registry.addRecipeCategoryCraftingItem(new ItemStack(ModBlocks.ALTAR), LibJEI.ALTER_CATEGORY_UID);
		registry.addRecipes(RecipeAltar.getRecipes());

		addDescriptions(registry);
	}

	private void addDescriptions(IModRegistry registry) {
		final String LINE = "--------------------------";
		registry.addDescription(new ItemStack(ModItems.BUDAH_BOUL), "grimoire.jei.descriptions.budah_boul", LINE
				, "grimoire.jei.descriptions_body.budah_boul");

		registry.addDescription(new ItemStack(ModItems.NAZRIN_STICK_ITEM), "grimoire.jei.descriptions.nazrin_stick", LINE
				, "grimoire.jei.descriptions_body.nazrin_stick");

		registry.addDescription(new ItemStack(ModItems.AMENONUHOKO), "grimoire.jei.descriptions.amenonuhoko", LINE
				, "grimoire.jei.descriptions_body.amenonuhoko");

		registry.addDescription(new ItemStack(ModItems.AYA_CAMERA), "grimoire.jei.descriptions.aya_camera", LINE
				, "grimoire.jei.descriptions_body.aya_camera");

		registry.addDescription(new ItemStack(ModItems.BLOOD_ORB), "grimoire.jei.descriptions.blood_orb", LINE
				, "grimoire.jei.descriptions_body.blood_orb");

		registry.addDescription(new ItemStack(ModItems.BYAKUREN_AURA), "grimoire.jei.descriptions.byakuren_aura");

		registry.addDescription(new ItemStack(ModItems.CATTAIL_PLANT), "grimoire.jei.descriptions.cattail_plant");

		registry.addDescription(new ItemStack(ModItems.CHERRY), "grimoire.jei.descriptions.cherry");

		registry.addDescription(new ItemStack(ModItems.COWRIE_SHELL), "grimoire.jei.descriptions.cowrie_shell", LINE
				, "grimoire.jei.descriptions_body.cowrie_shell");

		registry.addDescription(new ItemStack(ModItems.CREST_OF_YGGDRASILL), "grimoire.jei.descriptions.crest_of_yggdrassil");

		registry.addDescription(new ItemStack(ModItems.CURSED_DECOY_DOLL), "grimoire.jei.descriptions.cursed_decoy_doll");

		registry.addDescription(new ItemStack(ModItems.DEATH_SCYTHE), "grimoire.jei.descriptions.death_scythe", LINE
				, "grimoire.jei.descriptions_body.death_scythe");

		registry.addDescription(new ItemStack(ModItems.DRAGON_JEWEL), "grimoire.jei.descriptions.dragon_jewel", LINE
				, "grimoire.jei.descriptions_body.dragon_jewel");

		registry.addDescription(new ItemStack(ModItems.ELLY_SCYTHE), "grimoire.jei.descriptions.elly_scythe", LINE
				, "grimoire.jei.descriptions_body.elly_scythe");

		registry.addDescription(new ItemStack(ModItems.FAITH), "grimoire.jei.descriptions.faith");

		registry.addDescription(new ItemStack(ModItems.FAKE_MIRACLE_MALLET), "grimoire.jei.descriptions.fake_miracle_mallet");

		registry.addDescription(new ItemStack(ModItems.FIRE_ROBE), "grimoire.jei.descriptions.fire_robe", LINE
				, "grimoire.jei.descriptions.fire_robe_body");

		registry.addDescription(new ItemStack(ModItems.FOLDING_UMBRELLA), "grimoire.jei.descriptions.gap_folding_umbrella");

		registry.addDescription(new ItemStack(ModItems.FOX_MASK), "grimoire.jei.descriptions.fox_mask");

		registry.addDescription(new ItemStack(ModItems.FUKU_NO_KAMI_MASK), "grimoire.jei.descriptions.fuku_no_kami_mask");

		registry.addDescription(new ItemStack(ModItems.FULL_POWER), "grimoire.jei.descriptions.full_power");

		registry.addDescription(new ItemStack(ModItems.GHASTLY_SEND_OFF_LANTERN), "grimoire.jei.descriptions.ghastly_send_off_lantern");

		registry.addDescription(new ItemStack(ModItems.GHOST_DIPPER), "grimoire.jei.descriptions.ghost_dipper", LINE
				, "grimoire.jei.descriptions_body.ghost_dipper");

		registry.addDescription(new ItemStack(ModItems.GRILLED_LAMPREY), "grimoire.jei.descriptions.grilled_lamprey");

		registry.addDescription(new ItemStack(ModItems.GRIMOIRE_BOOK), "grimoire.jei.descriptions.grimoire_book");

		registry.addDescription(new ItemStack(ModItems.HAKUREI_GOHEI), "grimoire.jei.descriptions.hakurei_gohei", LINE
				, "grimoire.jei.descriptions.hakurei_gohei_passive"
				, "grimoire.jei.descriptions.hakurei_gohei_aura_manipulation"
				, "grimoire.jei.descriptions.hakurei_gohei_hakurei_yin_yang_orbs"
				, "grimoire.jei.descriptions.hakurei_gohei_exploding_barrier"
				, "grimoire.jei.descriptions.hakurei_gohei_motion_barrier"
				, "grimoire.jei.descriptions.hakurei_gohei_offensive");

		registry.addDescription(new ItemStack(ModItems.HANNYA_MASK), "grimoire.jei.descriptions.hannya_mask");

		registry.addDescription(new ItemStack(ModItems.HATATE_CAMERA), "grimoire.jei.descriptions.hatate_camera", LINE
				, "grimoire.jei.descriptions.hatate_camera_body");

		registry.addDescription(new ItemStack(ModItems.HEAVENLY_PEACH), "grimoire.jei.descriptions.heavenly_peach");

		registry.addDescription(new ItemStack(ModItems.HIHIIROKANE), "grimoire.jei.descriptions.hihiirokane");

		registry.addDescription(new ItemStack(ModItems.HISOU), "grimoire.jei.descriptions.hisou", LINE
				, "grimoire.jei.descriptions.hisou_body");

		registry.addDescription(new ItemStack(ModItems.HOURAI_ELIXIR), "grimoire.jei.descriptions.hourai_elixir", LINE
				, "grimoire.jei.descriptions_body.hourai_elixir");

		registry.addDescription(new ItemStack(ModItems.HYOTTOKO_MASK), "grimoire.jei.descriptions.hyottoko_mask");

		registry.addDescription(new ItemStack(ModItems.IBARAKI_BOX_EMPTY), "grimoire.jei.descriptions.ibaraki_box");

		registry.addDescription(new ItemStack(ModItems.IBUKI_GOURD), "grimoire.jei.descriptions.ibuki_gourd");

		registry.addDescription(new ItemStack(ModItems.ICHIRIN_AURA), "grimoire.jei.descriptions.ichirin_aura");

		registry.addDescription(new ItemStack(ModItems.ICHIRIN_RING), "grimoire.jei.descriptions.ichirin_ring", LINE
				, "grimoire.jei.descriptions.ichirin_ring_body");

		registry.addDescription(new ItemStack(ModItems.IMPURE_ROCK), "grimoire.jei.descriptions.impure_rock");

		registry.addDescription(new ItemStack(ModItems.JEWELED_HOURAI), "grimoire.jei.descriptions.jeweled_hourai", LINE
				, "grimoire.jei.descriptions.jeweled_hourai_body");

		registry.addDescription(new ItemStack(ModItems.KANAKO_AURA), "grimoire.jei.descriptions.kanako_aura", LINE
				, "grimoire.jei.descriptions.kanako_aura_body");

		registry.addDescription(new ItemStack(ModItems.KANAKO_ONBASHIRA), "grimoire.jei.descriptions.kanako_onbashira");

		registry.addDescription(new ItemStack(ModItems.KAPPA_HAT), "grimoire.jei.descriptions.kappa_hat");

		registry.addDescription(new ItemStack(ModItems.KAPPAS_NOSTRUM), "grimoire.jei.descriptions.kappa_nostrum");

		registry.addDescription(new ItemStack(ModItems.KOKOROS_MASKS), "grimoire.jei.descriptions.kokoros_masks");

		registry.addDescription(new ItemStack(ModItems.KOOMOTE_MASK), "grimoire.jei.descriptions.koomote_mask");

		registry.addDescription(new ItemStack(ModItems.LAEVATEIN), "grimoire.jei.descriptions.laevatein", LINE
				, "grimoire.jei.descriptions.laevatein_body");

		registry.addDescription(new ItemStack(ModItems.LEAF), "grimoire.jei.descriptions.leaf");

		registry.addDescription(new ItemStack(ModItems.LUNASA_VIOLIN), "grimoire.jei.descriptions.lunasa_violin");

		registry.addDescription(new ItemStack(ModItems.LYRICA_PIANO), "grimoire.jei.descriptions.lyrica_piano");

		registry.addDescription(new ItemStack(ModItems.MAPLE_LEAF_SHIELD), "grimoire.jei.descriptions.maple_leaf_shield");

		registry.addDescription(new ItemStack(ModItems.MARISA_HAT), "grimoire.jei.descriptions.marisa_hat");

		registry.addDescription(new ItemStack(ModItems.MASK), "grimoire.jei.descriptions.mask");

		registry.addDescription(new ItemStack(ModItems.MASK_OF_HOPE), "grimoire.jei.descriptions.mask_of_hope");

		registry.addDescription(new ItemStack(ModItems.MERLIN_TRUMPET), "grimoire.jei.descriptions.merlin_trumpet");

		registry.addDescription(new ItemStack(ModItems.MIKO_CAPE), "grimoire.jei.descriptions.miko_cape");

		registry.addDescription(new ItemStack(ModItems.MIKO_STICK), "grimoire.jei.descriptions.miko_stick");

		registry.addDescription(new ItemStack(ModItems.MIRACLE_MALLET), "grimoire.jei.descriptions.miracle_mallet", LINE
				, "grimoire.jei.descriptions.miracle_mallet_body");

		registry.addDescription(new ItemStack(ModItems.MOCHI_HAMMER), "grimoire.jei.descriptions.mochi_hammer");

		registry.addDescription(new ItemStack(ModItems.MOKOU_AURA), "grimoire.jei.descriptions.mokou_aura");

		registry.addDescription(new ItemStack(ModItems.MOMIJIS_SCIMITAR_SWORD), "grimoire.jei.descriptions.momijis_scimitar_sword");

		registry.addDescription(new ItemStack(ModItems.MONKEY_MASK), "grimoire.jei.descriptions.monkey_mask");

		registry.addDescription(new ItemStack(ModItems.MORTAR_AND_PESTLE), "grimoire.jei.descriptions.mortal_n_pestle");

		registry.addDescription(new ItemStack(ModItems.NAZRIN_PENDULUM), "grimoire.jei.descriptions.nazrin_pendulum");

		registry.addDescription(new ItemStack(ModItems.NEEDLE), "grimoire.jei.descriptions.needle");

		registry.addDescription(new ItemStack(ModItems.NIMBLE_FABRIC), "grimoire.jei.descriptions.nimble_fabric");

		registry.addDescription(new ItemStack(ModItems.NUE_TRIDENT), "grimoire.jei.descriptions.nue_trident", LINE
				, "grimoire.jei.descriptions.nue_trident_body");

		registry.addDescription(new ItemStack(ModItems.ORB_ELIXIR), "grimoire.jei.descriptions.orb_elixir");

		registry.addDescription(new ItemStack(ModItems.PATCHY_BOOK), "grimoire.jei.descriptions.patchy_book");

		registry.addDescription(new ItemStack(ModItems.POPSICLE_STICK), "grimoire.jei.descriptions.popsicle_stick");

		registry.addDescription(new ItemStack(ModItems.POUCH), "grimoire.jei.descriptions.pouch");

		registry.addDescription(new ItemStack(ModItems.RAIDEN_MASK), "grimoire.jei.descriptions.raiden_mask");

		registry.addDescription(new ItemStack(ModItems.ROD_REMORSE), "grimoire.jei.descriptions.rod_of_remorse");

		registry.addDescription(new ItemStack(ModItems.ROUKANKEN), "grimoire.jei.descriptions.roukanken");

		registry.addDescription(new ItemStack(ModItems.RUMIA_SWORD), "grimoire.jei.descriptions.rumia_sword", LINE
				, "grimoire.jei.descriptions.rumia_sword_body");

		registry.addDescription(new ItemStack(ModItems.SACRED_TOYOSATOMIMI), "grimoire.jei.descriptions.sacred_toyosatomimi_sword");

		registry.addDescription(new ItemStack(ModItems.SANAE_GOHEI), "grimoire.jei.descriptions.sanae_gohei");

		registry.addDescription(new ItemStack(ModItems.SARIEL_WAND), "grimoire.jei.descriptions.sariel_wand", LINE
				, "grimoire.jei.descriptions.sariel_wand_body");

		registry.addDescription(new ItemStack(ModItems.SHICHI_SEIKEN), "grimoire.jei.descriptions.shichi_seiken", LINE
				, "grimoire.jei.descriptions.shichi_seiken_body");

		registry.addDescription(new ItemStack(ModItems.SHIMENAWA_ROPE), "grimoire.jei.descriptions.shimenawa_rope");

		registry.addDescription(new ItemStack(ModItems.SHOU_LAMP), "grimoire.jei.descriptions.shou_lamp");

		registry.addDescription(new ItemStack(ModItems.SHROOM_POWDER), "grimoire.jei.descriptions.shroom_powder");

		registry.addDescription(new ItemStack(ModItems.SKULL), "grimoire.jei.descriptions.skull");

		registry.addDescription(new ItemStack(ModItems.SOLDIFIED_PAPER), "grimoire.jei.descriptions.soldifier_paper");

		registry.addDescription(new ItemStack(ModItems.STAR), "grimoire.jei.descriptions.star");

		registry.addDescription(new ItemStack(ModItems.STOP_WATCH), "grimoire.jei.descriptions.stop_watch");

		registry.addDescription(new ItemStack(ModItems.SUBSTITUTE_JIZO), "grimoire.jei.descriptions.substitute_jizo");

		registry.addDescription(new ItemStack(ModItems.SUWAKO_HAT), "grimoire.jei.descriptions.suwako_hat", LINE
				, "grimoire.jei.descriptions.suwako_hat_body");

		registry.addDescription(new ItemStack(ModItems.SWORD_OF_KUSANAGI), "grimoire.jei.descriptions.sword_of_kusanagi", LINE
				, "grimoire.jei.descriptions.sword_of_kusanagi_body");

		registry.addDescription(new ItemStack(ModItems.SYRINGE), "grimoire.jei.descriptions.syringe");

		registry.addDescription(new ItemStack(ModItems.TAMAHAGANE_STEEL), "grimoire.jei.descriptions.tamahagane_steel");

		registry.addDescription(new ItemStack(ModItems.TENGU_FAN), "grimoire.jei.descriptions.tengu_fan");

		registry.addDescription(new ItemStack(ModItems.THIRD_EYE), "grimoire.jei.descriptions.third_eye", LINE
				, "grimoire.jei.descriptions.third_eye_body");

		registry.addDescription(new ItemStack(ModItems.TIME_ORB), "grimoire.jei.descriptions.time_orb");

		registry.addDescription(new ItemStack(ModItems.TOYOSATOMIMI_AURA), "grimoire.jei.descriptions.toyosatomimi_aura");

		registry.addDescription(new ItemStack(ModItems.UBA_MASK), "grimoire.jei.descriptions.uba_mask");

		registry.addDescription(new ItemStack(ModItems.UFO_BLUE), "grimoire.jei.descriptions.ufo_blue");

		registry.addDescription(new ItemStack(ModItems.UFO_GREEN), "grimoire.jei.descriptions.ufo_green");

		registry.addDescription(new ItemStack(ModItems.UFO_RED), "grimoire.jei.descriptions.ufo_red");

		registry.addDescription(new ItemStack(ModItems.UFOS), "grimoire.jei.descriptions.ufos", LINE
				, "grimoire.jei.descriptions.ufos_body");

		registry.addDescription(new ItemStack(ModItems.UTSUHO_AURA), "grimoire.jei.descriptions.utsuho_aura");

		registry.addDescription(new ItemStack(ModItems.VOLATILE_STRING), "grimoire.jei.descriptions.volatile_string");

		registry.addDescription(new ItemStack(ModItems.WALL_PASSING_CHISEL), "grimoire.jei.descriptions.wall_passing_chisel");

		registry.addDescription(new ItemStack(ModItems.WATERMELON_BLADE), "grimoire.jei.descriptions.watermelon_blade");

		registry.addDescription(new ItemStack(ModItems.WATERMELON_SWORD), "grimoire.jei.descriptions.watermelon_sword");

		registry.addDescription(new ItemStack(ModItems.YOUKAI_BOOK), "grimoire.jei.descriptions.youkai_book");

		registry.addDescription(new ItemStack(ModBlocks.IMPURE_STONE), "grimoire.jei.descriptions.impure_stone");

		registry.addDescription(new ItemStack(ModBlocks.HOLY_STONE), "grimoire.jei.descriptions.holy_stone", LINE
				, "grimoire.jei.descriptions.holy_stone_gold_nugget"
				, "grimoire.jei.descriptions.holy_stone_gold_ingot"
				, "grimoire.jei.descriptions.holy_stone_iron_ingot"
				, "grimoire.jei.descriptions.holy_stone_blaze_powder"
				, "grimoire.jei.descriptions.holy_stone_speckled_melon"
				, "grimoire.jei.descriptions.holy_stone_diamond");

		registry.addDescription(new ItemStack(ModBlocks.ONBASHIRA), "grimoire.jei.descriptions.onbashira");
	}
}