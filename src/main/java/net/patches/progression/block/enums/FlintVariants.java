package net.patches.progression.block.enums;

import net.minecraft.util.StringIdentifiable;

public enum FlintVariants implements StringIdentifiable {
    SMALL("small"), MEDIUM("medium");

    private final String name;

    FlintVariants(String name) {
        this.name = name;
    }

    @Override
    public String asString() {
        return this.name;
    }
}