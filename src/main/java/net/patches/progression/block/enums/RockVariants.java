package net.patches.progression.block.enums;

import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.math.random.Random;

public enum RockVariants implements StringIdentifiable {
    SMALL("small"),
    MEDIUM("medium"),
    LARGE("large"),
    EXTRA_LARGE("extra_large");

    private final String name;

    RockVariants(String name) {
        this.name = name;
    }

    @Override
    public String asString() {
        return this.name;
    }

    public static RockVariants random(Random random) {
        return values()[random.nextInt(values().length)];
    }
}