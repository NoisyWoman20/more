package com.noisy_woman_20.more.item.items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.vehicle.AbstractMinecartEntity.Type;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MinecartItem;
import net.minecraft.util.Rarity;

public class SpawnerMinecartItem extends MinecartItem {
	public SpawnerMinecartItem() {
		super(
			Type.SPAWNER,
			new FabricItemSettings().rarity(Rarity.EPIC).maxCount(1)
		);
	}

	@Override
	public boolean isEnchantable(ItemStack stack) {
		return false;
	}
}
