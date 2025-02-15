package com.gmail.yichentang777.tyc_mod.client.renderers;

import com.gmail.yichentang777.tyc_mod.entities.aircrafts.AircraftEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class PilotRendererLayer extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {

    public PilotRendererLayer(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> parent) {
        super(parent);
    }

    @Override
    public void render(@NotNull PoseStack poseStack, @NotNull MultiBufferSource buffer, int packedLight,
                       @NotNull AbstractClientPlayer player, float limbSwing, float limbSwingAmount,
                       float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (player.getVehicle() instanceof AircraftEntity aircraft) {
            //retrieve Euler Angles dynamically from aircraft being ridden.
            //Reset player body rotation to prevent steering the body by mouse cursor.
            player.setYBodyRot(0.0f);
            //Apply Euler Angles to compute pilot orientation. Ensure compatible with aircraft.
            poseStack.pushPose();
            if (aircraft.isControlledByLocalInstance()){
                aircraft.ref.recoverRotationsFromCoordinate();
            }
            Vec3 ae_prime=aircraft.ref.getMainY().multiply(0.3,0.3,0.3);
            Vec3 aa_prime=ae_prime.add(0.0,-ae_prime.length(),0.0D);
            poseStack.translate(-aa_prime.x, aa_prime.y, aa_prime.z);
            poseStack.mulPose(Axis.YP.rotation(-(float) aircraft.ref.getRenderAngle2()));
            poseStack.mulPose(Axis.XP.rotation((float) aircraft.ref.getRenderAngle1()));
            poseStack.mulPose(Axis.ZP.rotation(-(float) aircraft.ref.getRenderAngle3()));
            this.getParentModel().renderToBuffer(poseStack,
                    buffer.getBuffer(RenderType.entityTranslucent(player.getSkin().texture())),
                    packedLight, OverlayTexture.NO_OVERLAY,
                    1.0F, 1.0F, 1.0F, 1.0F);
            poseStack.popPose();

        }
    }


}
