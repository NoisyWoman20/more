package com.noisy_woman_20.more;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.noisy_woman_20.more.enchantment.ModEnchantments;
import com.noisy_woman_20.more.event.ModEvents;
import com.noisy_woman_20.more.item.ModItems;
import com.noisy_woman_20.more.recipe_serializer.ModRecipeSerializers;
import com.noisy_woman_20.more.block.ModBlocks;
import com.noisy_woman_20.more.damage_type.ModDamageTypes;
import com.noisy_woman_20.more.trade.ModTrades;

public class More implements ModInitializer {
	public static final String MOD_ID = "more";
	public static final String MOD_NAME = "More";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModTrades.registerModTrades();
		ModEvents.registerModEvents();
		ModEnchantments.registerModEnchantments();
		ModDamageTypes.registerModDamageTypes();
		ModRecipeSerializers.registerModRecipeSerializers();
		LOGGER.info("Loaded mod successfully");
	}
}
