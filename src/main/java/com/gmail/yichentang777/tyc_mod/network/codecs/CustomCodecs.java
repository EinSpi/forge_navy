package com.gmail.yichentang777.tyc_mod.network.codecs;

import com.gmail.yichentang777.tyc_mod.network.codecs.packetcodecs.ServerboundAircraftPacketCodec;
import com.gmail.yichentang777.tyc_mod.network.codecs.commoncodecs.AircraftLocalRefStreamCodec;

public class CustomCodecs {
    public static final AircraftLocalRefStreamCodec LOCAL_REF = new AircraftLocalRefStreamCodec();
    public static final ServerboundAircraftPacketCodec LOCAL_REF_PACKET = new ServerboundAircraftPacketCodec();
}
