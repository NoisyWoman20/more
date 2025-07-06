package com.noisy_woman_20.more.recipe_serializer.recipe_serializers;

import com.google.gson.JsonObject;
import com.mojang.serialization.JsonOps;
import com.noisy_woman_20.more.recipe.TotemOfDyingRecipe;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;

public class TotemOfDyingRecipeSerializer extends AbstractRecipeSerializer<TotemOfDyingRecipe> {
	public static final TotemOfDyingRecipeSerializer INSTANCE = new TotemOfDyingRecipeSerializer();
	public static final String ID = "totem_of_dying_crafting";

	public TotemOfDyingRecipeSerializer() {
		super("totem_of_dying_crafting");
	}

	@Override
	public TotemOfDyingRecipe read(Identifier id, JsonObject json) {
		CraftingRecipeCategory category = CraftingRecipeCategory.CODEC
			.parse(JsonOps.INSTANCE, JsonHelper.getElement(json, "category"))
			.result()
			.orElse(CraftingRecipeCategory.EQUIPMENT);
		return new TotemOfDyingRecipe(id, category);
	}

	@Override
	public TotemOfDyingRecipe read(Identifier id, PacketByteBuf buf) {
		CraftingRecipeCategory category = buf.readEnumConstant(CraftingRecipeCategory.class);
		return new TotemOfDyingRecipe(id, category);
	}

	@Override
	public void write(PacketByteBuf buf, TotemOfDyingRecipe recipe) {
		buf.writeEnumConstant(recipe.getCategory());
	}
}
