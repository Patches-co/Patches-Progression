package net.patches.progression.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.SmithingTransformRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
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

        offerCopperUpgradeRecipe(exporter, Items.CHAINMAIL_HELMET, RecipeCategory.COMBAT, ModItems.COPPER_HELMET);
        offerCopperUpgradeRecipe(exporter, Items.CHAINMAIL_CHESTPLATE, RecipeCategory.COMBAT, ModItems.COPPER_CHESTPLATE);
        offerCopperUpgradeRecipe(exporter, Items.CHAINMAIL_LEGGINGS, RecipeCategory.COMBAT, ModItems.COPPER_LEGGINGS);
        offerCopperUpgradeRecipe(exporter, Items.CHAINMAIL_BOOTS, RecipeCategory.COMBAT, ModItems.COPPER_BOOTS);
    }

    public static void offerCopperUpgradeRecipe(RecipeExporter exporter, Item input, RecipeCategory category, Item result) {
        SmithingTransformRecipeJsonBuilder.create(
                        Ingredient.ofItems(ModItems.COPPER_UPGRADE_SMITHING_TEMPLATE), Ingredient.ofItems(input), Ingredient.ofItems(ModItems.COPPER_BAR), category, result
                )
                .criterion("has_copper_bar", conditionsFromItem(ModItems.COPPER_BAR))
                .offerTo(exporter, getItemPath(result) + "_smithing");
    }
}
