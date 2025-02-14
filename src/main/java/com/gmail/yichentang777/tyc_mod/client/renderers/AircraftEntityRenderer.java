package com.gmail.yichentang777.tyc_mod.client.renderers;

import com.mojang.math.Axis;
import com.gmail.yichentang777.tyc_mod.TycMod;
import com.gmail.yichentang777.tyc_mod.client.models.AircraftModel;
import com.gmail.yichentang777.tyc_mod.entities.aircrafts.AircraftEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class AircraftEntityRenderer extends EntityRenderer<AircraftEntity> {

    private final AircraftModel model;

    private static final ResourceLocation TEXTURE = new ResourceLocation(TycMod.MODID, "textures/entity/aircraft3.png");

    public AircraftEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new AircraftModel(context.bakeLayer(AircraftModel.LAYER_LOCATION));
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull AircraftEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(AircraftEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();
        poseStack.translate(0.0D, 2.3D, 0.0D);
        // Adjust Y offset to align the model with the ground
        poseStack.mulPose(Axis.ZP.rotationDegrees(180.0f));

        if (entity.isControlledByLocalInstance()) {
            //if insControlled locally, compute euler angles, otherwise trust ref itself.
            entity.ref.recoverRotationsFromCoordinate();
        }

        poseStack.mulPose(Axis.YP.rotation(-(float) entity.ref.getRenderAngle2()));
        poseStack.mulPose(Axis.XP.rotation(-(float) entity.ref.getRenderAngle1()));
        poseStack.mulPose(Axis.YP.rotation(-(float) entity.ref.getRenderAngle3()));


        // Pitch (vertical rotation)

        // Rotate model to align with entity's pitch
        this.model.renderToBuffer(
                poseStack,
                buffer.getBuffer(RenderType.entityTranslucent(getTextureLocation(entity))),
                packedLight, OverlayTexture.NO_OVERLAY,
                1.0F, 1.0F, 1.0F, 1.0F
        );
        poseStack.popPose();
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }

}
