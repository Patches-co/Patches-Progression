package net.patches.progression.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.patches.progression.PatchesProgression;

public class ModItems {

    public static final Item CHAINMAIL_SHEET = registerItem("chainmail_sheet", new Item(new Item.Settings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(PatchesProgression.MOD_ID, name), item);
    }

    public static void registerModItems() {
        PatchesProgression.LOGGER.info("Registering Mods Items for " + PatchesProgression.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.add(CHAINMAIL_SHEET);
        });
    }


}
