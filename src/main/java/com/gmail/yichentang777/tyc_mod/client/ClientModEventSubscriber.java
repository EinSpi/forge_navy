package com.gmail.yichentang777.tyc_mod.client;

import com.gmail.yichentang777.tyc_mod.ModEntities;
import com.gmail.yichentang777.tyc_mod.TycMod;
import com.gmail.yichentang777.tyc_mod.client.model.AircraftModel;
import com.gmail.yichentang777.tyc_mod.client.renderer.AircraftEntityRenderer;
import com.gmail.yichentang777.tyc_mod.client.keybindings.KeyBindings;

import com.gmail.yichentang777.tyc_mod.client.renderer.PilotRendererLayer;
import com.gmail.yichentang777.tyc_mod.entities.AircraftEntity;
import com.mojang.logging.LogUtils;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.resources.PlayerSkin;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.checkerframework.checker.units.qual.K;
import org.slf4j.Logger;

import java.util.Map;

@Mod.EventBusSubscriber(modid = TycMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEventSubscriber {
    private static final Logger LOGGER = LogUtils.getLogger();
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

    @SubscribeEvent
    public static void onAddingLayers(EntityRenderersEvent.AddLayers event) {

        //EntityRenderDispatcher dispatcher = Minecraft.getInstance().getEntityRenderDispatcher();
        //PlayerRenderer wideRenderer = (PlayerRenderer) dispatcher.getSkinMap().get(PlayerSkin.Model.byName(''));
        //PlayerRenderer slimRenderer = (PlayerRenderer) dispatcher.getSkinMap().get(PlayerSkin.Model.SLIM);
        PlayerRenderer wideRenderer = (PlayerRenderer)event.getPlayerSkin(PlayerSkin.Model.WIDE);
        PlayerRenderer slimRenderer = (PlayerRenderer)event.getPlayerSkin(PlayerSkin.Model.SLIM);
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