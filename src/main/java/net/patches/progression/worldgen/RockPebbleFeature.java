package net.patches.progression.worldgen;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.patches.progression.block.ModBlocks;
import net.patches.progression.block.custom.RockBlock;
import net.patches.progression.block.enums.RockVariants;

public class RockPebbleFeature extends Feature<RockPebbleFeatureConfig>{

    public RockPebbleFeature(Codec<RockPebbleFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(FeatureContext<RockPebbleFeatureConfig> context) {
        RockPebbleFeatureConfig config = context.getConfig();
        BlockPos origin = context.getOrigin();
        Random random = context.getRandom();
        StructureWorldAccess world = context.getWorld();
        boolean placedAny = false;

        for (int attempt = 0; attempt < config.tries(); attempt++) {
            BlockPos pos = origin.add(
                    random.nextBetween(-config.xzSpread(), config.xzSpread()),
                    random.nextBetween(-config.ySpread(), config.ySpread()),
                    random.nextBetween(-config.xzSpread(), config.xzSpread())
            );

            if (!tryPlace(world, pos, random)) {
                continue;
            }

            placedAny = true;
        }

        return placedAny;
    }

    private static boolean tryPlace(StructureWorldAccess world, BlockPos pos, Random random) {
        BlockState below = world.getBlockState(pos.down());

        boolean isValidGround = below.isOf(Blocks.DIRT) || below.isOf(Blocks.COARSE_DIRT) || below.isOf(Blocks.STONE);

        if (!isValidGround) {
            return false;
        }

        BlockState existing = world.getBlockState(pos);
        if (!existing.isReplaceable()) {
            return false;
        }

        RockVariants variant = RockVariants.random(random);
        Direction facing = Direction.Type.HORIZONTAL.random(random);

        BlockState state = ModBlocks.ROCK_PEBBLE.getDefaultState()
                .with(RockBlock.ROCK_TYPE, variant)
                .with(RockBlock.FACING, facing);

        world.setBlockState(pos, state, Block.NOTIFY_LISTENERS);
        return true;
    }
}
