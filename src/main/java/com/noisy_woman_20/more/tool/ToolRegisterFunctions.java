package com.noisy_woman_20.more.tool;

import com.mojang.serialization.Codec;
import com.noisy_woman_20.more.More;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.item.Item;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.structure.Structure;
import net.minecraft.world.gen.structure.StructureType;

public final class ToolRegisterFunctions<T> {
	public static Item registerItem(String id, Item item) {
		return Registry.register(Registries.ITEM, Identifier.of(More.MOD_ID, id), item);
	}

	public static Block registerBlock(String id, Block block) {
		return Registry.register(Registries.BLOCK, Identifier.of(More.MOD_ID, id), block);
	}

	public static <T extends BlockEntity> BlockEntityType<T> registerBlockEntity(
		String id,
		FabricBlockEntityTypeBuilder.Factory<T> blockEntity,
		Block block
	) {
		return Registry.register(
			Registries.BLOCK_ENTITY_TYPE,
			new Identifier(More.MOD_ID, id),
			FabricBlockEntityTypeBuilder.create(blockEntity, block).build()
		);
	}

	public static Enchantment registerEnchantment(String id, Enchantment enchantment) {
		Identifier identifier = new Identifier(More.MOD_ID, id);
		return Registry.register(Registries.ENCHANTMENT, identifier, enchantment);
	}

	public static SoundEvent registerSoundEvent(String id) {
		Identifier identifier = new Identifier(More.MOD_ID, id);
		return Registry.register(Registries.SOUND_EVENT, identifier, SoundEvent.of(identifier));
	}

	public static RegistryKey<DamageType> registerDamageType(String id) {
		return RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of(More.MOD_ID, id));
	}

	public static <T extends Recipe<?>> void registerRecipeSerializer(RecipeSerializer<T> serializer, String id) {
		Identifier identifier = new Identifier(More.MOD_ID, id);
		Registry.register( Registries.RECIPE_SERIALIZER, identifier, serializer);
	}

	public static <T extends Structure> StructureType<T> registerStructureType(Codec<T> codec, String id) {
		Identifier identifier = new Identifier(More.MOD_ID, id);
		StructureType<T> structureType = () -> codec;
		return Registry.register(Registries.STRUCTURE_TYPE, identifier, structureType);
	}
}
