package net.patches.progression.worldgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.gen.feature.FeatureConfig;

public record FlintPebbleFeatureConfig(int tries, int xzSpread, int ySpread) implements FeatureConfig {
    public static final Codec<FlintPebbleFeatureConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("tries").forGetter(FlintPebbleFeatureConfig::tries),
            Codec.INT.fieldOf("xz_spread").forGetter(FlintPebbleFeatureConfig::xzSpread),
            Codec.INT.fieldOf("y_spread").forGetter(FlintPebbleFeatureConfig::ySpread)
    ).apply(instance, FlintPebbleFeatureConfig::new));
}
