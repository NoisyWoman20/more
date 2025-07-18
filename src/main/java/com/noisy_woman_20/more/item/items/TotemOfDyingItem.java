package com.noisy_woman_20.more.item.items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Rarity;

public class TotemOfDyingItem extends Item {
	public TotemOfDyingItem() {
		super(new FabricItemSettings().rarity(Rarity.UNCOMMON).maxCount(1));
	}

	@Override
	public boolean isEnchantable(ItemStack stack) {
		return false;
	}
}
