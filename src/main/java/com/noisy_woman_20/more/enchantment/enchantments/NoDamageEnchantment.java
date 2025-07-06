package com.noisy_woman_20.more.enchantment.enchantments;

import net.minecraft.enchantment.DamageEnchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class NoDamageEnchantment extends DamageEnchantment {
	public NoDamageEnchantment(Rarity weight, int typeIndex) {
		super(weight, typeIndex, EquipmentSlot.MAINHAND);
	}

	@Override
	public float getAttackDamage(int level, EntityGroup group) {
		if (this.typeIndex == 0) {
			return -(1.0f + (float)Math.max(0, level - 1) * 0.5f);
		} else if (this.typeIndex == 1 && group == EntityGroup.UNDEAD) {
			return -((float)level * 2.5f);
		} else {
			return this.typeIndex == 2 && group == EntityGroup.ARTHROPOD ? -((float)level * 2.5f) : 0.0f;
		}
	}

	@Override
	public void onTargetDamaged(LivingEntity user, Entity target, int level) {
		if (
			target instanceof LivingEntity livingEntity &&
			this.typeIndex == 2 &&
			level > 0 &&
			livingEntity.getGroup() == EntityGroup.ARTHROPOD
		) {
			int i = 20 + user.getRandom().nextInt(10 * level);
			livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, i, 3));
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
}
