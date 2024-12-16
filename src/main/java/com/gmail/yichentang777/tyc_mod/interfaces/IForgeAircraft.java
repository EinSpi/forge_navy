package com.gmail.yichentang777.tyc_mod.interfaces;

import com.gmail.yichentang777.tyc_mod.entities.AircraftEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.fluids.FluidType;

public interface IForgeAircraft
{
    private AircraftEntity self()
    {
        return (AircraftEntity) this;
    }



}