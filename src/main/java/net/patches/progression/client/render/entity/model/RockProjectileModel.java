package net.patches.progression.client.render.entity.model;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.entity.Entity;

public class RockProjectileModel extends SinglePartEntityModel<Entity> {
    private final ModelPart root;

    public RockProjectileModel(ModelPart root) {
        this.root = root;
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();

        modelPartData.addChild("root", ModelPartBuilder.create()
                        .uv(0, 0)
                        .cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F),
                ModelTransform.NONE);

        return TexturedModelData.of(modelData, 16, 16);
    }

    @Override
    public ModelPart getPart() {
        return this.root;
    }

    @Override
    public void setAngles(Entity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        this.root.yaw = animationProgress * 0.5F;
        this.root.pitch = animationProgress * 0.5F;
        this.root.roll = animationProgress * 0.2F;
    }
}