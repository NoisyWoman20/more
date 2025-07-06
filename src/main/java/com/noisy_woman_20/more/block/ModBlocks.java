package com.noisy_woman_20.more.block;

import com.noisy_woman_20.more.More;
import com.noisy_woman_20.more.block.blocks.GrassBlock;
import com.noisy_woman_20.more.block.blocks.GlowingObsidianBlock;
import com.noisy_woman_20.more.block.blocks.ReactorCoreStageBlock;

import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
	public static void registerModBlocks() {    // 方块注册
		More.LOGGER.info("Registered mod blocks successfully");
	}

	private static Block registerBlock(String id, Block block) {
		return Registry.register(Registries.BLOCK, Identifier.of(More.MOD_ID, id), block);
	}

	public static final Block GRASS_BLOCK = registerBlock(
		"grass_block",
		new GrassBlock()
	);

	public static final Block GLOWING_OBSIDIAN = registerBlock(
		"glowing_obsidian",
		new GlowingObsidianBlock()
	);

	public static final Block REACTOR_CORE_STAGE = registerBlock(
		"reactor_core_stage",
		new ReactorCoreStageBlock()
	);
}
