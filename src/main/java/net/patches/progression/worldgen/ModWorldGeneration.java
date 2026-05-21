package net.patches.progression.worldgen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.patches.progression.PatchesProgression;

public class ModWorldGeneration {
    public static final RegistryKey<PlacedFeature> FLINT_PEBBLE_PLACED_KEY = RegistryKey.of(
            RegistryKeys.PLACED_FEATURE,
            Identifier.of(PatchesProgression.MOD_ID, "flint_pebble")
    );

    public static final RegistryKey<PlacedFeature> FLINT_PEBBLE_GRAVEL_PLACED_KEY = RegistryKey.of(
            RegistryKeys.PLACED_FEATURE,
            Identifier.of(PatchesProgression.MOD_ID, "flint_pebble_gravel")
    );

    public static final RegistryKey<PlacedFeature> ROCK_PEBBLE_PLACED_KEY = RegistryKey.of(
            RegistryKeys.PLACED_FEATURE,
            Identifier.of(PatchesProgression.MOD_ID, "rock_pebble")
    );

    public static void register() {
        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.VEGETAL_DECORATION,
                FLINT_PEBBLE_PLACED_KEY
        );

        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.VEGETAL_DECORATION,
                FLINT_PEBBLE_GRAVEL_PLACED_KEY
        );

        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.VEGETAL_DECORATION,
                ROCK_PEBBLE_PLACED_KEY
        );
    }
}
