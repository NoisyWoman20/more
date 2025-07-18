package com.noisy_woman_20.more.recipe_serializer;

import com.noisy_woman_20.more.More;
import com.noisy_woman_20.more.recipe.TotemOfDyingRecipe;
import com.noisy_woman_20.more.recipe_serializer.recipe_serializers.TotemOfDyingRecipeSerializer;
import com.noisy_woman_20.more.tool.ToolRegisterFunctions;

import net.minecraft.recipe.RecipeSerializer;

public class ModRecipeSerializers {
	public static void registerModRecipeSerializers() {    // 配方序列化器注册
		ToolRegisterFunctions.registerRecipeSerializer(TOTEM_OF_DYING, TotemOfDyingRecipeSerializer.ID);
		More.LOGGER.info("Registered mod recipe serializers successfully");
	}

	public static final RecipeSerializer<TotemOfDyingRecipe> TOTEM_OF_DYING = TotemOfDyingRecipeSerializer.INSTANCE;
}
