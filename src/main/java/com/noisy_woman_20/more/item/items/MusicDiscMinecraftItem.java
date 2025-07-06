package com.noisy_woman_20.more.item.items;

import com.noisy_woman_20.more.event.sound_events.ModSoundEvents;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.util.Rarity;

public class MusicDiscMinecraftItem extends MusicDiscItem {
	public MusicDiscMinecraftItem() {
		super(
			15,
			ModSoundEvents.MINECRAFT,
			new FabricItemSettings().rarity(Rarity.RARE).maxCount(1),
			254
		);
	}

	@Override
	public boolean isEnchantable(ItemStack stack) {
		return false;
	}
}
