package com.noisy_woman_20.more.structure_type;

import com.noisy_woman_20.more.More;
import com.noisy_woman_20.more.structure.NetherTowerStructure;
import com.noisy_woman_20.more.tool.ToolRegisterFunctions;

import net.minecraft.world.gen.structure.StructureType;

public class ModStructureTypes {
	public static final StructureType<NetherTowerStructure> NETHER_TOWER = ToolRegisterFunctions.registerStructureType(
		NetherTowerStructure.CODEC,
		"nether_tower"
	);
	
	public static void registerStructureTypes() {
		More.LOGGER.info("Registered mod structure types successfully");
	}
}
