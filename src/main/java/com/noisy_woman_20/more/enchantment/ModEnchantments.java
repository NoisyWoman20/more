package com.noisy_woman_20.more.enchantment;

import com.noisy_woman_20.more.More;
import com.noisy_woman_20.more.enchantment.enchantments.PoisoningEnchantment;
import com.noisy_woman_20.more.enchantment.enchantments.AccompliceEnchantment;
import com.noisy_woman_20.more.enchantment.enchantments.IronMaidenEnchantment;
import com.noisy_woman_20.more.enchantment.enchantments.NoDamageEnchantment;
import com.noisy_woman_20.more.enchantment.enchantments.MutualDestructionEnchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEnchantments {
	public static void registerModEnchantments() {    // 附魔注册
		More.LOGGER.info("Registered mod enchantment successfully");
	}

	private static Enchantment registerEnchantment(String id, Enchantment enchantment) {
		Identifier identifier = new Identifier(More.MOD_ID, id);
		return Registry.register(Registries.ENCHANTMENT, identifier, enchantment);
	}

	public static final Enchantment POISONING = registerEnchantment(
		"poisoning",
		new PoisoningEnchantment()
	);

	public static final Enchantment BLUNTNESS = registerEnchantment(
		"bluntness",
		new NoDamageEnchantment(Rarity.COMMON, 0)
	);

	public static final Enchantment SPARE = registerEnchantment(
		"spare",
		new NoDamageEnchantment(Rarity.UNCOMMON, 1)
	);

	public static final Enchantment FRIEND_OF_ARTHROPODS = registerEnchantment(
		"friend_of_arthropods",
		new NoDamageEnchantment(Rarity.UNCOMMON, 2)
	);

	public static final Enchantment IRON_MAIDEN = registerEnchantment(
		"iron_maiden",
		new IronMaidenEnchantment()
	);

	public static final Enchantment MUTUAL_DESTRUCTION = registerEnchantment(
		"mutual_destruction",
		new MutualDestructionEnchantment()
	);

	public static final Enchantment ACCOMPLICE = registerEnchantment(
		"accomplice",
		new AccompliceEnchantment()
	);
}
