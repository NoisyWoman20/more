package com.noisy_woman_20.more.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.noisy_woman_20.more.damage_type.ModDamageTypes;
import com.noisy_woman_20.more.item.ModItems;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.World;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
	@Inject(method = "tick", at = @At("HEAD"))
	private void tick(CallbackInfo cir) {
		LivingEntity entity = (LivingEntity)(Object)this;
		if (!entity.getWorld().isClient()) {
			shouldUseTotemOfDyingItem(entity);
		}
	}

	private static void shouldUseTotemOfDyingItem(LivingEntity entity) {
		if (
			!entity.isAlive() ||
			(
				entity instanceof PlayerEntity playerEntity &&
				(playerEntity.isCreative() || playerEntity.isSpectator())
			)
		) {
			// 当 实体死亡 或 (实体属于玩家 且 玩家游戏模式为创造或旁观) 时触发
		} else {
			useTotemOfDyingItem(entity);
		}
	}

	private static void useTotemOfDyingItem(LivingEntity entity) {
		World world = entity.getWorld();
		DamageSource totemCursesDamageSource = new DamageSource(
			world
			.getRegistryManager()
			.get(RegistryKeys.DAMAGE_TYPE)
			.entryOf(ModDamageTypes.TOTEM_CURSES)
		);
		ItemStack mainHandStack = entity.getMainHandStack();
		ItemStack offHandStack = entity.getOffHandStack();
		if (mainHandStack.isOf(ModItems.TOTEM_OF_DYING)) {
			// 当 主手持有必死图腾 时触发
			if (entity.damage(totemCursesDamageSource, entity.getHealth())) {
				mainHandStack.decrement(1);
			}
		} else if (offHandStack.isOf(ModItems.TOTEM_OF_DYING)) {
			// 当 副手持有必死图腾 时触发
			if (entity.damage(totemCursesDamageSource, entity.getHealth())) {
				offHandStack.decrement(1);
			}
		}
	}
}
