package net.patches.progression.block;

import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.patches.progression.PatchesProgression;
import net.patches.progression.block.custom.PebbleBlock;
import net.patches.progression.block.custom.RockBlock;

public class ModBlocks {

    public static final Block FLINT_PEBBLE = registerBlockWithoutItem("flint_pebble",
            new PebbleBlock(AbstractBlock.Settings.create()
                .mapColor(MapColor.STONE_GRAY)
                .nonOpaque()
                .noCollision()
                .breakInstantly()
                .sounds(BlockSoundGroup.BASALT)));

    public static final Block ROCK_PEBBLE = registerBlockWithoutItem("rock_pebble",
            new RockBlock(AbstractBlock.Settings.create()
                .mapColor(MapColor.STONE_GRAY)
                .nonOpaque()
                .noCollision()
                .breakInstantly()
                .sounds(BlockSoundGroup.STONE)));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(PatchesProgression.MOD_ID, name), block);
    }

    private static Block registerBlockWithoutItem(String name, Block block) {
        return Registry.register(Registries.BLOCK, Identifier.of(PatchesProgression.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(PatchesProgression.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        PatchesProgression.LOGGER.info("Registering Mod Blocks for " + PatchesProgression.MOD_ID);
    }
}
