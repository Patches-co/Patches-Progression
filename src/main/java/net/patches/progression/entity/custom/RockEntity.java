package net.patches.progression.entity.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.patches.progression.entity.ModEntities;
import net.patches.progression.item.ModItems;

public class RockEntity extends ThrownItemEntity {

    public RockEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public RockEntity(World world, LivingEntity owner) {
        super(ModEntities.ROCK_PROJECTILE, owner, world);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.ROCK_PEBBLE;
    }

    @Override
    protected double getGravity() {
        return 0.08;
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);

        if (!this.getWorld().isClient) {
            BlockPos hitPos = blockHitResult.getBlockPos();
            BlockState hitState = this.getWorld().getBlockState(hitPos);
            Block hitBlock = hitState.getBlock();

            boolean isGlass = hitState.isOf(net.minecraft.block.Blocks.GLASS) ||
                    hitBlock instanceof net.minecraft.block.StainedGlassBlock ||
                    hitBlock instanceof net.minecraft.block.TintedGlassBlock ||
                    hitBlock instanceof net.minecraft.block.PaneBlock;

            if (isGlass) {
                this.getWorld().breakBlock(hitPos, false);
            }

            if (this.getWorld() instanceof net.minecraft.server.world.ServerWorld serverWorld) {
                serverWorld.spawnParticles(
                        new net.minecraft.particle.BlockStateParticleEffect(net.minecraft.particle.ParticleTypes.BLOCK, net.minecraft.block.Blocks.STONE.getDefaultState()),
                        this.getX(), this.getY(), this.getZ(),
                        8,
                        0.1, 0.1, 0.1,
                        0.05
                );
            }
            this.getWorld().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.BLOCK_STONE_HIT, net.minecraft.sound.SoundCategory.NEUTRAL, 1.0F, 1.2F);
            this.discard();
        }
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);

        if (!this.getWorld().isClient) {
            entityHitResult.getEntity().damage(this.getDamageSources().thrown(this, this.getOwner()), 1.5F);

            if (this.getWorld() instanceof net.minecraft.server.world.ServerWorld serverWorld) {
                serverWorld.spawnParticles(
                        ParticleTypes.CRIT,
                        this.getX(), this.getY(), this.getZ(),
                        3,
                        0.1, 0.1, 0.1,
                        0.05
                );
            }
            //this.getWorld().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.BLOCK_STONE_HIT, net.minecraft.sound.SoundCategory.NEUTRAL, 1.0F, 1.2F);
            this.getWorld().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_PLAYER_ATTACK_CRIT, net.minecraft.sound.SoundCategory.NEUTRAL, 0.6F, 1.0F);
            this.discard();
        }
    }
}