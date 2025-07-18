package com.noisy_woman_20.more.item.items;

import com.noisy_woman_20.more.block.ModBlocks;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Rarity;

public class GlowingObsidianBlockItem extends BlockItem {
	public GlowingObsidianBlockItem() {
		super(
			ModBlocks.GLOWING_OBSIDIAN,
			new FabricItemSettings().rarity(Rarity.COMMON).maxCount(64)
		);
	}

	@Override
	public boolean isEnchantable(ItemStack stack) {
		return false;
	}
}
