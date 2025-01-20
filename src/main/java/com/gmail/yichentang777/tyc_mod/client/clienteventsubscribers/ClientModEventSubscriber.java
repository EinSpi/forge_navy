package com.gmail.yichentang777.tyc_mod.client.clienteventsubscribers;

import com.gmail.yichentang777.tyc_mod.entities.CustomEntities;
import com.gmail.yichentang777.tyc_mod.TycMod;
import com.gmail.yichentang777.tyc_mod.client.keybindings.KeyBindings;
import com.gmail.yichentang777.tyc_mod.client.models.AircraftModel;
import com.gmail.yichentang777.tyc_mod.client.renderers.AircraftEntityRenderer;
import com.gmail.yichentang777.tyc_mod.client.renderers.PilotRendererLayer;
import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.resources.PlayerSkin;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

@Mod.EventBusSubscriber(modid = TycMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEventSubscriber {
    private static final Logger LOGGER = LogUtils.getLogger();

    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(CustomEntities.AIRCRAFT_ENTITY_TYPE.get(), AircraftEntityRenderer::new);

    }

    @SubscribeEvent
    public static void onRegisterLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(AircraftModel.LAYER_LOCATION, AircraftModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void onRegisterKeyBindings(RegisterKeyMappingsEvent event) {
        event.register(KeyBindings.ROLL_LEFT);
        event.register(KeyBindings.ROLL_RIGHT);
        event.register(KeyBindings.LIFT);
        event.register(KeyBindings.DIVE);
    }

    @SubscribeEvent
    public static void onAddingLayers(EntityRenderersEvent.AddLayers event) {

        //EntityRenderDispatcher dispatcher = Minecraft.getInstance().getEntityRenderDispatcher();
        //PlayerRenderer wideRenderer = (PlayerRenderer) dispatcher.getSkinMap().get(PlayerSkin.Model.byName(''));
        //PlayerRenderer slimRenderer = (PlayerRenderer) dispatcher.getSkinMap().get(PlayerSkin.Model.SLIM);
        PlayerRenderer wideRenderer = (PlayerRenderer) event.getPlayerSkin(PlayerSkin.Model.WIDE);
        PlayerRenderer slimRenderer = (PlayerRenderer) event.getPlayerSkin(PlayerSkin.Model.SLIM);
        if (wideRenderer != null) {
            LOGGER.info("registering wide renderer");
            wideRenderer.addLayer(new PilotRendererLayer(wideRenderer));
            LOGGER.info("added wide renderer layer");
        }
        if (slimRenderer != null) {
            slimRenderer.addLayer(new PilotRendererLayer(slimRenderer));
        }
    }


}