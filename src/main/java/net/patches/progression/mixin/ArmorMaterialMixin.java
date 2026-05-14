package net.patches.progression.mixin;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ArmorMaterials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ArmorMaterial.class)
public class ArmorMaterialMixin {

    @Inject(method = "getProtection", at = @At("RETURN"), cancellable = true)
    private void patches$modifyProtection(ArmorItem.Type type, CallbackInfoReturnable<Integer> cir) {
        ArmorMaterial material = (ArmorMaterial) (Object) this;

        if (material == ArmorMaterials.LEATHER.value()) {
            cir.setReturnValue(switch (type) {
                case HELMET -> 1; case CHESTPLATE -> 3; case LEGGINGS -> 2; case BOOTS -> 1; case BODY -> 3;
            });
        } else if (material == ArmorMaterials.CHAIN.value()) {
            cir.setReturnValue(switch (type) {
                case HELMET -> 1; case CHESTPLATE -> 3; case LEGGINGS -> 2; case BOOTS -> 1; case BODY -> 4;
            });
        } else if (material == ArmorMaterials.GOLD.value()) {
            cir.setReturnValue(switch (type) {
                case HELMET -> 2; case CHESTPLATE -> 4; case LEGGINGS -> 3; case BOOTS -> 2; case BODY -> 6;
            });
        } else if (material == ArmorMaterials.IRON.value()) {
            cir.setReturnValue(switch (type) {
                case HELMET -> 3; case CHESTPLATE -> 5; case LEGGINGS -> 4; case BOOTS -> 3; case BODY -> 9;
            });
        } else if (material == ArmorMaterials.DIAMOND.value() || material == ArmorMaterials.NETHERITE.value()) {
            cir.setReturnValue(switch (type) {
                case HELMET -> 4; case CHESTPLATE -> 7; case LEGGINGS -> 5; case BOOTS -> 4; case BODY -> 12;
            });
        }
    }

    @Inject(method = "toughness", at = @At("RETURN"), cancellable = true)
    private void patches$modifyToughness(CallbackInfoReturnable<Float> cir) {
        ArmorMaterial material = (ArmorMaterial) (Object) this;

        if (material == ArmorMaterials.DIAMOND.value()) {
            cir.setReturnValue(1.0F);
        } else if (material == ArmorMaterials.NETHERITE.value()) {
            cir.setReturnValue(2.0F);
        }
    }

    @Inject(method = "knockbackResistance", at = @At("RETURN"), cancellable = true)
    private void patches$modifyKnockback(CallbackInfoReturnable<Float> cir) {
        ArmorMaterial material = (ArmorMaterial) (Object) this;

        if (material == ArmorMaterials.NETHERITE.value()) {
            cir.setReturnValue(0.1F);
        }
    }
}