package com.gmail.yichentang777.tyc_mod.CustomModPackets;

import com.gmail.yichentang777.tyc_mod.utils.AircraftLocalRef;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import org.jetbrains.annotations.NotNull;

public class ServerboundAircraftPacketCodec implements StreamCodec<RegistryFriendlyByteBuf,ServerboundAircraftPacket> {
    @Override
    public @NotNull ServerboundAircraftPacket decode(RegistryFriendlyByteBuf buf) {
        return new ServerboundAircraftPacket(new AircraftLocalRef(buf.readVec3(), buf.readVec3(), buf.readVec3(),
                buf.readDouble(), buf.readDouble(), buf.readDouble()));
    }

    @Override
    public void encode(RegistryFriendlyByteBuf buf, ServerboundAircraftPacket packet) {
        AircraftLocalRef ref = packet.getLocalRef();
        buf.writeVec3(ref.getMainX());
        buf.writeVec3(ref.getMainY());
        buf.writeVec3(ref.getMainZ());
        buf.writeDouble(ref.getRenderAngle1()).writeDouble(ref.getRenderAngle2()).writeDouble(ref.getRenderAngle3());
    }
}
