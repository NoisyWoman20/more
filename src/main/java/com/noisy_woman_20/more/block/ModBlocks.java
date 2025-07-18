package com.noisy_woman_20.more.block;

import com.noisy_woman_20.more.More;
import com.noisy_woman_20.more.block.blocks.GrassBlock;
import com.noisy_woman_20.more.block.blocks.GlowingObsidianBlock;
import com.noisy_woman_20.more.block.blocks.ReactorCoreStageBlock;
import com.noisy_woman_20.more.tool.ToolRegisterFunctions;

import net.minecraft.block.Block;

public class ModBlocks {
	public static void registerModBlocks() {    // 方块注册
		More.LOGGER.info("Registered mod blocks successfully");
	}

	public static final Block GRASS_BLOCK = ToolRegisterFunctions.registerBlock(
		"grass_block",
		new GrassBlock()
	);

	public static final Block GLOWING_OBSIDIAN = ToolRegisterFunctions.registerBlock(
		"glowing_obsidian",
		new GlowingObsidianBlock()
	);

	public static final Block REACTOR_CORE_STAGE = ToolRegisterFunctions.registerBlock(
		"reactor_core_stage",
		new ReactorCoreStageBlock()
	);
}
