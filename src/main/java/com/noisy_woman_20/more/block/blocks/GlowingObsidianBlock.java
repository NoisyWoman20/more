package com.noisy_woman_20.more.block.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.Instrument;

public class GlowingObsidianBlock extends Block {
	public GlowingObsidianBlock() {
		super(
			FabricBlockSettings
			.create()
			.strength(10f, 1200f)
			.luminance(12)
			.mapColor(MapColor.DARK_RED)
			.instrument(Instrument.BASEDRUM)
			.requiresTool()
		);
	}
}
