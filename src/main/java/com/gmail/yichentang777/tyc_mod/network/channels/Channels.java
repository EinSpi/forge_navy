package com.gmail.yichentang777.tyc_mod.network.channels;

import com.gmail.yichentang777.tyc_mod.network.packets.ServerboundAircraftPacket;
import com.gmail.yichentang777.tyc_mod.TycMod;
import com.gmail.yichentang777.tyc_mod.network.codecs.CustomCodecs;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.Channel;
import net.minecraftforge.network.ChannelBuilder;
import net.minecraftforge.network.NetworkProtocol;
import net.minecraftforge.network.SimpleChannel;

public class Channels {

    private static final int PROTOCOL_VERSION = 1;

    public static final SimpleChannel CHANNEL = ChannelBuilder.named(new ResourceLocation(TycMod.MODID,"main"))
            .networkProtocolVersion(PROTOCOL_VERSION).
            acceptedVersions(Channel.VersionTest.exact(PROTOCOL_VERSION)).simpleChannel();

    public static void registerPackets() {
        CHANNEL.messageBuilder(ServerboundAircraftPacket.class,0,NetworkProtocol.PLAY).
                codec(CustomCodecs.LOCAL_REF_PACKET).
                direction(PacketFlow.SERVERBOUND).
                consumerMainThread(ServerboundAircraftPacket::handle).add();
    }



}
