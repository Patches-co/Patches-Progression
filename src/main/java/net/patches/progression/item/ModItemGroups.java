package net.patches.progression.item;


import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.patches.progression.PatchesProgression;
import net.patches.progression.block.ModBlocks;

public class ModItemGroups {
    public static final ItemGroup PATCHES_ITEMS = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(PatchesProgression.MOD_ID, "patches_items"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.CHAINMAIL_SHEET))
                    .displayName(Text.translatable("itemgroup.patches-progression.patches_items"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.CHAINMAIL_SHEET);
                        entries.add(ModItems.RAW_ROSE);
                        entries.add(ModItems.ROSE_INGOT);
                        entries.add(ModItems.RAW_ROSE_NUGGET);
                        entries.add(ModItems.ROSE_NUGGET);
                    }).build());

    public static final ItemGroup PATCHES_BLOCKS = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(PatchesProgression.MOD_ID, "patches_blocks"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModBlocks.ROSE_BLOCK))
                    .displayName(Text.translatable("itemgroup.patches-progression.patches_blocks"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModBlocks.ROSE_BLOCK);
                        entries.add(ModBlocks.RAW_ROSE_BLOCK);
                        entries.add(ModBlocks.POLISHED_ROSE);
                        entries.add(ModBlocks.POLISHED_CUT_ROSE);
                        entries.add(ModBlocks.NETHER_ROSE_ORE);
                        entries.add(ModBlocks.BLACKSTONE_ROSE_ORE);
                    }).build());


    public static void registerItemGroups() {
        PatchesProgression.LOGGER.info("Registering Item Groups for " + PatchesProgression.MOD_ID);
    }

}

