package com.noisy_woman_20.more.damage_type;

import com.noisy_woman_20.more.More;
import com.noisy_woman_20.more.tool.ToolRegisterFunctions;

import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;

public class ModDamageTypes {
	public static void registerModDamageTypes() {    // 伤害类型注册
		More.LOGGER.info("Registered mod damage types successfully");
	}

	public static final RegistryKey<DamageType> TOTEM_CURSES = ToolRegisterFunctions.registerDamageType("totem_curses");
	public static final RegistryKey<DamageType> MOBS_FIGHT_BACK = ToolRegisterFunctions.registerDamageType("mobs_fight_back");
}
