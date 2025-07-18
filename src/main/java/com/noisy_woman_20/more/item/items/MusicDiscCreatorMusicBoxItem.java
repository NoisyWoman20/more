package com.noisy_woman_20.more.item.items;

import com.noisy_woman_20.more.event.sound_events.ModSoundEvents;
import com.noisy_woman_20.more.tool.ToolFunctions;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.util.Rarity;

public class MusicDiscCreatorMusicBoxItem extends MusicDiscItem {
	public MusicDiscCreatorMusicBoxItem() {
		super(
			11,
			ModSoundEvents.CREATOR_MUSIC_BOX,
			new FabricItemSettings().rarity(Rarity.RARE).maxCount(1),
			ToolFunctions.toTotalSeconds(0, 1, 14)
		);
	}

	@Override
	public boolean isEnchantable(ItemStack stack) {
		return false;
	}
}
