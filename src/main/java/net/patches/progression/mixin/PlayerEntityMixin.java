package net.patches.progression.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {

    @Inject(method = "getBlockBreakingSpeed", at = @At("RETURN"), cancellable = true)
    private void patches$slowDownIneffectiveMining(BlockState block, CallbackInfoReturnable<Float> cir) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        ItemStack handItem = player.getMainHandStack();

        if (handItem.getMiningSpeedMultiplier(block) <= 1.0f) {
            float originalSpeed = cir.getReturnValue();
            cir.setReturnValue(originalSpeed / 5.0f);
        }
    }
}