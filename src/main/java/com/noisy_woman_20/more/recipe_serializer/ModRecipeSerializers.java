package com.noisy_woman_20.more.recipe_serializer;

import com.noisy_woman_20.more.More;
import com.noisy_woman_20.more.recipe.TotemOfDyingRecipe;
import com.noisy_woman_20.more.recipe_serializer.recipe_serializers.TotemOfDyingRecipeSerializer;

import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipeSerializers {
	public static void registerModRecipeSerializers() {    // 配方序列化器注册
		registerRecipeSerializer(TOTEM_OF_DYING, TotemOfDyingRecipeSerializer.ID);
		More.LOGGER.info("Registered mod recipe serializer successfully");
	}

	private static <T extends Recipe<?>> void registerRecipeSerializer(RecipeSerializer<T> serializer, String Id) {
		Identifier identifier = new Identifier(More.MOD_ID, Id);
		Registry.register( Registries.RECIPE_SERIALIZER, identifier, serializer);
	}

	public static final RecipeSerializer<TotemOfDyingRecipe> TOTEM_OF_DYING = TotemOfDyingRecipeSerializer.INSTANCE;
}
