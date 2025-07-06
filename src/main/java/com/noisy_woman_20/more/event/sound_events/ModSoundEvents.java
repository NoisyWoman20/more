package com.noisy_woman_20.more.event.sound_events;

import com.noisy_woman_20.more.More;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSoundEvents {
	public static final SoundEvent MINECRAFT = registerSoundEvent("music_disc.minecraft");
	public static final SoundEvent INFINITE_AMETHYST = registerSoundEvent("music_disc.infinite_amethyst");
	public static final SoundEvent CREATOR = registerSoundEvent("music_disc.creator");
	public static final SoundEvent CREATOR_MUSIC_BOX = registerSoundEvent("music_disc.creator_music_box");
	public static final SoundEvent PRECIPICE = registerSoundEvent("music_disc.precipice");

	public static void registerModSoundEvents() {    // 声音事件注册
		More.LOGGER.info("+ Registered mod sound event successfully");
	}

	private static SoundEvent registerSoundEvent(String id) {
		Identifier identifier = new Identifier(More.MOD_ID, id);
		return Registry.register(Registries.SOUND_EVENT, identifier, SoundEvent.of(identifier));
	}
}
