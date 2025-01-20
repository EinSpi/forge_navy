package com.gmail.yichentang777.tyc_mod.network.packets;

import com.gmail.yichentang777.tyc_mod.entities.aircrafts.AircraftEntity;
import com.gmail.yichentang777.tyc_mod.utils.AircraftLocalRef;
import net.minecraftforge.event.network.CustomPayloadEvent;

import java.util.Objects;

public class ServerboundAircraftPacket {

    private final AircraftLocalRef localRef;

    public ServerboundAircraftPacket(AircraftLocalRef localRef) {
        this.localRef = localRef;
    }

    public AircraftLocalRef getLocalRef() {
        return localRef;
    }


    public void handle(CustomPayloadEvent.Context context) {
        if (Objects.requireNonNull(context.getSender()).getVehicle() instanceof AircraftEntity aircraftEntity) {
            aircraftEntity.setLocalRef(this.localRef);
        }
    }


}
