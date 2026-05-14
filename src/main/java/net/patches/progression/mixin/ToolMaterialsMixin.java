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
}