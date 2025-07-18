package com.noisy_woman_20.more.enchantment;

import com.noisy_woman_20.more.More;
import com.noisy_woman_20.more.enchantment.enchantments.PoisoningEnchantment;
import com.noisy_woman_20.more.tool.ToolRegisterFunctions;
import com.noisy_woman_20.more.enchantment.enchantments.AccompliceEnchantment;
import com.noisy_woman_20.more.enchantment.enchantments.IronMaidenEnchantment;
import com.noisy_woman_20.more.enchantment.enchantments.NoDamageEnchantment;
import com.noisy_woman_20.more.enchantment.enchantments.MutualDestructionEnchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantment.Rarity;

public class ModEnchantments {
	public static void registerModEnchantments() {    // 附魔注册
		More.LOGGER.info("Registered mod enchantments successfully");
	}

	public static final Enchantment POISONING = ToolRegisterFunctions.registerEnchantment(
		"poisoning",
		new PoisoningEnchantment()
	);

	public static final Enchantment BLUNTNESS = ToolRegisterFunctions.registerEnchantment(
		"bluntness",
		new NoDamageEnchantment(Rarity.COMMON, 0)
	);

	public static final Enchantment SPARE = ToolRegisterFunctions.registerEnchantment(
		"spare",
		new NoDamageEnchantment(Rarity.UNCOMMON, 1)
	);

	public static final Enchantment FRIEND_OF_ARTHROPODS = ToolRegisterFunctions.registerEnchantment(
		"friend_of_arthropods",
		new NoDamageEnchantment(Rarity.UNCOMMON, 2)
	);

	public static final Enchantment IRON_MAIDEN = ToolRegisterFunctions.registerEnchantment(
		"iron_maiden",
		new IronMaidenEnchantment()
	);

	public static final Enchantment MUTUAL_DESTRUCTION = ToolRegisterFunctions.registerEnchantment(
		"mutual_destruction",
		new MutualDestructionEnchantment()
	);

	public static final Enchantment ACCOMPLICE = ToolRegisterFunctions.registerEnchantment(
		"accomplice",
		new AccompliceEnchantment()
	);
}
