package com.gmail.yichentang777.tyc_mod.network.serializers;

import com.gmail.yichentang777.tyc_mod.network.codecs.CustomCodecs;
import com.gmail.yichentang777.tyc_mod.utils.AircraftLocalRef;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;

public class CustomEntityDataSerializers {
    public static final EntityDataSerializer<AircraftLocalRef> LOCAL_REF = EntityDataSerializer.forValueType(CustomCodecs.LOCAL_REF);

    public static void register() {
        //registering custom entity data serializers.
        EntityDataSerializers.registerSerializer(LOCAL_REF);
    }
}
