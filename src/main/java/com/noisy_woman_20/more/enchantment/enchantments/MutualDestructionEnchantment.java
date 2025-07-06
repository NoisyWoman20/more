package com.noisy_woman_20.more.enchantment.enchantments;

import com.noisy_woman_20.more.damage_type.ModDamageTypes;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.World;

public class MutualDestructionEnchantment extends Enchantment {
	private float previousHealth;

	public MutualDestructionEnchantment() {
		super(Rarity.RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
		this.previousHealth = 0f;
	}

	@Override
	public boolean isAcceptableItem(ItemStack stack) {
		return stack.getItem() instanceof AxeItem ? true : super.isAcceptableItem(stack);
	}

	@Override
	public void onTargetDamaged(LivingEntity user, Entity target, int level) {
		World world = user.getWorld();
		DamageSource mobsFightBackDamageSource = new DamageSource(
			world
			.getRegistryManager()
			.get(RegistryKeys.DAMAGE_TYPE)
			.entryOf(ModDamageTypes.MOBS_FIGHT_BACK)
		);
		if (target instanceof LivingEntity livingEntity) {
			float attackDamage = 0f;

			if (isEqual(livingEntity.getMaxHealth(), livingEntity.getHealth()) && livingEntity.isAlive()) {
				attackDamage = (livingEntity.getMaxHealth() - livingEntity.getHealth());
			} else {
				if (livingEntity.isAlive()) {
					attackDamage = 2f;
				} else {
					attackDamage = (this.previousHealth - livingEntity.getHealth());
					this.previousHealth = 0f;
				}

			}

			user.damage(mobsFightBackDamageSource, attackDamage);
			this.previousHealth = livingEntity.getHealth();
		}

		super.onTargetDamaged(user, target, level);
	}

	@Override
	public int getMinPower(int level) {
		return (1 + (level - 1) * 11);
	}

	@Override
	public int getMaxPower(int level) {
		return (1 + (level - 1) * 11);
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

	protected static boolean isEqual(float num1, float num2) {
		if (Math.abs(num1 - num2) <= 0.0001f) {
			return true;
		} else {
			return false;
		}
	}
}
