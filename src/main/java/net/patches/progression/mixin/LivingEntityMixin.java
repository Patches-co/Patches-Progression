package net.patches.progression.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.patches.progression.item.custom.KnifeItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Inject(method = "modifyAppliedDamage", at = @At("RETURN"), cancellable = true)
    private void applyKnifeBackstab(DamageSource source, float amount, CallbackInfoReturnable<Float> cir) {

        if (source.getAttacker() instanceof PlayerEntity player) {

            if (player.isSneaking()) {
                ItemStack stack = player.getMainHandStack();

                if (stack.getItem() instanceof KnifeItem) {
                    LivingEntity target = (LivingEntity) (Object) this;

                    Vec3d targetLook = target.getRotationVec(1.0F);
                    Vec3d toTarget = target.getPos().subtract(player.getPos());

                    Vec3d targetLook2D = new Vec3d(targetLook.x, 0, targetLook.z).normalize();
                    Vec3d toTarget2D = new Vec3d(toTarget.x, 0, toTarget.z).normalize();

                    if (toTarget2D.dotProduct(targetLook2D) > 0.5) {

                        float finalDamage = cir.getReturnValue();
                        cir.setReturnValue(finalDamage * 2.0f);

                        if (!target.getWorld().isClient() && target.getWorld() instanceof ServerWorld serverWorld) {
                            serverWorld.playSound(null, target.getBlockPos(), SoundEvents.ENTITY_PLAYER_ATTACK_CRIT, SoundCategory.PLAYERS, 1.0f, 1.5f);

                            int particleCount = Math.max(1, (int) (finalDamage));
                            serverWorld.spawnParticles(ParticleTypes.DAMAGE_INDICATOR,
                                    target.getX(), target.getBodyY(0.5), target.getZ(),
                                    particleCount, 0.2, 0.2, 0.2, 0.1);
                        }
                    }
                }
            }
        }
    }
}