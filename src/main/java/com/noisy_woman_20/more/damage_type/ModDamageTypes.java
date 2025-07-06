package com.noisy_woman_20.more.damage_type;

import com.noisy_woman_20.more.More;

import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModDamageTypes {
	public static void registerModDamageTypes() {    // 伤害类型注册
		More.LOGGER.info("Registered mod damage type successfully");
	}

	private static RegistryKey<DamageType> registerDamageType(String id) {
		return RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of(More.MOD_ID, id));
	}

	public static final RegistryKey<DamageType> TOTEM_CURSES = registerDamageType("totem_curses");
	public static final RegistryKey<DamageType> MOBS_FIGHT_BACK = registerDamageType("mobs_fight_back");
}
