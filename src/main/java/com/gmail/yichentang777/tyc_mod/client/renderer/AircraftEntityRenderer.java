package com.gmail.yichentang777.tyc_mod.client.renderer;

import com.mojang.math.Axis;
import com.gmail.yichentang777.tyc_mod.TycMod;
import com.gmail.yichentang777.tyc_mod.client.model.AircraftModel;
import com.gmail.yichentang777.tyc_mod.entities.AircraftEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import org.joml.Quaternionf;

public class AircraftEntityRenderer extends EntityRenderer<AircraftEntity> {

    private final AircraftModel model;

    private static final ResourceLocation TEXTURE = new ResourceLocation(TycMod.MODID, "textures/entity/aircraft3.png");

    public AircraftEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new AircraftModel(context.bakeLayer(AircraftModel.LAYER_LOCATION));
    }

    @Override
    public ResourceLocation getTextureLocation(AircraftEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(AircraftEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();
        poseStack.translate(0.0D, 2.3D, 0.0D);
        // Adjust Y offset to align the model with the ground
        poseStack.mulPose(Axis.ZP.rotationDegrees(180.0f));
        double[] eulerangles=entity.recoverRotationsFromCoordinate();

        poseStack.mulPose(Axis.YP.rotation(-(float)eulerangles[1]));
        poseStack.mulPose(Axis.XP.rotation(-(float)eulerangles[0]));
        poseStack.mulPose(Axis.YP.rotation(-(float)eulerangles[2]));

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
