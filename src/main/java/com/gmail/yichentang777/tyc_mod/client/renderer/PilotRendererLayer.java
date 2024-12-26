package com.gmail.yichentang777.tyc_mod.client.renderer;

import com.gmail.yichentang777.tyc_mod.entities.AircraftEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.logging.LogUtils;
import com.mojang.math.Axis;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.ElytraLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

public class PilotRendererLayer  extends RenderLayer<AbstractClientPlayer,PlayerModel<AbstractClientPlayer>>{

    private static final Logger LOGGER = LogUtils.getLogger();
    public PilotRendererLayer(RenderLayerParent<AbstractClientPlayer,PlayerModel<AbstractClientPlayer>> parent) {
        super(parent);
    }
    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight,
                       @NotNull AbstractClientPlayer player, float limbSwing, float limbSwingAmount,
                       float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (player.getVehicle() instanceof AircraftEntity aircraft) {
            double[] eulerangles = aircraft.recoverRotationsFromCoordinate();
            poseStack.pushPose();
            player.setYBodyRot(0.0f);


            poseStack.translate(0.0D, 0.0D, 0.0D);
            poseStack.mulPose(Axis.YP.rotation(-(float) eulerangles[1])); // Yaw
            poseStack.mulPose(Axis.XP.rotation((float) eulerangles[0])); // Pitch
            poseStack.mulPose(Axis.YP.rotation(-(float) eulerangles[2])); // Roll
            this.getParentModel().renderToBuffer(poseStack,
                    buffer.getBuffer(RenderType.entityTranslucent(player.getSkin().texture())),
                    packedLight, OverlayTexture.NO_OVERLAY,
                    1.0F, 1.0F, 1.0F, 1.0F);
            poseStack.popPose();

        }
    }


}
