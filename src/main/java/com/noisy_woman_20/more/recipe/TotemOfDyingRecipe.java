package com.noisy_woman_20.more.recipe;

import com.noisy_woman_20.more.item.ModItems;
import com.noisy_woman_20.more.recipe_serializer.ModRecipeSerializers;

import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.CraftingRecipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class TotemOfDyingRecipe implements CraftingRecipe {
	private final Identifier id;
	private final CraftingRecipeCategory category;

	public TotemOfDyingRecipe(Identifier id, CraftingRecipeCategory category) {
		this.id = id;
		this.category = category;
	}

	@Override
	public boolean matches(RecipeInputInventory inventory, World world) {
		boolean hasTotem = false;
		boolean hasPotion = false;

		for (int i = 0; i < inventory.size(); i++) {
			ItemStack stack = inventory.getStack(i);
			if (stack.isEmpty()) {
				continue;
			}

			if (stack.isOf(Items.TOTEM_OF_UNDYING)) {
				if (hasTotem) {
					return false;
				}
				hasTotem = true;
			} else if (stack.isOf(Items.POTION)) {
				NbtCompound nbt = stack.getNbt();
				if (nbt != null && "minecraft:harming".equals(nbt.getString("Potion"))) {
					if (hasPotion) {
						return false;
					}
					hasPotion = true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}

		return (hasTotem && hasPotion);
	}

	@Override
	public ItemStack craft(RecipeInputInventory inventory, DynamicRegistryManager registryManager) {
		return getOutput(registryManager).copy();
	}

	@Override
	public boolean fits(int width, int height) {
		return (width * height >= 2);
	}

	@Override
	public ItemStack getOutput(DynamicRegistryManager registryManager) {
		return new ItemStack(ModItems.TOTEM_OF_DYING);
	}

	@Override
	public Identifier getId() {
		return id;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return ModRecipeSerializers.TOTEM_OF_DYING;
	}

	@Override
	public CraftingRecipeCategory getCategory() {
		return category;
	}

	@Override
	public RecipeType<?> getType() {
		return RecipeType.CRAFTING;
	}
}
