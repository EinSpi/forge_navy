package com.gmail.yichentang777.tyc_mod.client.clienteventsubscribers;


import com.gmail.yichentang777.tyc_mod.TycMod;
import com.gmail.yichentang777.tyc_mod.client.keybindings.KeyBindings;
import com.gmail.yichentang777.tyc_mod.entities.aircrafts.AircraftEntity;
import com.mojang.logging.LogUtils;
import net.minecraft.client.CameraType;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;


@Mod.EventBusSubscriber(modid = TycMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientForgeEventSubscriber {
    private static final Logger LOGGER = LogUtils.getLogger();
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void AircraftRollHandler(TickEvent.ClientTickEvent event) {
        //Centralized handling aircraft control logics involving customized keymappings.
        //Implemented by listening to general client tick event.
        //Alternative: listen to KeyInput event, but causes non-smooth issue due to distinction of long vs. short keypress introduced by this event.
        Player player = Minecraft.getInstance().player;
        if (player != null && player.getVehicle() instanceof AircraftEntity ac) {
            //cancelling vanilla game behavior triggered by specific key input.
            suppressVanillaKeyAction(Minecraft.getInstance().options.keyDrop);
            suppressVanillaKeyAction(Minecraft.getInstance().options.keyInventory);
            if (KeyBindings.ROLL_LEFT.isDown()) {

                ac.roll(false);
                suppressVanillaKeyAction(Minecraft.getInstance().options.keyDrop);
            }
            if (KeyBindings.ROLL_RIGHT.isDown()) {

                ac.roll(true);
                suppressVanillaKeyAction(Minecraft.getInstance().options.keyInventory);
            }

            if (KeyBindings.LIFT.isDown()) {
                ac.lift_dive(false);
            }

            if (KeyBindings.DIVE.isDown()) {
                ac.lift_dive(true);
            }


        }
    }

    @SubscribeEvent
    public static void onRenderPlayer(RenderLivingEvent.Pre<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> event) {
        // Check if the player is riding an aircraft, if so, activate the following functionalities.
        if (event.getEntity() instanceof AbstractClientPlayer abstractClientPlayer) {
            if (event.getEntity().getVehicle() instanceof AircraftEntity) {
                //Prevent player body yaw from mouse cursor movement, ensure compatibility with aircraft seat.
                abstractClientPlayer.setYBodyRot(0.0f);
                //Set the player invisible to ignore vanilla render, fully rely on custom defined render layer, avoid visual duplication.
                abstractClientPlayer.setInvisible(true);

            }
            else{
                abstractClientPlayer.setInvisible(false);
            }
        }
    }

    // Helper method to reset the drop key state
    private static void suppressVanillaKeyAction(@NotNull KeyMapping keyMapping) {
        // Reset the key's state and manually mark it as not pressed.
        // Prevent vanilla game behavior of certain key while driving an aircraft.
        while (keyMapping.consumeClick()) {
            // Clear all pending "click" actions
        }
        keyMapping.setDown(false);
    }
    @SubscribeEvent
    public static void onCameraSetup(ViewportEvent.ComputeCameraAngles event) {
        Minecraft mc = Minecraft.getInstance();
        Entity cameraEntity = mc.getCameraEntity();

        if (mc.options.getCameraType().isFirstPerson() && cameraEntity instanceof LocalPlayer player) {
            Entity vehicle = player.getVehicle();

            if (vehicle instanceof AircraftEntity aircraft) {


                // Set yaw (left-right rotation)
                event.setYaw((float)(-aircraft.ref.getRenderAngle2()*(180.0D/Math.PI)));
                // Set pitch (up-down rotation)
                event.setPitch((float)(aircraft.ref.getRenderAngle1()*(180.0D/Math.PI)));


            }
        }
    }



}
