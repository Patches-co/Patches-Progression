package net.patches.progression.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.patches.progression.PatchesProgression;
import net.patches.progression.entity.custom.KnappedRockEntity;
import net.patches.progression.entity.custom.RockEntity;

public class ModEntities {

    public static final EntityType<RockEntity> ROCK_PROJECTILE = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(PatchesProgression.MOD_ID, "rock_projectile"),
            EntityType.Builder.<RockEntity>create(RockEntity::new, SpawnGroup.MISC)
                    .dimensions(0.25F, 0.25F)
                    .maxTrackingRange(4)
                    .trackingTickInterval(10)
                    .build()
    );

    public static final EntityType<KnappedRockEntity> KNAPPED_ROCK_PROJECTILE = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(PatchesProgression.MOD_ID, "knapped_rock_projectile"),
            EntityType.Builder.<KnappedRockEntity>create(KnappedRockEntity::new, SpawnGroup.MISC)
                    .dimensions(0.25F, 0.25F)
                    .maxTrackingRange(4)
                    .trackingTickInterval(10)
                    .build()
    );

    public static void registerModEntities() {
        PatchesProgression.LOGGER.info("Registering entities for " + PatchesProgression.MOD_ID);
    }
}