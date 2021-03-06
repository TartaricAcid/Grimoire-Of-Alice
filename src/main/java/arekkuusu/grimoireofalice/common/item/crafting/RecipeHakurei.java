/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item.crafting;

import arekkuusu.grimoireofalice.api.recipes.AltarRecipe;
import arekkuusu.grimoireofalice.common.item.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

public class RecipeHakurei extends AltarRecipe {

	public RecipeHakurei() {
		super(new ItemStack(ModItems.HAKUREI_GOHEI), "stickWood", "stickWood", ModItems.SHIMENAWA_ROPE, ModItems.SHIMENAWA_ROPE, ModItems.SHIMENAWA_ROPE, ModItems.SHIMENAWA_ROPE, ModItems.SHIMENAWA_ROPE, ModItems.SHIMENAWA_ROPE);
	}

	@Override
	public boolean checkRecipe(List<ItemStack> usedItems, World world) {
		return world.isDaytime() && 0 == world.provider.getMoonPhase(world.getWorldTime()) && super.checkRecipe(usedItems, world);
	}
}
