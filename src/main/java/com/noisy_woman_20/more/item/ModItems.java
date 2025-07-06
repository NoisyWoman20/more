package com.noisy_woman_20.more.item;

import com.noisy_woman_20.more.More;
import com.noisy_woman_20.more.item.items.DiamondSwordItem;
import com.noisy_woman_20.more.item.items.EndGatewayBlockItem;
import com.noisy_woman_20.more.item.items.EndPortalBlockItem;
import com.noisy_woman_20.more.item.items.FireBlockItem;
import com.noisy_woman_20.more.item.items.FrostedIceBlockItem;
import com.noisy_woman_20.more.item.items.GlowingObsidianBlockItem;
import com.noisy_woman_20.more.item.items.MusicDiscCreatorItem;
import com.noisy_woman_20.more.item.items.MusicDiscCreatorMusicBoxItem;
import com.noisy_woman_20.more.item.items.MusicDiscInfiniteAmethystItem;
import com.noisy_woman_20.more.item.items.MusicDiscMinecraftItem;
import com.noisy_woman_20.more.item.items.MusicDiscPrecipiceItem;
import com.noisy_woman_20.more.item.items.NetherPortalBlockItem;
import com.noisy_woman_20.more.item.items.PowderSnowBlockItem;
import com.noisy_woman_20.more.item.items.ReactorCoreStageBlockItem;
import com.noisy_woman_20.more.item.items.SoulFireBlockItem;
import com.noisy_woman_20.more.item.items.SpawnerMinecartItem;
import com.noisy_woman_20.more.item.items.TotemOfDyingItem;
import com.noisy_woman_20.more.item.items.WaterItem;
import com.noisy_woman_20.more.item.items.GrassBlockItem;
import com.noisy_woman_20.more.item.items.LavaItem;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
	public static void registerModItems() {    // 物品注册
		addItemsToItemGroup();
		More.LOGGER.info("Registered mod item successfully");
	}

	private static Item registerItem(String id, Item item) {
		return Registry.register(Registries.ITEM, Identifier.of(More.MOD_ID, id), item);
	}

	private static void addItemsToItemGroup() {
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(ModItems::addItemsToToolsItemGroup);
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(ModItems::addItemsToCombatItemGroup);
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(ModItems::addItemsToBuildingBlockItemGroup);
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(ModItems::addItemsToNaturalItemGroup);
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.OPERATOR).register(ModItems::addItemsToOperatorItemGroup);
	}

	private static void addItemsToToolsItemGroup(FabricItemGroupEntries fabricItemGroupEntries) {
		fabricItemGroupEntries.add(MUSIC_DISC_MINECRAFT);
		fabricItemGroupEntries.add(MUSIC_DISC_INFINITE_AMETHYST);
		fabricItemGroupEntries.add(MUSIC_DISC_PRECIPICE);
		fabricItemGroupEntries.add(MUSIC_DISC_CREATOR);
		fabricItemGroupEntries.add(MUSIC_DISC_CREATOR_MUSIC_BOX);
	}

	private static void addItemsToCombatItemGroup(FabricItemGroupEntries fabricItemGroupEntries) {
		fabricItemGroupEntries.add(TOTEM_OF_DYING);
	}

	private static void addItemsToBuildingBlockItemGroup(FabricItemGroupEntries fabricItemGroupEntries) {
		fabricItemGroupEntries.add(Items.OBSIDIAN);
		fabricItemGroupEntries.add(Items.CRYING_OBSIDIAN);
		fabricItemGroupEntries.add(GLOWING_OBSIDIAN);
		fabricItemGroupEntries.add(REACTOR_CORE_STAGE);
	}

	private static void addItemsToNaturalItemGroup(FabricItemGroupEntries fabricItemGroupEntries) {
		fabricItemGroupEntries.add(POWDER_SNOW);
		fabricItemGroupEntries.add(FROSTED_ICE);
		fabricItemGroupEntries.add(WATER_BLOCK);
		fabricItemGroupEntries.add(LAVA_BLOCK);
		fabricItemGroupEntries.add(FIRE);
		fabricItemGroupEntries.add(SOUL_FIRE);
	}

	private static void addItemsToOperatorItemGroup(FabricItemGroupEntries fabricItemGroupEntries) {
		fabricItemGroupEntries.add(NETHER_PORTAL);
		fabricItemGroupEntries.add(END_PORTAL);
		fabricItemGroupEntries.add(END_GATEWAY);
		fabricItemGroupEntries.add(SPAWNER_MINECART);
	}

	public static final Item DIAMOND_SWORD = registerItem(
		"diamond_sword",
		new DiamondSwordItem()
	);

	public static final Item MUSIC_DISC_MINECRAFT = registerItem(
		"music_disc_minecraft",
		new MusicDiscMinecraftItem()
	);

	public static final Item MUSIC_DISC_INFINITE_AMETHYST = registerItem(
		"music_disc_infinite_amethyst",
		new MusicDiscInfiniteAmethystItem()
	);

	public static final Item MUSIC_DISC_PRECIPICE = registerItem(
		"music_disc_precipice",
		new MusicDiscPrecipiceItem()
	);

	public static final Item MUSIC_DISC_CREATOR = registerItem(
		"music_disc_creator",
		new MusicDiscCreatorItem()
	);

	public static final Item MUSIC_DISC_CREATOR_MUSIC_BOX = registerItem(
		"music_disc_creator_music_box",
		new MusicDiscCreatorMusicBoxItem()
	);

	public static final Item GRASS_BLOCK = registerItem(
		"grass_block",
		new GrassBlockItem()
	);

	public static final Item GLOWING_OBSIDIAN = registerItem(
		"glowing_obsidian",
		new GlowingObsidianBlockItem()
	);

	public static final Item REACTOR_CORE_STAGE = registerItem(
		"reactor_core_stage",
		new ReactorCoreStageBlockItem()
	);

	public static final Item NETHER_PORTAL = registerItem(
		"nether_portal",
		new NetherPortalBlockItem()
	);

	public static final Item END_PORTAL = registerItem(
		"end_portal",
		new EndPortalBlockItem()
	);

	public static final Item END_GATEWAY = registerItem(
		"end_gateway",
		new EndGatewayBlockItem()
	);

	public static final Item FROSTED_ICE = registerItem(
		"frosted_ice",
		new FrostedIceBlockItem()
	);

	public static final Item POWDER_SNOW = registerItem(
		"powder_snow",
		new PowderSnowBlockItem()
	);

	public static final Item FIRE = registerItem(
		"fire",
		new FireBlockItem()
	);

	public static final Item SOUL_FIRE = registerItem(
		"soul_fire",
		new SoulFireBlockItem()
	);

	public static final Item WATER_BLOCK = registerItem(
		"water",
		new WaterItem()
	);

	public static final Item LAVA_BLOCK = registerItem(
		"lava",
		new LavaItem()
	);

	public static final Item SPAWNER_MINECART = registerItem(
		"spawner_minecart",
		new SpawnerMinecartItem()
	);

	public static final Item TOTEM_OF_DYING = registerItem(
		"totem_of_dying",
		new TotemOfDyingItem()
	);
}
