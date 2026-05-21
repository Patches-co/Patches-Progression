package net.patches.progression.block.enums;

import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.math.random.Random;

public enum FlintVariants implements StringIdentifiable {
    SMALL("small"),
    MEDIUM("medium"),
    CLUSTER("cluster");

    private final String name;

    FlintVariants(String name) {
        this.name = name;
    }

    @Override
    public String asString() {
        return this.name;
    }

    public static FlintVariants random(Random random) {
        return values()[random.nextInt(values().length)];
    }
}
