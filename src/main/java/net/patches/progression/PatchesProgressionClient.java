package net.patches.progression;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import net.patches.progression.block.ModBlocks;
import net.patches.progression.client.render.entity.KnappedRockProjectileRenderer;
import net.patches.progression.client.render.entity.RockProjectileRenderer;
import net.patches.progression.client.render.entity.model.RockProjectileModel;
import net.patches.progression.entity.ModEntities;

public class PatchesProgressionClient implements ClientModInitializer {
    public static final EntityModelLayer ROCK_PROJECTILE_LAYER = new EntityModelLayer(Identifier.of("patches-progression", "rock_projectile"), "main");

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FLINT_PEBBLE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ROCK_PEBBLE, RenderLayer.getCutout());

        EntityModelLayerRegistry.registerModelLayer(ROCK_PROJECTILE_LAYER, RockProjectileModel::getTexturedModelData);
        if (FabricLoader.getInstance().isModLoaded("amendments")) {
            EntityRendererRegistry.register(ModEntities.ROCK_PROJECTILE, context -> new RockProjectileRenderer(context, ROCK_PROJECTILE_LAYER));
            EntityRendererRegistry.register(ModEntities.KNAPPED_ROCK_PROJECTILE, context -> new KnappedRockProjectileRenderer(context, ROCK_PROJECTILE_LAYER));
        } else {
            EntityRendererRegistry.register(ModEntities.ROCK_PROJECTILE, FlyingItemEntityRenderer::new);
            EntityRendererRegistry.register(ModEntities.KNAPPED_ROCK_PROJECTILE, FlyingItemEntityRenderer::new);
        }
    }
}
