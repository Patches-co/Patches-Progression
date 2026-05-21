package net.patches.progression.worldgen;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.Feature;
import net.patches.progression.PatchesProgression;

public class ModFeatures {
    public static final Feature<FlintPebbleFeatureConfig> FLINT_PEBBLE = Registry.register(
            Registries.FEATURE,
            Identifier.of(PatchesProgression.MOD_ID, "flint_pebble"),
            new FlintPebbleFeature(FlintPebbleFeatureConfig.CODEC)
    );
    public static final Feature<RockPebbleFeatureConfig> ROCK_PEBBLE = Registry.register(
            Registries.FEATURE,
            Identifier.of(PatchesProgression.MOD_ID, "rock_pebble"),
            new RockPebbleFeature(RockPebbleFeatureConfig.CODEC)
    );

    public static void register() {
        PatchesProgression.LOGGER.info("Registering Mod Features for " + PatchesProgression.MOD_ID);
    }
}
