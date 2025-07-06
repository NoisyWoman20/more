package com.noisy_woman_20.more.enchantment.enchantments;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.ThornsEnchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import java.util.Map.Entry;

import com.noisy_woman_20.more.enchantment.ModEnchantments;

import net.minecraft.item.ItemStack;
import net.minecraft.util.math.random.Random;

public class IronMaidenEnchantment extends ThornsEnchantment {
	public IronMaidenEnchantment() {
		super(Rarity.VERY_RARE, ALL_ARMOR);
	}

	public void onUserDamaged(LivingEntity user, Entity attacker, int level) {
		Random random = user.getRandom();
		Entry<EquipmentSlot, ItemStack> entry = EnchantmentHelper.chooseEquipmentWith(ModEnchantments.IRON_MAIDEN, user);
		if (shouldDamageAttacker(level, random)) {
			user.damage(user.getDamageSources().thorns(user), (float)getDamageAmount(level, random));

			if (entry != null) {
				((ItemStack)entry.getValue()).damage(2, user, entity -> entity.sendEquipmentBreakStatus((EquipmentSlot)entry.getKey()));
			}
		}
	}

	@Override
	public boolean isCursed() {
		return true;
	}

	@Override
	public boolean isTreasure() {
		return true;
	}

	private static final EquipmentSlot[] ALL_ARMOR = new EquipmentSlot[] {
		EquipmentSlot.HEAD,
		EquipmentSlot.CHEST,
		EquipmentSlot.LEGS,
		EquipmentSlot.FEET
	};	
}
