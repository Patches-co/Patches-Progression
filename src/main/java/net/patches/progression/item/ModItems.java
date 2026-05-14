package net.patches.progression.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.patches.progression.PatchesProgression;

import java.util.List;

public class ModItems {

    // ICONS
    private static final List<Identifier> EMPTY_BASE_SLOTS = List.of(
            Identifier.of("item/empty_armor_slot_helmet"),
            Identifier.of("item/empty_armor_slot_chestplate"),
            Identifier.of("item/empty_armor_slot_leggings"),
            Identifier.of("item/empty_armor_slot_boots"),
            Identifier.of("item/empty_slot_sword"),
            Identifier.of("item/empty_slot_pickaxe"),
            Identifier.of("item/empty_slot_axe"),
            Identifier.of("item/empty_slot_hoe"),
            Identifier.of("item/empty_slot_shovel")
    );

    private static final List<Identifier> EMPTY_ADDITION_SLOTS = List.of(
            Identifier.of("item/empty_slot_diamond"),
            Identifier.of("item/empty_slot_ingot")
    );

    // --- SMITHING TEMPLATES ---
    public static final Item COPPER_UPGRADE_SMITHING_TEMPLATE = createTemplate("copper", "copper");
    public static final Item IRON_UPGRADE_SMITHING_TEMPLATE = createTemplate("iron", "iron");
    public static final Item GOLD_UPGRADE_SMITHING_TEMPLATE = createTemplate("gold", "gold");
    public static final Item DIAMOND_UPGRADE_SMITHING_TEMPLATE = createTemplate("diamond", "diamond");

    // --- SHEETS ---
    public static final Item CHAINMAIL_SHEET = registerItem("chainmail_sheet", new Item(new Item.Settings()));
    public static final Item COPPER_SHEET = registerItem("copper_sheet", new Item(new Item.Settings()));
    public static final Item IRON_SHEET = registerItem("iron_sheet", new Item(new Item.Settings()));
    public static final Item GOLD_SHEET = registerItem("gold_sheet", new Item(new Item.Settings()));

    // METAL NUGGETS
    public static final Item COPPER_NUGGET = registerItem("copper_nugget", new Item(new Item.Settings()));
    public static final Item RAW_COPPER_NUGGET = registerItem("raw_copper_nugget", new Item(new Item.Settings()));
    public static final Item RAW_IRON_NUGGET = registerItem("raw_iron_nugget", new Item(new Item.Settings()));
    public static final Item RAW_GOLD_NUGGET = registerItem("raw_gold_nugget", new Item(new Item.Settings()));

    public static final Item DIAMOND_SHARD = registerItem("diamond_shard", new Item(new Item.Settings()));


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(PatchesProgression.MOD_ID, name), item);
    }

    public static void registerModItems() {
        PatchesProgression.LOGGER.info("Registering Mods Items for " + PatchesProgression.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.addAfter(Items.RABBIT_HIDE, CHAINMAIL_SHEET, IRON_SHEET, COPPER_SHEET, GOLD_SHEET);
            fabricItemGroupEntries.addBefore(Items.COAL, RAW_IRON_NUGGET, RAW_COPPER_NUGGET, RAW_GOLD_NUGGET);
            fabricItemGroupEntries.addAfter(Items.IRON_NUGGET, COPPER_NUGGET);
            fabricItemGroupEntries.addBefore(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE,
                    COPPER_UPGRADE_SMITHING_TEMPLATE, GOLD_UPGRADE_SMITHING_TEMPLATE,
                    IRON_UPGRADE_SMITHING_TEMPLATE, DIAMOND_UPGRADE_SMITHING_TEMPLATE);
            fabricItemGroupEntries.addBefore(Items.DIAMOND, DIAMOND_SHARD);
        });
    }

    public static Item createTemplate(String name, String ingredientKey) {
        return registerItem(name + "_upgrade_template", new SmithingTemplateItem(
            Text.translatable("tooltip.patches-progression."+ ingredientKey + "_upgrade.applies_to").formatted(Formatting.BLUE),
            Text.translatable("tooltip.patches-progression." + ingredientKey + "_upgrade.ingredients").formatted(Formatting.BLUE),
            Text.translatable("item.patches-progression." + name + "_upgrade_smithing_template.title").formatted(Formatting.GRAY),
            Text.translatable("item.patches-progression.smithing_template." + name + "_base_slot"),
            Text.translatable("item.patches-progression.smithing_template." + ingredientKey + "_additions_slot"),
            EMPTY_BASE_SLOTS, EMPTY_ADDITION_SLOTS));
    }
}
