package com.gmail.yichentang777.tyc_mod;

import com.gmail.yichentang777.tyc_mod.entities.AircraftEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {

    public static final RegistryObject<EntityType<AircraftEntity>> AIRCRAFT_ENTITY_TYPE = TycMod.ENTITY_TYPES.register(
            "aircraft_entity_type",
            () -> EntityType.Builder
                    .<AircraftEntity>of(AircraftEntity::new, MobCategory.MISC)
                    .sized(1.0f, 2.0f)
                    .build("aircraft_entity_type")
    );


    //called to let JVM load this class so that all above static fields are assigned.
    public static int init() {return 0;}

    /*
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES= DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, TycMod.MODID);

    public static final RegistryObject<EntityType<?>> AIRCRAFT_ENTITY_TYPE = ENTITY_TYPES.register("aircraft_entity_type", ()->EntityType.Builder
            .<AircraftEntity>of(AircraftEntity::new, MobCategory.MISC).
            sized(0.5f, 0.5f).build("aircraft_entity_type"));

    @SubscribeEvent
    public static void registerEntities(RegisterEvent event) {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ENTITY_TYPES.register(modEventBus);
    }


*/

}
