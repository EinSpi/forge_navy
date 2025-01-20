package com.gmail.yichentang777.tyc_mod.eventsubscriber;


import com.gmail.yichentang777.tyc_mod.TycMod;
import com.gmail.yichentang777.tyc_mod.network.channels.Channels;
import com.gmail.yichentang777.tyc_mod.network.serializers.CustomEntityDataSerializers;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = TycMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventSubscriber {

@SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
    CustomEntityDataSerializers.register();
    Channels.registerPackets();
}
}
