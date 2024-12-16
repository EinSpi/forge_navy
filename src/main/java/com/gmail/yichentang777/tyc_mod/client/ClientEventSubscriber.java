package com.gmail.yichentang777.tyc_mod.client;

import com.gmail.yichentang777.tyc_mod.ModEntities;
import com.gmail.yichentang777.tyc_mod.TycMod;
import com.gmail.yichentang777.tyc_mod.client.model.AircraftModel;
import com.gmail.yichentang777.tyc_mod.client.renderer.AircraftEntityRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TycMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventSubscriber {
    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.AIRCRAFT_ENTITY_TYPE.get(), AircraftEntityRenderer::new);
    }

    @SubscribeEvent
    public static void onRegisterLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(AircraftModel.LAYER_LOCATION,AircraftModel::createBodyLayer);
    }
}