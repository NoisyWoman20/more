package com.noisy_woman_20.more.block_entity.block_entities;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

import com.noisy_woman_20.more.More;
import com.noisy_woman_20.more.block.ModBlocks;
import com.noisy_woman_20.more.block.blocks.ReactorCoreStageBlock;
import com.noisy_woman_20.more.block_entity.ModBlockEntities;
import com.noisy_woman_20.more.tool.ToolFunctions;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.ZombifiedPiglinEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.structure.StructureTemplateManager;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

public class ReactorCoreStageBlockEntity extends BlockEntity implements BlockEntityTicker<ReactorCoreStageBlockEntity>{
	private boolean hasFinished = false;
	private boolean isInitialized = false;
	private short progress = 0;    // 单位：tick
	private int targetUndateTimer = 0;
	private final Set<UUID> spawnedItemUUIDs = new HashSet<>();
	private static final short MAX_PROGRESS = (short)ToolFunctions.secondsToTicks(45);    // 45s（900t）
	private static final Item[] ITEM_POOL = {
		Items.GLOWSTONE_DUST,
		Items.QUARTZ,
		Items.CACTUS,
		Items.SUGAR_CANE,
		Items.RED_MUSHROOM,
		Items.BROWN_MUSHROOM,
		Items.PUMPKIN_SEEDS,
		Items.MELON_SEEDS,
		Items.BOWL,
		Items.BOOK
	};

	public ReactorCoreStageBlockEntity(BlockPos pos, BlockState state) {
		super(ModBlockEntities.REACTOR_CORE_STAGE, pos, state);
	}

	public boolean getHasFinished() {
		return hasFinished;
	}

	public boolean getIsInitialized() {
		return isInitialized;
	}

	public short getProgress() {
		return progress;
	}

	public void setHasFinished(boolean new_value) {
		hasFinished = new_value;
	}

	public void setIsInitialized(boolean new_value) {
		isInitialized = new_value;
	}

	public void setProgress(short new_value) {
		progress = new_value;
	}

	public short getMaxProgress() {
		return MAX_PROGRESS;
	}

	@Override
	public void readNbt(NbtCompound nbt) {
		super.readNbt(nbt);
		isInitialized = nbt.getBoolean("IsInitialized");
		hasFinished = nbt.getBoolean("HasFinished");
		progress = nbt.getShort("Progress");
	}

	@Override
	public void writeNbt(NbtCompound nbt) {
		super.writeNbt(nbt);
		nbt.putBoolean("IsInitialized", isInitialized);
		nbt.putBoolean("HasFinished", hasFinished);
		nbt.putShort("Progress", progress);
	}

	@Override
	public void tick(World world, BlockPos pos, BlockState state, ReactorCoreStageBlockEntity blockEntity) {
		if (!isInitialized || hasFinished) {
			return;
		}

		if (progress == 0) {
			generateNetherTower(world, pos);
		} else if (progress == ToolFunctions.secondsToTicks(1)) {
			replaceCobblestones(world, pos, -1);
		} else if (progress == ToolFunctions.secondsToTicks(2)) {
			replaceCobblestones(world, pos, 0);
		} else if (progress == ToolFunctions.secondsToTicks(3)) {
			replaceCobblestones(world, pos, 1);
		} else if (progress == ToolFunctions.secondsToTicks(4)) {
			replaceGoldBlocks(world, pos);
		} else if (progress == ToolFunctions.secondsToTicks(6)) {
			spawnZombifiedPiglins(4);
			spawnRandomItems(1);
		} else if (
			progress >= ToolFunctions.secondsToTicks(6) &&
			progress <= ToolFunctions.secondsToTicks(42)
		) {
			if (ToolFunctions.ticksToSeconds(progress) % 3 == 0) {
				spawnRandomItems(1);
			}

			if (ToolFunctions.ticksToSeconds(progress) % 10 == 0 && ToolFunctions.ticksToSeconds(progress) != 10) {
				clearPreviouslySpawnedItems();
			}
		} else if (progress == ToolFunctions.secondsToTicks(43)) {
			replaceGlowingObsidians(world, pos, 1);
		} else if (progress == ToolFunctions.secondsToTicks(44)) {
			replaceGlowingObsidians(world, pos, 0);
		} else if (progress == ToolFunctions.secondsToTicks(45)) {
			replaceGlowingObsidians(world, pos, -1);
		}

		if (
			!world.isClient() &&
			++targetUndateTimer >= ToolFunctions.secondsToTicks(1) &&
			!hasFinished &&
			isInitialized
		) {
			targetUndateTimer = 0;
			updateZombifiedPiglinTargets((ServerWorld)world, pos);
		}

		if (progress >= MAX_PROGRESS) {
			hasFinished = true;
			world.setBlockState(
				pos,
				state.with(
					ReactorCoreStageBlock.STATE,
					ReactorCoreStageBlock.ReactorCoreBlockState.EXTINGUISHED
				)
			);
			clearPreviouslySpawnedItems();
			progress = 0;
		} else {
			progress++;
			markDirty();
		}
	}

