package net.patches.progression.item;


import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.patches.progression.PatchesProgression;
import net.patches.progression.block.ModBlocks;

public class ModItemGroups {
    public static final ItemGroup PATCHES_PROGRESSION = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(PatchesProgression.MOD_ID, "patches_progression"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.DIAMOND_UPGRADE_SMITHING_TEMPLATE))
                    .displayName(Text.translatable("itemgroup.patches-progression.patches_progression"))
                    .entries((displayContext, entries) -> {
                        for (Item item : Registries.ITEM) {
                            Identifier id = Registries.ITEM.getId(item);
                            if (id.getNamespace().equals(PatchesProgression.MOD_ID)) {
                                entries.add(item);
                            }
                        }
                    }).build());


    public static void registerItemGroups() {
        PatchesProgression.LOGGER.info("Registering Item Groups for " + PatchesProgression.MOD_ID);
    }

}

