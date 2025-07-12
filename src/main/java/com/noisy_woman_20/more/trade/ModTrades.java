package com.noisy_woman_20.more.trade;

import com.noisy_woman_20.more.More;
import com.noisy_woman_20.more.enchantment.ModEnchantments;
import com.noisy_woman_20.more.item.ModItems;

import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.VillagerProfession;

import java.util.Map;
import java.util.Random;

public class ModTrades {
	public static void registerModTrades() {    // 交易注册
		registerTrades();
		More.LOGGER.info("Registered mod trade successfully");
	}

	private static void registerTrades() {
		TradeOfferHelper.registerVillagerOffers(VillagerProfession.LIBRARIAN, 2, factories -> {
			factories.add((entity, random) -> {
				int bluntnessEnchantmentLevel = (random.nextInt(5) + 1);

				ItemStack enchantedBook = new ItemStack(Items.ENCHANTED_BOOK);
				EnchantmentHelper.set(Map.of(ModEnchantments.BLUNTNESS, bluntnessEnchantmentLevel), enchantedBook);

				int emeraldCount = 0;    // 交易所需的绿宝石数量
				try {
					emeraldCount = (bluntnessEnchantmentLevel * 11 + interval(-5, 5));
				} catch (IllegalAccessException e) {
					More.LOGGER.warn(("BLUNTNESS_ENCHANTMENT: " + e.getMessage()));
				}
				int maxUses = 16;    // 最大交易次数
				int experience = 5;    // 交易经验
				float priceMultiplier = 0.05f;    // 价格乘数
		
				return new TradeOffer(
					new ItemStack(Items.EMERALD, emeraldCount),
					new ItemStack(Items.BOOK),
					enchantedBook, maxUses, experience, priceMultiplier
				);
			});
			
			factories.add((entity, random) -> {
				int spareEnchantmentLevel = (random.nextInt(5) + 1);

				ItemStack enchantedBook = new ItemStack(Items.ENCHANTED_BOOK);
				EnchantmentHelper.set(Map.of(ModEnchantments.SPARE, spareEnchantmentLevel), enchantedBook);

				int emeraldCount = 1;
				try {
					emeraldCount = (spareEnchantmentLevel * 9 + interval(-3, 3));
				} catch (IllegalAccessException e) {
					More.LOGGER.warn(("SPARE_ENCHANTMENT: " + e.getMessage()));
				}
				int maxUses = 16;
				int experience = 5;
				float priceMultiplier = 0.05f;
				return new TradeOffer(
					new ItemStack(Items.EMERALD, emeraldCount),
					new ItemStack(Items.BOOK),
					enchantedBook, maxUses, experience, priceMultiplier
				);
			});

			factories.add((entity, random) -> {
				int friendOfArthropodsEnchantmentLevel = (random.nextInt(5) + 1);

				ItemStack enchantedBook = new ItemStack(Items.ENCHANTED_BOOK);
				EnchantmentHelper.set(Map.of(ModEnchantments.FRIEND_OF_ARTHROPODS, friendOfArthropodsEnchantmentLevel), enchantedBook);

				int emeraldCount = 1;
				try {
					emeraldCount = (friendOfArthropodsEnchantmentLevel * 8 + interval(-2, 2));
				} catch (IllegalAccessException e) {
					More.LOGGER.warn(("FRIEND_OF_ARTHROPODS_ENCHANTMENT: " + e.getMessage()));
				}
				int maxUses = 16;
				int experience = 5;
				float priceMultiplier = 0.05f;
				return new TradeOffer(
					new ItemStack(Items.EMERALD, emeraldCount),
					new ItemStack(Items.BOOK),
					enchantedBook, maxUses, experience, priceMultiplier
				);
			});

			factories.add((entity, random) -> {
				int ironMaidenEnchantmentLevel = (random.nextInt(3) + 1);

				ItemStack enchantedBook = new ItemStack(Items.ENCHANTED_BOOK);
				EnchantmentHelper.set(Map.of(ModEnchantments.IRON_MAIDEN, ironMaidenEnchantmentLevel), enchantedBook);

				int emeraldCount = 1;
				try {
					emeraldCount = (ironMaidenEnchantmentLevel * 12 + interval(-7, 12));
				} catch (IllegalAccessException e) {
					More.LOGGER.warn(("IRON_MAIDEN_ENCHANTMENT: " + e.getMessage()));
				}
				int maxUses = 16;
				int experience = 5;
				float priceMultiplier = 0.05f;
				return new TradeOffer(
					new ItemStack(Items.EMERALD, emeraldCount),
					new ItemStack(Items.BOOK),
					enchantedBook, maxUses, experience, priceMultiplier
				);
			});

			factories.add((entity, random) -> {
				int ironMaidenEnchantmentLevel = (random.nextInt(3) + 1);

				ItemStack enchantedBook = new ItemStack(Items.ENCHANTED_BOOK);
				EnchantmentHelper.set(Map.of(ModEnchantments.POISONING, ironMaidenEnchantmentLevel), enchantedBook);

				int emeraldCount = 1;
				try {
					emeraldCount = (ironMaidenEnchantmentLevel * 11 + interval(-5, 10));
				} catch (IllegalAccessException e) {
					More.LOGGER.warn(("POISONING_ENCHANTMENT: " + e.getMessage()));
				}
				int maxUses = 16;
				int experience = 5;
				float priceMultiplier = 0.05f;
				return new TradeOffer(
					new ItemStack(Items.EMERALD, emeraldCount),
					new ItemStack(Items.BOOK),
					enchantedBook, maxUses, experience, priceMultiplier
				);
			});

			factories.add((entity, random) -> {
				ItemStack enchantedBook = new ItemStack(Items.ENCHANTED_BOOK);
				EnchantmentHelper.set(Map.of(ModEnchantments.MUTUAL_DESTRUCTION, 1), enchantedBook);

				int emeraldCount = 1;
				try {
					emeraldCount = (10 + interval(-5, 5));
				} catch (IllegalAccessException e) {
					More.LOGGER.warn(("mobs_fight_back: " + e.getMessage()));
				}
				int maxUses = 16;
				int experience = 5;
				float priceMultiplier = 0.05f;
				return new TradeOffer(
					new ItemStack(Items.EMERALD, emeraldCount),
					new ItemStack(Items.BOOK),
					enchantedBook, maxUses, experience, priceMultiplier
				);
			});

			factories.add((entity, random) -> {
				int accompliceEnchantmentLevel = (random.nextInt(2) + 1);

				ItemStack enchantedBook = new ItemStack(Items.ENCHANTED_BOOK);
				EnchantmentHelper.set(Map.of(ModEnchantments.ACCOMPLICE, 1), enchantedBook);

				int emeraldCount = 1;
				try {
					emeraldCount = (10 + interval(-5, 5) * accompliceEnchantmentLevel);
				} catch (IllegalAccessException e) {
					More.LOGGER.warn(("ACCOMPLICE_ENCTANTMENT: " + e.getMessage()));
				}
				int maxUses = 16;
				int experience = 5;
				float priceMultiplier = 0.05f;
				return new TradeOffer(
					new ItemStack(Items.EMERALD, emeraldCount),
					new ItemStack(Items.BOOK),
					enchantedBook, maxUses, experience, priceMultiplier
				);
			});
		});

		TradeOfferHelper.registerVillagerOffers(VillagerProfession.MASON, 2, factories -> {
			factories.add((entity, random) -> {
				int emeraldCount = 2;
				int maxUses = 16;
				int experience = 5;
				float priceMultiplier = 0.05f;
				return new TradeOffer(
					new ItemStack(Items.EMERALD, emeraldCount),
					new ItemStack(Items.AIR, 0),
					new ItemStack(ModItems.GLOWING_OBSIDIAN, 4),
					maxUses, experience, priceMultiplier
				);
			});
		});
	}

	private static int interval(int min, int max) throws IllegalAccessException {
		Random random = new Random();
		if (min > max) {
			throw new IllegalAccessException("minimum > maximum");
		}
		return (random.nextInt(max - min + 1) + min);
	}
}
