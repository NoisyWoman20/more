package com.noisy_woman_20.more.item.items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Rarity;

public class EndGatewayBlockItem extends BlockItem {
	public EndGatewayBlockItem() {
		super(
			Blocks.END_GATEWAY,
			new FabricItemSettings().rarity(Rarity.EPIC).maxCount(64)
		);
	}

	@Override
	public boolean isEnchantable(ItemStack stack) {
		return false;
	}
}
