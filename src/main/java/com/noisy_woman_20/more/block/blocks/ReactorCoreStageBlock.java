package com.noisy_woman_20.more.block.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.Instrument;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.StringIdentifiable;

public class ReactorCoreStageBlock extends Block {
	public enum ReactorCoreBlockState implements StringIdentifiable {
		INACTIVE("inactive"),
		ACTIVATION("activation"),
		EXTINGUISHED("extinguished");

		private final String STATE;

		ReactorCoreBlockState(String state) {
			this.STATE = state;
		}

		@Override
		public String asString() {
			return this.STATE;
		}
	}

	public static final EnumProperty<ReactorCoreBlockState> STATE = EnumProperty.of("state", ReactorCoreBlockState.class);

	public ReactorCoreStageBlock() {
		super(
			FabricBlockSettings
			.create()
			.strength(3, 6)
			.mapColor(MapColor.BLUE)
			.instrument(Instrument.BASEDRUM)
			.requiresTool()
		);
		setDefaultState(getDefaultState().with(STATE, ReactorCoreBlockState.INACTIVE));
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(STATE);
	}
}
