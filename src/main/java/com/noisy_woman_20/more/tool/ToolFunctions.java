package com.noisy_woman_20.more.tool;

public final class ToolFunctions {
	static public int toTotalSeconds(int hours, int minutes, int seconds) {
		if (hours < 0) {
			throw new IllegalArgumentException("Hours cannot be negative");
		} else if (minutes < 0) {
			throw new IllegalArgumentException("Minutes cannot be negative");
		} else if (seconds < 0) {
			throw new IllegalArgumentException("Seconds cannot be negative");
		}
		return (hours * 3600 + minutes * 60 + seconds);
	}

	static public int secondsToTicks(int seconds) {
		if (seconds < 0) {
			throw new IllegalArgumentException("Seconds cannot be negative");
		}
		return (seconds * 20);
	}

	static public int secondsToTicks(float seconds) {
		if (seconds < 0) {
			throw new IllegalArgumentException("Seconds cannot be negative");
		}
		return Math.round((seconds * 20));
	}

	static public int ticksToSeconds(int ticks) {
		if (ticks < 0) {
			throw new IllegalArgumentException("Ticks cannot be negative");
		}
		return (ticks / 20);
	}
}
