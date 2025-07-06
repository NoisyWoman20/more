package com.noisy_woman_20.more.enchantment.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;

public class PoisoningEnchantment extends Enchantment {
	public PoisoningEnchantment() {
		super(Rarity.COMMON, EnchantmentTarget.WEAPON, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
	}

	@Override
	public void onTargetDamaged(LivingEntity user, Entity target, int level) {
		if (target instanceof LivingEntity livingEntity) {
			livingEntity.addStatusEffect(
				new StatusEffectInstance(StatusEffects.POISON, 200, (level - 1))
			);
		}

		super.onTargetDamaged(user, target, level);
	}

	@Override
	public boolean isAcceptableItem(ItemStack stack) {
		return stack.getItem() instanceof AxeItem ? true : super.isAcceptableItem(stack);
	}

	@Override
	public int getMinPower(int level) {
		return (1 + (level - 1) * 11);
	}

	@Override
	public int getMaxPower(int level) {
		return (this.getMinPower(level) + 3);
	}

	@Override
	public int getMinLevel() {
		return 1;
	}

	@Override
	public int getMaxLevel() {
		return 5;
	}
}