	private void generateNetherTower(World world, BlockPos pos) {
		if (world.isClient() || !(world instanceof ServerWorld serverWorld)) {
			return;
		}

		StructureTemplateManager structureManager = serverWorld.getStructureTemplateManager();

		Identifier structureId = new Identifier(More.MOD_ID, "nether_tower");
		Optional<StructureTemplate> template = structureManager.getTemplate(structureId);

		BlockPos structurePos = pos.add(-8, -3, -8);

		template.get().place(
			serverWorld,
			structurePos,
			structurePos,
			new StructurePlacementData()
				.setRotation(BlockRotation.NONE)
				.setMirror(BlockMirror.NONE)
				.setIgnoreEntities(false),
			world.getRandom(),
			Block.NOTIFY_ALL
		);
	}

	private void spawnZombifiedPiglins(int spawnCount) {
		if (!world.isClient()) {
			ServerWorld serverWorld = (ServerWorld)world;
			BlockPos centerPos = this.pos;

			PlayerEntity nearestPlayer = world.getClosestPlayer(
				(centerPos.getX() + 0.5),
				(centerPos.getY() + 0.5),
				(centerPos.getZ() + 0.5),
				10,
				EntityPredicates.EXCEPT_SPECTATOR
			);

			final int AREA_SIZE = 15;
			final int HALF_SIZE = (AREA_SIZE / 2);

			for (int i = 1; i <= spawnCount; i++) {
				double spawnX = 0;
				double spawnZ = 0;

				do {
					spawnX = (centerPos.getX() + (world.random.nextInt(AREA_SIZE) - HALF_SIZE) + 0.5);
					spawnZ = (centerPos.getZ() + (world.random.nextInt(AREA_SIZE) - HALF_SIZE) + 0.5);
				} while (
					Math.abs((spawnX - centerPos.getX())) <= 1.5 &&
					Math.abs((spawnZ - centerPos.getZ())) <= 1.5
				);

				double spawnY = (centerPos.getY() + 0.5);

				ZombifiedPiglinEntity piglin = EntityType.ZOMBIFIED_PIGLIN.create(serverWorld);
				piglin.setPosition(spawnX, spawnY, spawnZ);
				piglin.setAngerTime(Integer.MAX_VALUE);
				if (!(nearestPlayer == null)) {
					piglin.setAngryAt(nearestPlayer.getUuid());
				}
				piglin.setPersistent();
				piglin.setStackInHand(Hand.MAIN_HAND, new ItemStack(Items.GOLDEN_SWORD));
				serverWorld.spawnEntity(piglin);
			}
		}
	};

	private void replaceGlowingObsidians(World world, BlockPos centerPos, int layer) {
		BlockPos[] allPositions = Stream.concat(
			Arrays.stream(ReactorCoreStageBlock.COBBLESTONE_POSITIONS),
			Arrays.stream(ReactorCoreStageBlock.GOLD_BLOCK_POSITIONS)
		).toArray(BlockPos[]::new);

		for (BlockPos offset : allPositions) {
			if (offset.getY() == layer) {
				BlockPos targetPos = centerPos.add(offset);
				if (world.getBlockState(targetPos).isOf(ModBlocks.GLOWING_OBSIDIAN)) {
					world.setBlockState(targetPos, Blocks.OBSIDIAN.getDefaultState());
				}
			}
		}
	}

