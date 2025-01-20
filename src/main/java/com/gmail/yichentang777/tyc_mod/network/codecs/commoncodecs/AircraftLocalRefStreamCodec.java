package com.gmail.yichentang777.tyc_mod.network.codecs.commoncodecs;

import com.gmail.yichentang777.tyc_mod.utils.AircraftLocalRef;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import org.jetbrains.annotations.NotNull;

public class AircraftLocalRefStreamCodec implements StreamCodec<RegistryFriendlyByteBuf, AircraftLocalRef> {
    @Override
    public @NotNull AircraftLocalRef decode(RegistryFriendlyByteBuf buf) {
       return new AircraftLocalRef(
               buf.readVec3(),
               buf.readVec3(),
               buf.readVec3(),
               buf.readDouble(),buf.readDouble(),buf.readDouble()
       );
    }

    @Override
    public void encode(RegistryFriendlyByteBuf buf, AircraftLocalRef ref) {
        buf.writeVec3(ref.getMainX());
        buf.writeVec3(ref.getMainY());
        buf.writeVec3(ref.getMainZ());
        buf.writeDouble(ref.getRenderAngle1()).writeDouble(ref.getRenderAngle2()).writeDouble(ref.getRenderAngle3());
    }
}
