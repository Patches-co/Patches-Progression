package net.patches.progression;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.patches.progression.block.ModBlocks;
import net.patches.progression.entity.ModEntities;

public class PatchesProgressionClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FLINT_PEBBLE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ROCK_PEBBLE, RenderLayer.getCutout());

        EntityRendererRegistry.register(ModEntities.ROCK_PROJECTILE, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.KNAPPED_ROCK_PROJECTILE, FlyingItemEntityRenderer::new);
    }
}
