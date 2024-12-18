package com.gmail.yichentang777.tyc_mod.client;


import com.gmail.yichentang777.tyc_mod.TycMod;
import com.gmail.yichentang777.tyc_mod.client.keybindings.KeyBindings;
import com.gmail.yichentang777.tyc_mod.entities.AircraftEntity;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TycMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientForgeEventSubscriber {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void AircraftRollHandler(TickEvent.ClientTickEvent event){
        Player player = Minecraft.getInstance().player;
        if (player != null && player.getVehicle() instanceof AircraftEntity ac){
            suppressVanillaKeyAction(Minecraft.getInstance().options.keyDrop);
            suppressVanillaKeyAction(Minecraft.getInstance().options.keyInventory);
            if (KeyBindings.ROLL_LEFT.isDown()){

                ac.roll_left();
                suppressVanillaKeyAction(Minecraft.getInstance().options.keyDrop);
            }
            if (KeyBindings.ROLL_RIGHT.isDown()){

                ac.roll_right();
                suppressVanillaKeyAction(Minecraft.getInstance().options.keyInventory);
            }

            if (KeyBindings.LIFT.isDown()){
                ac.lift();
            }

            if (KeyBindings.DIVE.isDown()){
                ac.dive();
            }


        }
    }

    // Helper method to reset the drop key state
    private static void suppressVanillaKeyAction(KeyMapping keyMapping) {
        // Reset the key's state and manually mark it as not pressed
        while (keyMapping.consumeClick()) {
            // Clear all pending "click" actions
        }
        keyMapping.setDown(false);
    }


}
