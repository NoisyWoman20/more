package com.noisy_woman_20.more.recipe_serializer.recipe_serializers;

import com.google.gson.JsonObject;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;

public abstract class AbstractRecipeSerializer<T extends Recipe<?>> implements RecipeSerializer<T> {
	protected final String ID;
	protected final RecipeSerializer<T> INSTANCE;

	public AbstractRecipeSerializer(String id) {
		ID = id;
		INSTANCE = this;
	}

	public String getId() {
		return ID;
	}

	public RecipeSerializer<T> getInstance() {
		return INSTANCE;
	}

	@Override
	public abstract T read(Identifier id, JsonObject json);

	@Override
	public abstract T read(Identifier id, PacketByteBuf buf);

	@Override
	public abstract void write(PacketByteBuf buf, T recipe);
}
