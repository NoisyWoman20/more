package com.noisy_woman_20.more.item.items;

import com.noisy_woman_20.more.tool_material.ModToolMaterials;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.Rarity;

public class DiamondSwordItem extends SwordItem {
	public DiamondSwordItem() {
		super(
			ModToolMaterials.DIAMOND,
			3,
			-2.4f,
			new FabricItemSettings().rarity(Rarity.COMMON).maxCount(1)
		);
	}

	@Override
	public boolean isEnchantable(ItemStack stack) {
		return true;
	}
}
