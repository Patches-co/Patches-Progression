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

    public static final Item ROSE_NUGGET = registerItem("rose_nugget", new Item(new Item.Settings()));
    public static final Item RAW_ROSE_NUGGET = registerItem("raw_rose_nugget", new Item(new Item.Settings()));
    public static final Item ROSE_INGOT = registerItem("rose_ingot", new Item(new Item.Settings()));
    public static final Item RAW_ROSE = registerItem("raw_rose", new Item(new Item.Settings()));

    public static final Item COPPER_UPGRADE_TEMPLATE = registerItem("copper_upgrade_template", new Item(new Item.Settings()));
    public static final Item GOLD_UPGRADE_TEMPLATE = registerItem("gold_upgrade_template", new Item(new Item.Settings()));
    public static final Item IRON_UPGRADE_TEMPLATE = registerItem("iron_upgrade_template", new Item(new Item.Settings()));
    public static final Item DIAMOND_UPGRADE_TEMPLATE = registerItem("diamond_upgrade_template", new Item(new Item.Settings()));
    public static final Item ROSE_UPGRADE_TEMPLATE = registerItem("rose_upgrade_template", new Item(new Item.Settings()));

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
