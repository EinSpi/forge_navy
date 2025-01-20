package com.gmail.yichentang777.tyc_mod.entities;

import com.gmail.yichentang777.tyc_mod.TycMod;
import com.gmail.yichentang777.tyc_mod.entities.aircrafts.AircraftEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.RegistryObject;

public class CustomEntities {

    public static final RegistryObject<EntityType<AircraftEntity>> AIRCRAFT_ENTITY_TYPE = TycMod.ENTITY_TYPES.register(
            "aircraft_entity_type",
            () -> EntityType.Builder
                    .<AircraftEntity>of(AircraftEntity::new, MobCategory.MISC)
                    .sized(1.0f, 2.0f)
                    .build("aircraft_entity_type")
    );


    //called to let JVM load this class so that all above static fields are assigned.
    public static int init() {
        return 0;
    }


}
