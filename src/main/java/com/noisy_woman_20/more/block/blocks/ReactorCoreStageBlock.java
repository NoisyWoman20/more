package com.noisy_woman_20.more.block.blocks;

import com.noisy_woman_20.more.block_entity.ModBlockEntities;
import com.noisy_woman_20.more.block_entity.block_entities.ReactorCoreStageBlockEntity;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.enums.Instrument;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ReactorCoreStageBlock extends BlockWithEntity {
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

	public static final BlockPos[] COBBLESTONE_POSITIONS = {
		new BlockPos(0, -1, -1),
		new BlockPos(-1, -1, 0),
		new BlockPos(0, -1, 0),
		new BlockPos(1, -1, 0),
		new BlockPos(0, -1, 1),
		new BlockPos(-1, 0, -1),
		new BlockPos(1, 0, -1),
		new BlockPos(-1, 0, 1),
		new BlockPos(1, 0, 1),
		new BlockPos(0, 1, -1),
		new BlockPos(-1, 1, 0),
		new BlockPos(0, 1, 0),
		new BlockPos(1, 1, 0),
		new BlockPos(0, 1, 1)
	};

	public static final BlockPos[] GOLD_BLOCK_POSITIONS = {
		new BlockPos(-1, -1, -1),
		new BlockPos(1, -1, -1),
		new BlockPos(-1, -1, 1),
		new BlockPos(1, -1, 1)
	};

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

	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return new ReactorCoreStageBlockEntity(pos, state);
	}

	@Override
	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.MODEL;
	}

	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		if (!player.getMainHandStack().isEmpty()) {
			return ActionResult.PASS;
		}

		if (world.isClient()) {
			return ActionResult.SUCCESS;
		}

		if (pos.getY() + 35 > world.getTopY()) {
			return ActionResult.CONSUME;
		}

		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity instanceof ReactorCoreStageBlockEntity reactorCoreStageBlockEntity) {
			boolean allValid = checkStructure(world, pos);

			if (
				allValid &&
				(!reactorCoreStageBlockEntity.getIsInitialized() && !reactorCoreStageBlockEntity.getHasFinished())
				) {
				reactorCoreStageBlockEntity.setIsInitialized(true);
				reactorCoreStageBlockEntity.markDirty();
				world.setBlockState(pos, state.with(STATE, ReactorCoreBlockState.ACTIVATION));
				return ActionResult.SUCCESS;
			} else {
				return ActionResult.CONSUME;
			}
		}

		return ActionResult.CONSUME;
	}

	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
		return checkType(
			type,
			ModBlockEntities.REACTOR_CORE_STAGE, 
			(world1, pos, state1, blockEntity) -> (
				(ReactorCoreStageBlockEntity)blockEntity
			).tick(world1, pos, state1, (ReactorCoreStageBlockEntity)blockEntity)
		);
	}

	private boolean checkStructure(World world, BlockPos blockPos) {
		for (BlockPos offset : COBBLESTONE_POSITIONS) {
			if (!world.getBlockState(blockPos.add(offset)).isOf(Blocks.COBBLESTONE)) {
				return false;
			}
		}

		for (BlockPos offset : GOLD_BLOCK_POSITIONS) {
			if (!world.getBlockState(blockPos.add(offset)).isOf(Blocks.GOLD_BLOCK)) {
				return false;
			}
		}

		return true;
	}
}
