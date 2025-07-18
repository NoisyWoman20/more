package com.noisy_woman_20.more.event.sound_events;

import com.noisy_woman_20.more.More;
import com.noisy_woman_20.more.tool.ToolRegisterFunctions;

import net.minecraft.sound.SoundEvent;

public class ModSoundEvents {
	public static final SoundEvent MINECRAFT = ToolRegisterFunctions.registerSoundEvent("music_disc.minecraft");
	public static final SoundEvent INFINITE_AMETHYST = ToolRegisterFunctions.registerSoundEvent("music_disc.infinite_amethyst");
	public static final SoundEvent CREATOR = ToolRegisterFunctions.registerSoundEvent("music_disc.creator");
	public static final SoundEvent CREATOR_MUSIC_BOX = ToolRegisterFunctions.registerSoundEvent("music_disc.creator_music_box");
	public static final SoundEvent PRECIPICE = ToolRegisterFunctions.registerSoundEvent("music_disc.precipice");

	public static void registerModSoundEvents() {    // 声音事件注册
		More.LOGGER.info("+ Registered mod sound events successfully");
	}
}
