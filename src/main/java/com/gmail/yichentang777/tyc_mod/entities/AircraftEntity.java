package com.gmail.yichentang777.tyc_mod.entities;


import com.gmail.yichentang777.tyc_mod.CustomModPackets.ServerboundAircraftPacket;
import com.gmail.yichentang777.tyc_mod.channels.TycModPacketHandler;
import com.gmail.yichentang777.tyc_mod.serializers.CustomEntityDataSerializers;
import com.gmail.yichentang777.tyc_mod.utils.AircraftLocalRef;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.joml.Quaterniond;
import org.joml.Vector3f;
import org.slf4j.Logger;

import net.minecraft.network.syncher.EntityDataSerializers;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;


public class AircraftEntity extends Entity {

    private static final Logger LOGGER = LogUtils.getLogger();
    protected int lerpSteps;
    protected double lerpX;
    protected double lerpY;
    protected double lerpZ;
    protected double lerpYRot;
    protected double lerpXRot;
    protected double sinSensitivity=(double) Mth.sin((float) (Math.PI / 180.0));
    protected double cosSensitivity=(double) Mth.cos((float) (Math.PI / 180.0));
    

    public double controlledSpeed = 0.0d;
    public AircraftLocalRef ref=this.entityData.get(LOCAL_REF);

    public static final EntityDataAccessor<AircraftLocalRef> LOCAL_REF =SynchedEntityData.defineId(AircraftEntity.class, CustomEntityDataSerializers.LOCAL_REF);




    public AircraftEntity(EntityType<? extends AircraftEntity> entityType, Level level) {
        super(entityType, level);
    }


    @Override
    protected void defineSynchedData(SynchedEntityData.Builder p_333664_) {
        p_333664_.define(LOCAL_REF,new AircraftLocalRef(
                new Vec3(1.0d,0.0d,0.0d),
                new Vec3(0.0d,1.0d,0.0d),
                new Vec3(0.0d,0.0d,1.0d)
        ));
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag p_20052_) {

    }

    @Override
    protected void addAdditionalSaveData(CompoundTag p_20139_) {

    }

    @Override
    protected double getDefaultGravity() {
        return 0.04;
    }


    @Override
    public void tick() {

        super.tick();
        this.tickLerp();
        this.applyGravity();
        this.applyFriction();
        this.applySpeedThreshold();


        if (this.isControlledByLocalInstance()) {
            if (this.level().isClientSide) {
                this.controlAircraft();
                TycModPacketHandler.CHANNEL.send(new ServerboundAircraftPacket(ref), Objects.requireNonNull(Minecraft.getInstance().getConnection()).getConnection());
            }

            this.move(MoverType.SELF, this.getDeltaMovement());
        } else {
            if (this.level().isClientSide){
                //when a client not controlling the aircraft, listen to the server and update client copy "ref"
                ref = this.entityData.get(LOCAL_REF);
            }

            this.setDeltaMovement(Vec3.ZERO);
        }

        //LOGGER.info("pitch {}", this.getXRot());


        this.checkInsideBlocks();
        //handles push despite being defined as can be collided with.
        // The push box is customized by a inflation, making push happen before collision.
        this.handlePush();
    }

    public void applyFriction() {
        if (this.onGround()) {
            this.setDeltaMovement(this.getDeltaMovement().scale(0.96));
        }
    }


    @Override
    public boolean isPushable() {
        return true;
    }

    @Override
    public boolean canBeCollidedWith() {
        //key attribute that enable the player to step on that without pushing it away.
        return true;
    }

    public static boolean canVehicleCollide(Entity p_38324_, Entity p_38325_) {
        return (p_38325_.canBeCollidedWith() || p_38325_.isPushable()) && !p_38324_.isPassengerOfSameVehicle(p_38325_);
    }

    @Override
    public boolean canCollideWith(Entity p_38376_) {
        return canVehicleCollide(this, p_38376_);
    }

    @Override
    public void push(Entity p_20293_) {
        if (p_20293_.getBoundingBox().minY <= this.getBoundingBox().minY) {
            super.push(p_20293_);
        }
    }

    private void tickLerp() {
        if (this.isControlledByLocalInstance()) {
            this.lerpSteps = 0;
            this.syncPacketPositionCodec(this.getX(), this.getY(), this.getZ());
        }

        if (this.lerpSteps > 0) {
            this.lerpPositionAndRotationStep(this.lerpSteps, this.lerpX, this.lerpY, this.lerpZ, this.lerpYRot, this.lerpXRot);
            this.lerpSteps--;
        }
    }

