package net.patches.progression.client.render.entity;

import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.patches.progression.client.render.entity.model.RockProjectileModel;
import net.patches.progression.entity.custom.RockEntity;

public class RockProjectileRenderer extends EntityRenderer<RockEntity> {
    public static final Identifier TEXTURE = Identifier.ofVanilla("textures/block/stone.png");
    private final RockProjectileModel model;

    public RockProjectileRenderer(EntityRendererFactory.Context context, EntityModelLayer layer) {
        super(context);
        this.model = new RockProjectileModel(context.getPart(layer));
    }

    @Override
    public void render(RockEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();
        matrices.translate(0.0f, 0.15f, 0.0f);

        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(this.model.getLayer(TEXTURE));

        float age = (float)entity.age + tickDelta;
        this.model.setAngles(entity, 0.0f, 0.0f, age, yaw, 0.0f);

        this.model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV);
        matrices.pop();

        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    @Override
    public Identifier getTexture(RockEntity entity) {
        return TEXTURE;
    }
}