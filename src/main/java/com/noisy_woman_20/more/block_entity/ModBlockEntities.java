package com.noisy_woman_20.more.block_entity;

import com.noisy_woman_20.more.More;
import com.noisy_woman_20.more.block.ModBlocks;
import com.noisy_woman_20.more.block_entity.block_entities.ReactorCoreStageBlockEntity;
import com.noisy_woman_20.more.tool.ToolRegisterFunctions;

import net.minecraft.block.entity.BlockEntityType;

public class ModBlockEntities {
	public static void registerModBlockEntities() {    // 方块实体注册
		More.LOGGER.info("Registered mod block entities successfully");
	}
	
	public static BlockEntityType<ReactorCoreStageBlockEntity> REACTOR_CORE_STAGE = ToolRegisterFunctions.registerBlockEntity(
		"reactor_core_stage",
		(pos, state) -> new ReactorCoreStageBlockEntity(pos, state),
		ModBlocks.REACTOR_CORE_STAGE
	);
}