    public void applySpeedThreshold() {
        Vec3 vec3 = this.getDeltaMovement();
        double d0 = vec3.x;
        double d1 = vec3.y;
        double d2 = vec3.z;
        if (Math.abs(vec3.x) < 0.003) {
            d0 = 0.0;
        }

        if (Math.abs(vec3.y) < 0.003) {
            d1 = 0.0;
        }

        if (Math.abs(vec3.z) < 0.003) {
            d2 = 0.0;
        }

        this.setDeltaMovement(d0, d1, d2);
    }


    @Override
    public void lerpTo(double p_38299_, double p_38300_, double p_38301_, float p_38302_, float p_38303_, int p_38304_) {
        this.lerpX = p_38299_;
        this.lerpY = p_38300_;
        this.lerpZ = p_38301_;
        this.lerpYRot = (double) p_38302_;
        this.lerpXRot = (double) p_38303_;
        this.lerpSteps = p_38304_;
    }

    @Override
    public double lerpTargetX() {
        return this.lerpSteps > 0 ? this.lerpX : this.getX();
    }

    @Override
    public double lerpTargetY() {
        return this.lerpSteps > 0 ? this.lerpY : this.getY();
    }

    @Override
    public double lerpTargetZ() {
        return this.lerpSteps > 0 ? this.lerpZ : this.getZ();
    }

    @Override
    public float lerpTargetXRot() {
        return this.lerpSteps > 0 ? (float) this.lerpXRot : this.getXRot();
    }

    @Override
    public float lerpTargetYRot() {
        return this.lerpSteps > 0 ? (float) this.lerpYRot : this.getYRot();
    }

    @Override
    public @NotNull Direction getMotionDirection() {
        return this.getDirection().getClockWise();
    }

    @Override
    public float maxUpStep() {
        return 0.5F;
    }

    @Override
    public boolean isPickable() {
        return !this.isRemoved();
    }

    @Override
    public @NotNull InteractionResult interact(@NotNull Player player, @NotNull InteractionHand hand) {
        this.controlledSpeed = 0.0d;
        if (!this.level().isClientSide) {
            return player.startRiding(this) ? InteractionResult.CONSUME : InteractionResult.PASS;
        } else {
            return InteractionResult.SUCCESS;
        }

    }

    @Nullable
    @Override
    public LivingEntity getControllingPassenger() {
        return this.getFirstPassenger() instanceof LivingEntity livingentity ? livingentity : super.getControllingPassenger();
    }

    public void controlAircraft() {
        if (this.getControllingPassenger() instanceof LocalPlayer lp) {

            if (lp.input.left) {
                this.left_right(true);
            } else if (lp.input.right) {
                this.left_right(false);
            }

            if (lp.input.up) {
                controlledSpeed = controlledSpeed <= 2.0d ? controlledSpeed + 0.1d : controlledSpeed;
            }
            if (lp.input.down) {
                controlledSpeed = controlledSpeed >= -2.0d ? controlledSpeed - 0.1d : controlledSpeed;
            }

            this.setDeltaMovement(this.entityData.get(LOCAL_REF).getMainZ().scale(controlledSpeed));
            //LOGGER.info("Y Pos Aircraft {}, Y Pos Player {}",this.getY(),this.getControllingPassenger().getY());

        }


    }

    public void handlePush() {
        List<Entity> list = this.level().getEntities(this, this.getBoundingBox().inflate(0.2f, -0.01f, 0.2f), EntitySelector.pushableBy(this));
        if (!list.isEmpty()) {
            boolean flag = !this.level().isClientSide && !(this.getControllingPassenger() instanceof Player);

            for (Entity entity : list) {
                if (!entity.hasPassenger(this)) {

                    this.push(entity);
                }
            }
        }
    }

    @Override
    protected @NotNull Vec3 getPassengerAttachmentPoint(@NotNull Entity passenger, @NotNull EntityDimensions dimensions, float scaleFactor) {
        //Constant seat offset.
        return new Vec3(0.0, 1.5, 0.0);
    }







    public void roll(boolean left) {

        ref.rotateAround(left,ref.getMainZ().x,ref.getMainZ().y,ref.getMainZ().z,
                sinSensitivity,cosSensitivity);
    }
    

    public void lift_dive(boolean dive) {
        ref.rotateAround(dive,ref.getMainX().x,ref.getMainX().y,ref.getMainX().z,
                sinSensitivity,cosSensitivity);
    }


    public void left_right(boolean left) {
        ref.rotateAround(left,0.0d,1.0d,0.0d,
                sinSensitivity,cosSensitivity);
    }


    public void setLocalRef(AircraftLocalRef Ref) {
        this.entityData.set(LOCAL_REF,Ref);

    }

    @Override
    public void kill() {

        super.kill();
    }
}
