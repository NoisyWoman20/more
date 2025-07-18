package com.noisy_woman_20.more.item.items;

import com.noisy_woman_20.more.event.sound_events.ModSoundEvents;
import com.noisy_woman_20.more.tool.ToolFunctions;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.util.Rarity;

public class MusicDiscPrecipiceItem extends MusicDiscItem {
	public MusicDiscPrecipiceItem() {
		super(
			13,
			ModSoundEvents.PRECIPICE,
			new FabricItemSettings().rarity(Rarity.RARE).maxCount(1),
			ToolFunctions.toTotalSeconds(0, 4, 59)
		);
	}

	@Override
	public boolean isEnchantable(ItemStack stack) {
		return false;
	}
}
