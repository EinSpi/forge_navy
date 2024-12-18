package com.gmail.yichentang777.tyc_mod.client;

import com.gmail.yichentang777.tyc_mod.ModEntities;
import com.gmail.yichentang777.tyc_mod.TycMod;
import com.gmail.yichentang777.tyc_mod.client.model.AircraftModel;
import com.gmail.yichentang777.tyc_mod.client.renderer.AircraftEntityRenderer;
import com.gmail.yichentang777.tyc_mod.client.keybindings.KeyBindings;
import com.gmail.yichentang777.tyc_mod.entities.AircraftEntity;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.checkerframework.checker.units.qual.K;

@Mod.EventBusSubscriber(modid = TycMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEventSubscriber {
    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.AIRCRAFT_ENTITY_TYPE.get(), AircraftEntityRenderer::new);
    }

    @SubscribeEvent
    public static void onRegisterLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(AircraftModel.LAYER_LOCATION,AircraftModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void onRegisterKeyBindings(RegisterKeyMappingsEvent event) {
        event.register(KeyBindings.ROLL_LEFT);
        event.register(KeyBindings.ROLL_RIGHT);
        event.register(KeyBindings.LIFT);
        event.register(KeyBindings.DIVE);
    }



}