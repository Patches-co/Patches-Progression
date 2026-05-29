package net.patches.progression.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import net.patches.progression.block.ModBlocks;
import net.patches.progression.item.ModItems;
 
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        List<ItemConvertible> COPPER_ARMORS = List.of(ModItems.COPPER_HELMET, ModItems.COPPER_CHESTPLATE, ModItems.COPPER_LEGGINGS, ModItems.COPPER_BOOTS);

        offerBlasting(exporter, COPPER_ARMORS, RecipeCategory.MISC, ModItems.COPPER_NUGGET, 0.25f, 100, "copper_ingot");
        offerSmelting(exporter, COPPER_ARMORS, RecipeCategory.MISC, ModItems.COPPER_NUGGET, 0.25f, 200, "copper_ingot");
        offerSmelting(exporter, List.of(ModItems.ROTTEN_LEATHER), RecipeCategory.MISC, Items.LEATHER, 0.15f, 200, "rotten_leather");

        CookingRecipeJsonBuilder.createSmoking(Ingredient.ofItems(ModItems.ROTTEN_LEATHER), RecipeCategory.MISC, Items.LEATHER, 0.15f, 100)
                .criterion(hasItem(ModItems.ROTTEN_LEATHER), conditionsFromItem(ModItems.ROTTEN_LEATHER))
                .offerTo(exporter, Identifier.of("patches-progression", "leather_from_smoking"));
        CookingRecipeJsonBuilder.createCampfireCooking(Ingredient.ofItems(ModItems.ROTTEN_LEATHER), RecipeCategory.MISC, Items.LEATHER, 0.15f, 600)
                .criterion(hasItem(ModItems.ROTTEN_LEATHER), conditionsFromItem(ModItems.ROTTEN_LEATHER))
                .offerTo(exporter, Identifier.of("patches-progression", "leather_from_campfire"));

        offerCopperUpgradeRecipe(exporter, Items.CHAINMAIL_HELMET, RecipeCategory.COMBAT, ModItems.COPPER_HELMET);
        offerCopperUpgradeRecipe(exporter, Items.CHAINMAIL_CHESTPLATE, RecipeCategory.COMBAT, ModItems.COPPER_CHESTPLATE);
        offerCopperUpgradeRecipe(exporter, Items.CHAINMAIL_LEGGINGS, RecipeCategory.COMBAT, ModItems.COPPER_LEGGINGS);
        offerCopperUpgradeRecipe(exporter, Items.CHAINMAIL_BOOTS, RecipeCategory.COMBAT, ModItems.COPPER_BOOTS);

        offerLeatherToMetalSmithing(exporter, ModItems.COPPER_UPGRADE_SMITHING_TEMPLATE, Items.LEATHER_HELMET, ModItems.COPPER_BAR, ModItems.COPPER_HELMET);
        offerLeatherToMetalSmithing(exporter, ModItems.COPPER_UPGRADE_SMITHING_TEMPLATE, Items.LEATHER_CHESTPLATE, ModItems.COPPER_BAR, ModItems.COPPER_CHESTPLATE);
        offerLeatherToMetalSmithing(exporter, ModItems.COPPER_UPGRADE_SMITHING_TEMPLATE, Items.LEATHER_LEGGINGS, ModItems.COPPER_BAR, ModItems.COPPER_LEGGINGS);
        offerLeatherToMetalSmithing(exporter, ModItems.COPPER_UPGRADE_SMITHING_TEMPLATE, Items.LEATHER_BOOTS, ModItems.COPPER_BAR, ModItems.COPPER_BOOTS);

        offerLeatherToMetalSmithing(exporter, ModItems.IRON_UPGRADE_SMITHING_TEMPLATE, Items.LEATHER_HELMET, ModItems.IRON_BAR, Items.IRON_HELMET);
        offerLeatherToMetalSmithing(exporter, ModItems.IRON_UPGRADE_SMITHING_TEMPLATE, Items.LEATHER_CHESTPLATE, ModItems.IRON_BAR, Items.IRON_CHESTPLATE);
        offerLeatherToMetalSmithing(exporter, ModItems.IRON_UPGRADE_SMITHING_TEMPLATE, Items.LEATHER_LEGGINGS, ModItems.IRON_BAR, Items.IRON_LEGGINGS);
        offerLeatherToMetalSmithing(exporter, ModItems.IRON_UPGRADE_SMITHING_TEMPLATE, Items.LEATHER_BOOTS, ModItems.IRON_BAR, Items.IRON_BOOTS);

        offerLeatherToMetalSmithing(exporter, ModItems.GOLD_UPGRADE_SMITHING_TEMPLATE, Items.LEATHER_HELMET, ModItems.GOLD_BAR, Items.GOLDEN_HELMET);
        offerLeatherToMetalSmithing(exporter, ModItems.GOLD_UPGRADE_SMITHING_TEMPLATE, Items.LEATHER_CHESTPLATE, ModItems.GOLD_BAR, Items.GOLDEN_CHESTPLATE);
        offerLeatherToMetalSmithing(exporter, ModItems.GOLD_UPGRADE_SMITHING_TEMPLATE, Items.LEATHER_LEGGINGS, ModItems.GOLD_BAR, Items.GOLDEN_LEGGINGS);
        offerLeatherToMetalSmithing(exporter, ModItems.GOLD_UPGRADE_SMITHING_TEMPLATE, Items.LEATHER_BOOTS, ModItems.GOLD_BAR, Items.GOLDEN_BOOTS);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.KNAPPED_ROCK).input(ModBlocks.ROCK_PEBBLE).input(Items.FLINT)
                .criterion(hasItem(ModBlocks.ROCK_PEBBLE), conditionsFromItem(ModBlocks.ROCK_PEBBLE))
                .offerTo(exporter);

        offerReversibleCompactingRecipes(exporter, RecipeCategory.COMBAT, ModBlocks.ROCK_PEBBLE, RecipeCategory.BUILDING_BLOCKS, Items.COBBLESTONE);
        offer2x2CompactingRecipe(exporter, RecipeCategory.MISC, ModItems.ROTTEN_LEATHER, Items.ROTTEN_FLESH);


        // FERRAMENTAS DE COBRE
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.COPPER_SWORD, 1)
                .pattern(" C ")
                .pattern(" C ")
                .pattern(" S ")
                .input('C', Items.COPPER_INGOT)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.COPPER_INGOT), conditionsFromItem(Items.COPPER_INGOT))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.COPPER_PICKAXE, 1)
                .pattern("CCC")
                .pattern(" S ")
                .pattern(" S ")
                .input('C', Items.COPPER_INGOT)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.COPPER_INGOT), conditionsFromItem(Items.COPPER_INGOT))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.COPPER_AXE, 1)
                .pattern("CC ")
                .pattern("CS ")
                .pattern(" S ")
                .input('C', Items.COPPER_INGOT)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.COPPER_INGOT), conditionsFromItem(Items.COPPER_INGOT))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.COPPER_SHOVEL, 1)
                .pattern(" C ")
                .pattern(" S ")
                .pattern(" S ")
                .input('C', Items.COPPER_INGOT)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.COPPER_INGOT), conditionsFromItem(Items.COPPER_INGOT))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.COPPER_HOE, 1)
                .pattern("CC ")
                .pattern(" S ")
                .pattern(" S ")
                .input('C', Items.COPPER_INGOT)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.COPPER_INGOT), conditionsFromItem(Items.COPPER_INGOT))
                .offerTo(exporter);
    }

    public static void offerCopperUpgradeRecipe(RecipeExporter exporter, Item input, RecipeCategory category, Item result) {
        SmithingTransformRecipeJsonBuilder.create(
                        Ingredient.ofItems(ModItems.COPPER_UPGRADE_SMITHING_TEMPLATE), Ingredient.ofItems(input), Ingredient.ofItems(ModItems.COPPER_BAR), category, result
                )
                .criterion("has_copper_bar", conditionsFromItem(ModItems.COPPER_BAR))
                .offerTo(exporter, getItemPath(result) + "_smithing");
    }

    private void offerLeatherToMetalSmithing(RecipeExporter exporter, Item template, Item leatherBase, Item addition, Item result) {
        SmithingTransformRecipeJsonBuilder.create(
                        Ingredient.ofItems(template),
                        Ingredient.ofItems(leatherBase),
                        Ingredient.ofItems(addition),
                        RecipeCategory.COMBAT,
                        result
                )
                .criterion(hasItem(addition), conditionsFromItem(addition))
                .offerTo(exporter, Identifier.of("patches-progression", getItemPath(result) + "_from_leather_smithing"));
    }
}
