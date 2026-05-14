package net.patches.progression.mixin;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.LoreComponent;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.recipe.SmithingTransformRecipe;
import net.minecraft.recipe.input.SmithingRecipeInput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(SmithingTransformRecipe.class)
public class SmithingTransformRecipeMixin {

    @Inject(method = "craft(Lnet/minecraft/recipe/input/SmithingRecipeInput;Lnet/minecraft/registry/RegistryWrapper$WrapperLookup;)Lnet/minecraft/item/ItemStack;", at = @At("RETURN"), cancellable = true)
    private void patches$addChainmailBonus(SmithingRecipeInput input, RegistryWrapper.WrapperLookup registries, CallbackInfoReturnable<ItemStack> cir) {
        ItemStack result = cir.getReturnValue();
        ItemStack base = input.base();

        if (!base.isEmpty() && !result.isEmpty() && base.getItem() instanceof ArmorItem baseArmor && result.getItem() instanceof ArmorItem resultArmor) {

            boolean hasChainBonus = (baseArmor.getMaterial() == ArmorMaterials.CHAIN);

            if (!hasChainBonus) {
                AttributeModifiersComponent baseMods = base.getOrDefault(DataComponentTypes.ATTRIBUTE_MODIFIERS, AttributeModifiersComponent.DEFAULT);
                for (AttributeModifiersComponent.Entry mod : baseMods.modifiers()) {
                    if (mod.modifier().id().getPath().startsWith("chain_toughness_")) {
                        hasChainBonus = true;
                        break;
                    }
                }
            }

            if (hasChainBonus) {
                LoreComponent currentLore = result.getOrDefault(DataComponentTypes.LORE, new LoreComponent(List.of()));
                boolean loreExists = currentLore.lines().stream().anyMatch(text -> text.getString().contains("Chainmail"));

                if (!loreExists) {
                    List<Text> newLoreLines = new java.util.ArrayList<>(currentLore.lines());
                    newLoreLines.add(Text.literal("- Chainmailed").formatted(Formatting.GRAY, Formatting.ITALIC));
                    result.set(DataComponentTypes.LORE, new LoreComponent(newLoreLines));
                }

                AttributeModifiersComponent.Builder builder = AttributeModifiersComponent.builder();

                AttributeModifierSlot slotGroup = switch (resultArmor.getType()) {
                    case HELMET -> AttributeModifierSlot.HEAD;
                    case CHESTPLATE -> AttributeModifierSlot.CHEST;
                    case LEGGINGS -> AttributeModifierSlot.LEGS;
                    case BOOTS -> AttributeModifierSlot.FEET;
                    case BODY -> AttributeModifierSlot.BODY;
                };

                String pieceName = resultArmor.getType().name().toLowerCase();
                ArmorMaterial material = resultArmor.getMaterial().value();

                int baseDefense = material.getProtection(resultArmor.getType());
                if (baseDefense > 0) {
                    builder.add(
                            EntityAttributes.GENERIC_ARMOR,
                            new EntityAttributeModifier(Identifier.of("patches", "base_armor_" + pieceName), baseDefense, EntityAttributeModifier.Operation.ADD_VALUE),
                            slotGroup
                    );
                }

                float totalToughness = material.toughness() + 1.0F;
                if (totalToughness > 0) {
                    builder.add(
                            EntityAttributes.GENERIC_ARMOR_TOUGHNESS,
                            new EntityAttributeModifier(Identifier.of("patches", "chain_toughness_" + pieceName), totalToughness, EntityAttributeModifier.Operation.ADD_VALUE),
                            slotGroup
                    );
                }

                result.set(DataComponentTypes.ATTRIBUTE_MODIFIERS, builder.build());
                cir.setReturnValue(result);
            }
        }
    }
}