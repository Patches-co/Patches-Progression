package net.patches.progression.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.patches.progression.PatchesProgression;
import net.patches.progression.block.ModBlocks;
import net.patches.progression.item.custom.KnappedRockItem;
import net.patches.progression.item.custom.RockItem;

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

    // SMITHING TEMPLATES
    public static final Item COPPER_UPGRADE_SMITHING_TEMPLATE = createTemplate("copper", "copper");
    public static final Item IRON_UPGRADE_SMITHING_TEMPLATE = createTemplate("iron", "iron");
    public static final Item GOLD_UPGRADE_SMITHING_TEMPLATE = createTemplate("gold", "gold");
    public static final Item DIAMOND_UPGRADE_SMITHING_TEMPLATE = createTemplate("diamond", "diamond");

    // NUGGETS
    public static final Item ROCK_PEBBLE = registerItem("rock_pebble",
            new RockItem(ModBlocks.ROCK_PEBBLE, new Item.Settings()));
    public static final Item KNAPPED_ROCK = registerItem("knapped_rock",
            new KnappedRockItem(new Item.Settings()));

    public static final Item COPPER_NUGGET = registerItem("copper_nugget", new Item(new Item.Settings()));
    //public static final Item DIAMOND_SHARD = registerItem("diamond_shard", new Item(new Item.Settings()));

    // SHEETS
    public static final Item CHAINMAIL_SHEET = registerItem("chainmail_sheet", new Item(new Item.Settings()));
    public static final Item ROTTEN_LEATHER = registerItem("rotten_leather", new Item(new Item.Settings()));


    // BARS
    public static final Item COPPER_BAR = registerItem("copper_bar", new Item(new Item.Settings()));
    public static final Item IRON_BAR = registerItem("iron_bar", new Item(new Item.Settings()));
    public static final Item GOLD_BAR = registerItem("gold_bar", new Item(new Item.Settings()));
    public static final Item DIAMOND_BAR = registerItem("diamond_bar", new Item(new Item.Settings()));

    // COPPER ARMOR
    public static final Item COPPER_HELMET = registerItem("copper_helmet", new ArmorItem(ModArmorMaterials.COPPER_ARMOR_MATERIAL, ArmorItem.Type.HELMET, new Item.Settings()
            .maxDamage(ArmorItem.Type.HELMET.getMaxDamage(11))));
    public static final Item COPPER_CHESTPLATE = registerItem("copper_chestplate", new ArmorItem(ModArmorMaterials.COPPER_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Settings()
            .maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(11))));
    public static final Item COPPER_LEGGINGS = registerItem("copper_leggings", new ArmorItem(ModArmorMaterials.COPPER_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Settings()
            .maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(11))));
    public static final Item COPPER_BOOTS = registerItem("copper_boots", new ArmorItem(ModArmorMaterials.COPPER_ARMOR_MATERIAL, ArmorItem.Type.BOOTS, new Item.Settings()
            .maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(11))));

    public static final Item COPPER_SWORD = registerItem("copper_sword",
            new SwordItem(ModToolMaterials.COPPER, new Item.Settings()
                    .attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.COPPER, 3, -2.4F))));

    public static final Item COPPER_PICKAXE = registerItem("copper_pickaxe",
            new PickaxeItem(ModToolMaterials.COPPER, new Item.Settings()
                    .attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.COPPER, 1, -2.8F))));

    public static final Item COPPER_AXE = registerItem("copper_axe",
            new AxeItem(ModToolMaterials.COPPER, new Item.Settings()
                    .attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterials.COPPER, 6, -3.2F))));

    public static final Item COPPER_SHOVEL = registerItem("copper_shovel",
            new ShovelItem(ModToolMaterials.COPPER, new Item.Settings()
                    .attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterials.COPPER, 1.5F, -3.0F))));

    public static final Item COPPER_HOE = registerItem("copper_hoe",
            new HoeItem(ModToolMaterials.COPPER, new Item.Settings()
                    .attributeModifiers(HoeItem.createAttributeModifiers(ModToolMaterials.COPPER, -1, -2.0F))));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(PatchesProgression.MOD_ID, name), item);
    }

    public static void registerModItems() {
        PatchesProgression.LOGGER.info("Registering Mods Items for " + PatchesProgression.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.addAfter(Items.RABBIT_HIDE, CHAINMAIL_SHEET);

            fabricItemGroupEntries.addAfter(Items.IRON_NUGGET, COPPER_NUGGET);
            fabricItemGroupEntries.addBefore(Items.LEATHER, ROTTEN_LEATHER);

            fabricItemGroupEntries.addBefore(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE,
                    COPPER_UPGRADE_SMITHING_TEMPLATE, GOLD_UPGRADE_SMITHING_TEMPLATE,
                    IRON_UPGRADE_SMITHING_TEMPLATE, DIAMOND_UPGRADE_SMITHING_TEMPLATE);

            fabricItemGroupEntries.addAfter(Items.COPPER_INGOT, COPPER_BAR);
            fabricItemGroupEntries.addAfter(Items.GOLD_INGOT, GOLD_BAR);
            fabricItemGroupEntries.addAfter(Items.IRON_INGOT, IRON_BAR);
            fabricItemGroupEntries.addAfter(Items.DIAMOND, DIAMOND_BAR);

            fabricItemGroupEntries.addAfter(Items.FLINT, ModBlocks.ROCK_PEBBLE, KNAPPED_ROCK);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.addAfter(Items.CHAINMAIL_BOOTS,
                    COPPER_HELMET, COPPER_CHESTPLATE, COPPER_LEGGINGS, COPPER_BOOTS);

            fabricItemGroupEntries.addAfter(Items.STONE_SWORD, COPPER_SWORD);
            fabricItemGroupEntries.addAfter(Items.STONE_AXE, COPPER_AXE);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.addAfter(Items.STONE_HOE,
                    COPPER_SHOVEL, COPPER_PICKAXE, COPPER_AXE, COPPER_HOE);
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
