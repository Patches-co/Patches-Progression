package net.patches.progression.mixin;

import net.minecraft.item.ToolMaterials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ToolMaterials.class)
public class ToolMaterialsMixin {

    @Inject(method = "getAttackDamage", at = @At("RETURN"), cancellable = true)
    private void patches$modifyGoldDamage(CallbackInfoReturnable<Float> cir) {
        if ((Object) this == ToolMaterials.GOLD) {
            cir.setReturnValue(1.0F);
        }
    }

    @Inject(method = "getMiningSpeedMultiplier", at = @At("HEAD"), cancellable = true)
    private void nerfStoneMiningSpeed(CallbackInfoReturnable<Float> cir) {
        if ((Object) this == ToolMaterials.STONE) {
            cir.setReturnValue(2.0F);
        }
    }

    @Inject(method = "getAttackDamage", at = @At("HEAD"), cancellable = true)
    private void nerfStoneAttackDamage(CallbackInfoReturnable<Float> cir) {
        if ((Object) this == ToolMaterials.STONE) {
            cir.setReturnValue(0.0F);
        }
    }

    @Inject(method = "getDurability", at = @At("HEAD"), cancellable = true)
    private void nerfStoneDurability(CallbackInfoReturnable<Integer> cir) {
        if ((Object) this == ToolMaterials.STONE) {
            cir.setReturnValue(59);
        }
    }
}