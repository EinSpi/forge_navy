package com.gmail.yichentang777.tyc_mod.client;


import com.gmail.yichentang777.tyc_mod.TycMod;
import com.gmail.yichentang777.tyc_mod.client.keybinding.KeyBindings;
import com.gmail.yichentang777.tyc_mod.entities.AircraftEntity;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;


@Mod.EventBusSubscriber(modid = TycMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientForgeEventSubscriber {

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

                ac.roll_left();
                suppressVanillaKeyAction(Minecraft.getInstance().options.keyDrop);
            }
            if (KeyBindings.ROLL_RIGHT.isDown()) {

                ac.roll_right();
                suppressVanillaKeyAction(Minecraft.getInstance().options.keyInventory);
            }

            if (KeyBindings.LIFT.isDown()) {
                ac.lift();
            }

            if (KeyBindings.DIVE.isDown()) {
                ac.dive();
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


}
