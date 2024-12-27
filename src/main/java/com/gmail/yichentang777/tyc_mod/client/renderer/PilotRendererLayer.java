package com.gmail.yichentang777.tyc_mod.client.renderer;

import com.gmail.yichentang777.tyc_mod.entities.AircraftEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import org.jetbrains.annotations.NotNull;

public class PilotRendererLayer  extends RenderLayer<AbstractClientPlayer,PlayerModel<AbstractClientPlayer>>{

    public PilotRendererLayer(RenderLayerParent<AbstractClientPlayer,PlayerModel<AbstractClientPlayer>> parent) {
        super(parent);
    }
    @Override
    public void render(@NotNull PoseStack poseStack, @NotNull MultiBufferSource buffer, int packedLight,
                       @NotNull AbstractClientPlayer player, float limbSwing, float limbSwingAmount,
                       float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (player.getVehicle() instanceof AircraftEntity aircraft) {
            //retrieve Euler Angles dynamically from aircraft being ridden. 
            double[] euler_angles = aircraft.recoverRotationsFromCoordinate();
            //Reset player body rotation to prevent steering the body by mouse cursor.
            player.setYBodyRot(0.0f);
            //Apply Euler Angles to compute pilot orientation. Ensure compatible with aircraft.
            poseStack.pushPose();
            poseStack.mulPose(Axis.YP.rotation(-(float) euler_angles[1]));
            poseStack.mulPose(Axis.XP.rotation((float) euler_angles[0]));
            poseStack.mulPose(Axis.YP.rotation(-(float) euler_angles[2]));
            this.getParentModel().renderToBuffer(poseStack,
                    buffer.getBuffer(RenderType.entityTranslucent(player.getSkin().texture())),
                    packedLight, OverlayTexture.NO_OVERLAY,
                    1.0F, 1.0F, 1.0F, 1.0F);
            poseStack.popPose();

        }
    }


}
