package net.patches.progression.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.patches.progression.PatchesProgression;

public class ModBlocks {
    public static final Block NETHER_ROSE_ORE = registerBlock("nether_rose_ore",
            new Block(AbstractBlock.Settings.create().strength(4f)
                    .requiresTool().sounds(BlockSoundGroup.NETHER_ORE)));

    public static final Block BLACKSTONE_ROSE_ORE = registerBlock("blackstone_rose_ore",
            new Block(AbstractBlock.Settings.create().strength(4f)
                    .requiresTool().sounds(BlockSoundGroup.GILDED_BLACKSTONE)));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(PatchesProgression.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(PatchesProgression.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        PatchesProgression.LOGGER.info("Registering Mod Blocks for " + PatchesProgression.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(fabricItemGroupEntries -> {
                fabricItemGroupEntries.add(ModBlocks.NETHER_ROSE_ORE);
                fabricItemGroupEntries.add(ModBlocks.BLACKSTONE_ROSE_ORE);
        });
    }
}
