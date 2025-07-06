package com.noisy_woman_20.more.block.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.sound.BlockSoundGroup;

public class GrassBlock extends Block {
	public GrassBlock() {
		super(
			FabricBlockSettings
			.create()
			.mapColor(MapColor.PALE_GREEN)
			.strength(0.6f)
			.sounds(BlockSoundGroup.GRASS)
		);
	}
}
