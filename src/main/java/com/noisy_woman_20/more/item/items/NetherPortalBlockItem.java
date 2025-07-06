package com.noisy_woman_20.more.item.items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Rarity;

public class NetherPortalBlockItem extends BlockItem {
	public NetherPortalBlockItem() {
		super(
			Blocks.NETHER_PORTAL,
			new FabricItemSettings().rarity(Rarity.EPIC).maxCount(64)
		);
	}

	@Override
	public boolean isEnchantable(ItemStack stack) {
		return false;
	}
}
