package com.noisy_woman_20.more.enchantment.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;

public class AccompliceEnchantment extends Enchantment {
	public AccompliceEnchantment() {
		super(Rarity.RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
	}

	@Override
	public boolean isAcceptableItem(ItemStack stack) {
		return stack.getItem() instanceof AxeItem ? true : super.isAcceptableItem(stack);
	}

	@Override
	public void onTargetDamaged(LivingEntity user, Entity target, int level) {
		if (target instanceof LivingEntity livingEntity) {
			EntityGroup group = livingEntity.getGroup();
			if (group == EntityGroup.UNDEAD) {    // 当 生物属于亡灵生物 时触发
				livingEntity.addStatusEffect(
					new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE, 1, 1)
				);
			} else {    // 当 生物不属于亡灵生物 时触发
				livingEntity.addStatusEffect(
					new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 1, 1)
				);
			}
		}
	}

	@Override
	public int getMinPower(int level) {
		return 11;
	}

	@Override
	public int getMaxPower(int level) {
		return 11;
	}

	@Override
	public int getMaxLevel() {
		return 1;
	}

	@Override
	public boolean isCursed() {
		return true;
	}

	@Override
	public boolean isTreasure() {
		return true;
	}
}