	private void replaceCobblestones(World world, BlockPos centerPos, int layer) {
		for (BlockPos offset : ReactorCoreStageBlock.COBBLESTONE_POSITIONS) {
			if (offset.getY() == layer) {
				BlockPos targetPos = centerPos.add(offset);
				if (world.getBlockState(targetPos).isOf(Blocks.COBBLESTONE)) {
					world.setBlockState(targetPos, ModBlocks.GLOWING_OBSIDIAN.getDefaultState());
				}
			}
		}
	}

	private void replaceGoldBlocks(World world, BlockPos centerPos) {
		for (BlockPos offset : ReactorCoreStageBlock.GOLD_BLOCK_POSITIONS) {
			BlockPos targetPos = centerPos.add(offset);
			if (world.getBlockState(targetPos).isOf(Blocks.GOLD_BLOCK)) {
				world.setBlockState(targetPos, ModBlocks.GLOWING_OBSIDIAN.getDefaultState());
			}
		}
	}

	private void updateZombifiedPiglinTargets(ServerWorld serverWorld, BlockPos reactoePos) {
		PlayerEntity nearestPlayer = serverWorld.getClosestPlayer(
			(reactoePos.getX() + 0.5),
			(reactoePos.getY() + 0.5),
			(reactoePos.getZ() + 0.5),
			10,
			EntityPredicates.EXCEPT_SPECTATOR
		);

		if (nearestPlayer == null) {
			return;
		}

		serverWorld.getEntitiesByType(
			EntityType.ZOMBIFIED_PIGLIN,
			new Box(reactoePos).expand(10),
			piglin -> true
		).forEach(
			piglin -> {
				LivingEntity curreTarget = piglin.getTarget();

				if (
					curreTarget == null ||
					!curreTarget.isAlive() ||
					!curreTarget.getUuid().equals(nearestPlayer.getUuid())
				) {
					piglin.setAngerTime(Integer.MAX_VALUE);
					piglin.setAngryAt(nearestPlayer.getUuid());
					piglin.setTarget(nearestPlayer);
				}
			}
		);
	}

	private void clearPreviouslySpawnedItems() {
		if (world.isClient()) {
			return;
		}

		ServerWorld serverWorld = (ServerWorld)world;
		BlockPos centerPos = this.pos;
		final int CLEAR_RADIUS = 10;

		List<ItemEntity> itemsToRemove = serverWorld.getEntitiesByType(
			EntityType.ITEM,
			new Box(centerPos).expand(CLEAR_RADIUS),
			item -> spawnedItemUUIDs.contains(item.getUuid())
		);

		for (ItemEntity item : itemsToRemove) {
			item.discard();
			spawnedItemUUIDs.remove(item.getUuid());
		}
	}

	private void spawnRandomItems(int spawnCount) {
		if (world.isClient()) {
			return;
		}

		ServerWorld serverWorld = (ServerWorld)world;
		BlockPos centerPos = this.pos;
		final int AREA_SIZE = 15;
		int HALF_SIZE = (AREA_SIZE / 2);
		final int MIN_STACK_SIZE = 1;
		final int MAX_STACK_SIZE = 3;

		for (int batch = 1; batch <= spawnCount; batch++) {
			for (int i = 1; i <= 2; i++) {
				Item selectedItem = ITEM_POOL[world.random.nextInt(ITEM_POOL.length)];
				int stackSize = (MIN_STACK_SIZE + world.random.nextInt((MAX_STACK_SIZE - MIN_STACK_SIZE + 1)));

				double spawnX = 0;
				double spawnZ = 0;

				do {
					spawnX = (centerPos.getX() + (world.random.nextInt(AREA_SIZE) - HALF_SIZE) + 0.5);
					spawnZ = (centerPos.getZ() + (world.random.nextInt(AREA_SIZE) - HALF_SIZE) + 0.5);
				} while (
					Math.abs((spawnX - centerPos.getX())) <= 1.5 &&
					Math.abs((spawnZ - centerPos.getZ())) <= 1.5
				);

				double spawnY = centerPos.getY();

				ItemEntity itemEntity = new ItemEntity(
					serverWorld,
					spawnX,
					spawnY,
					spawnZ,
					new ItemStack(selectedItem, stackSize)
				);

				itemEntity.setVelocity(
					(world.random.nextDouble() * 0.2 - 0.1),
					0.2,
					(world.random.nextDouble() * 0.2 - 0.1)
				);

				serverWorld.spawnEntity(itemEntity);
				spawnedItemUUIDs.add(itemEntity.getUuid());
			}
		}
	}
}
