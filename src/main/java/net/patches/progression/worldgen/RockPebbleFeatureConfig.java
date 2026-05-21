package net.patches.progression.worldgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.gen.feature.FeatureConfig;

public record RockPebbleFeatureConfig(int tries, int xzSpread, int ySpread) implements FeatureConfig{
    
    public static final Codec<RockPebbleFeatureConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("tries").forGetter(RockPebbleFeatureConfig::tries),
            Codec.INT.fieldOf("xz_spread").forGetter(RockPebbleFeatureConfig::xzSpread),
            Codec.INT.fieldOf("y_spread").forGetter(RockPebbleFeatureConfig::ySpread)
    ).apply(instance, RockPebbleFeatureConfig::new));
}
