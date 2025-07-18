package com.noisy_woman_20.more.structure;

import java.util.Optional;

import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.noisy_woman_20.more.structure_type.ModStructureTypes;
import com.mojang.serialization.Codec;

import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.world.gen.heightprovider.HeightProvider;
import net.minecraft.world.gen.structure.Structure;
import net.minecraft.world.gen.structure.StructureType;

public class NetherTowerStructure extends Structure {
	public static final Codec<NetherTowerStructure> CODEC = RecordCodecBuilder.<NetherTowerStructure>mapCodec(
		instance -> instance.group(
			NetherTowerStructure.configCodecBuilder(instance),
			StructurePool.REGISTRY_CODEC.fieldOf("start_pool").forGetter(structure -> structure.startPool),
			Codec.intRange(0, 30).fieldOf("size").forGetter(structure -> structure.size),
			HeightProvider.CODEC.fieldOf("start_height").forGetter(structure -> structure.startHeight)
		).apply(instance, NetherTowerStructure::new)
	).codec();

	private final RegistryEntry<StructurePool> startPool;
	private final int size;
	private final HeightProvider startHeight;

	public NetherTowerStructure(
		Structure.Config config,
		RegistryEntry<StructurePool> startPool,
		int size,
		HeightProvider startHeight
	) {
		super(config);
		this.startPool = startPool;
		this.size = size;
		this.startHeight = startHeight;
	}

	@Override
	protected Optional<Structure.StructurePosition> getStructurePosition(Structure.Context context) {
		return Optional.empty();
	}

	@Override
	public StructureType<?> getType() {
		return ModStructureTypes.NETHER_TOWER;
	}
}
