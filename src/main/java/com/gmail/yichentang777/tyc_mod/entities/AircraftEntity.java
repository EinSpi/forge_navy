package com.gmail.yichentang777.tyc_mod.entities;


import com.mojang.logging.LogUtils;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;

import net.minecraft.network.syncher.SynchedEntityData;

import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;

import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;
import org.slf4j.Logger;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Locale;
import java.util.Objects;


public class AircraftEntity extends Entity {
    private static final Logger LOGGER = LogUtils.getLogger();
    protected int lerpSteps;
    protected double lerpX;
    protected double lerpY;
    protected double lerpZ;


    protected double lerpYRot;
    protected double lerpXRot;

    protected float zRot=0.0f;

    public double controlledSpeed=0.0d;
    public float normalizedYRot=0.0f;
    public float normalizedXRot=0.0f;
    public float normalizedZRot=0.0f;

    public AircraftEntity(EntityType<? extends AircraftEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder p_333664_) {

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


        if (this.isControlledByLocalInstance()){
            if (this.level().isClientSide) {
                this.controlAircraft();
            }
            this.move(MoverType.SELF, this.getDeltaMovement());
        } else {
            this.setDeltaMovement(Vec3.ZERO);
        }

        //LOGGER.info("pitch {}", this.getXRot());



       this.checkInsideBlocks();
        //handles push despite being defined as can be collided with.
        // The push box is customized by a inflation, making push happen before collision.
        this.handlePush();

//


    }

    public void applyFriction(){
        if(this.onGround()){
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
        this.lerpYRot = (double)p_38302_;
        this.lerpXRot = (double)p_38303_;
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
        return this.lerpSteps > 0 ? (float)this.lerpXRot : this.getXRot();
    }

    @Override
    public float lerpTargetYRot() {
        return this.lerpSteps > 0 ? (float)this.lerpYRot : this.getYRot();
    }

    @Override
    public Direction getMotionDirection() {
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
    public InteractionResult interact(Player p_38330_, InteractionHand p_38331_) {
            this.controlledSpeed=0.0d;
            if (!this.level().isClientSide) {
                return p_38330_.startRiding(this) ? InteractionResult.CONSUME : InteractionResult.PASS;
            } else {
                return InteractionResult.SUCCESS;
            }

    }

    @Nullable
    @Override
    public LivingEntity getControllingPassenger() {
        return this.getFirstPassenger() instanceof LivingEntity livingentity ? livingentity : super.getControllingPassenger();
    }

    public void controlAircraft(){
        if (this.getControllingPassenger() instanceof LocalPlayer lp)
        {

            if (lp.input.left && !lp.input.right)
            {
                if(this.isXRotNormal()^this.isZRotNormal()) {
                    //abnormal case, Y need to be inversed.
                    //this.addYRot();
                    this.setYRot(this.getYRot()+3.0f);

                }
                else{
                    //this.minusYRot();
                    this.setYRot(this.getYRot()-3.0f);
                }
            }
            else if (lp.input.right && !lp.input.left)
            {
                if (this.isXRotNormal()^this.isZRotNormal()) {
                    //this.minusYRot();
                    this.setYRot(this.getYRot()-3.0f);

                }
                else{
                    //this.addYRot();
                    this.setYRot(this.getYRot()+3.0f);
                }
            }



            if (lp.input.jumping){
                if (lp.input.down){
                    if (this.isZRotNormal()) {
                        //normal case
                        //this.addXRot();
                        this.setXRot(this.getXRot()+3.0f);
                    }
                    else {
                        //this.minusXRot();
                        this.setXRot(this.getXRot()-3.0f);
                    }
                }
                else {
                    if (this.isZRotNormal()) {
                        //this.minusXRot();
                        this.setXRot(this.getXRot()-3.0f);
                    }
                    else {
                        //this.addXRot();
                        this.setXRot(this.getXRot()+3.0f);

                    }
                }
            }




            if (lp.input.left && lp.input.right){
                //this.addZRot();
                this.setZRot(this.getZRot()+3.0f);

            }
            if (lp.input.up && lp.input.down){
                //left roll
                //this.minusZRot();
                this.setZRot(this.getZRot()-3.0f);
            }

            this.setNormalizeAngle();
//            LOGGER.info("speed: " + speed);

            if (lp.input.up && !lp.input.down){
                controlledSpeed=controlledSpeed<=2.0d?controlledSpeed+0.1d:controlledSpeed;
            }
            else if (lp.input.down && !lp.input.jumping && !lp.input.up){
                controlledSpeed=controlledSpeed>=-2.0d?controlledSpeed-0.1d:controlledSpeed;
            }

            this.setDeltaMovement(
                    (double)(Mth.sin(-this.getYRot() * (float) (Math.PI / 180.0)) * Mth.cos(this.getXRot()*(float)(Math.PI/180.0)) * controlledSpeed),
                    (double)(Mth.sin(-this.getXRot() * (float) (Math.PI / 180.0)) * controlledSpeed),
                    (double)(Mth.cos(this.getYRot() * (float) (Math.PI / 180.0)) * Mth.cos(this.getXRot()*(float)(Math.PI/180.0)) *controlledSpeed));

            //this.setDeltaMovement(lp.input.up?0.2D:0.0D,lp.input.jumping?0.2D:0.0D,lp.input.down?0.2D:0.0D);

        }


    }

    public void handlePush(){
        List<Entity> list = this.level().getEntities(this, this.getBoundingBox().inflate(0.2f,-0.01f,0.2f), EntitySelector.pushableBy(this));
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
    protected Vec3 getPassengerAttachmentPoint(Entity passenger, EntityDimensions dimensions, float scaleFactor) {
        // Seat's relative position (local space)
        double seatX = 0.0; // Example X offset
        double seatY = 1.0; // Example Y offset (height)
        double seatZ = 2.0; // Example Z offset (depth/backward)
        double pivotOffset=1.5;

        Vec3 vec = new Vec3( seatX, seatY-pivotOffset, seatZ)
                .xRot(-this.getXRot()* (float) (Math.PI / 180.0))
                .yRot(-this.getYRot()* (float) (Math.PI / 180.0));

        Vec3 z_axis=new Vec3(0.0d,0.0d,1.0d)
                .xRot(-this.getXRot()* (float) (Math.PI / 180.0))
                .yRot(-this.getYRot()* (float) (Math.PI / 180.0));

        return rotateAxisInternal(this.zRot,z_axis.x,z_axis.y,z_axis.z,vec).add(0.0d,pivotOffset,0.0d);
    }

    public void addZRot(){
        // +180 ok
        this.zRot=this.zRot>=180.0f?this.zRot+1.0f-360.0f:this.zRot+1.0f;
    }

    public void minusZRot(){
        this.zRot=this.zRot<=-179.0f?this.zRot-1.0f+360.0f:this.zRot-1.0f;
    }
    
    public void addXRot(){
        this.setXRot(this.getXRot()>=180.0f?this.getXRot()+2.0f-360.0f:this.getXRot()+2.0f);
    }
    
    public void minusXRot(){
        this.setXRot(this.getXRot()<=-179.0?this.getXRot()-2.0f+360.0f:this.getXRot()-2.0f);
    }

    public void addYRot(){
        this.setYRot(this.getYRot()>=180.0f?this.getYRot()+2.0f-360.0f:this.getYRot()+2.0f);
    }

    public void minusYRot(){
        this.setYRot(this.getYRot()<=-179.0?this.getYRot()-2.0f+360.0f:this.getYRot()-2.0f);
    }

    public boolean isXRotNormal(){
        return this.normalizedXRot<90.0f && this.normalizedXRot>-90.0f;
    }

    public boolean isZRotNormal(){
        return this.normalizedZRot<90.0f && this.normalizedZRot>-90.0f;
    }

    public void rollToLeftSlightly(){
        if (this.zRot<=10.0f && this.zRot>=-10.0f){
            this.minusZRot();
        }
    }
    public void rollToRightSlightly(){
        if (this.zRot<=10.0f && this.zRot>=-10.0f){
            this.addZRot();
        }
    }
    
    public Vec3 rotateAxisInternal(float angle, double aX, double aY, double aZ, Vec3 vec) {
        float hangle = angle * 0.5f;
        double sinAngle = (double) Mth.sin(hangle * (float) (Math.PI / 180.0));
        double qx = aX * sinAngle;
        double qy = aY * sinAngle;
        double qz = aZ * sinAngle;
        double qw = Mth.cos(hangle * (float) (Math.PI / 180.0));
        double w2 = qw * qw;
        double x2 = qx * qx;
        double y2 = qy * qy;
        double z2 = qz * qz;
        double zw = qz * qw;
        double xy = qx * qy;
        double xz = qx * qz;
        double yw = qy * qw;
        double yz = qy * qz;
        double xw = qx * qw;
        double x = vec.x;
        double y = vec.y;
        double z = vec.z;

        double x_end = (w2 + x2 - z2 - y2) * x + (-zw + xy - zw + xy) * y + (yw + xz + xz + yw) * z;
        double y_end = (xy + zw + zw + xy) * x + (y2 - z2 + w2 - x2) * y + (yz + yz - xw - xw) * z;
        double z_end = (xz - yw + xz - yw) * x + (yz + yz + xw + xw) * y + (z2 - y2 - x2 + w2) * z;
        return new Vec3(x_end, y_end, z_end);
    }

    public float getZRot() {
        return zRot;
    }

    public void setZRot(float zRot) {
        this.zRot = zRot;
    }

    private float normalizeAngle(float angle) {
        // Normalize angle to the range [-180, 180]
        while (angle > 180.0f) angle -= 360.0f;
        while (angle < -180.0f) angle += 360.0f;
        return angle;
    }

    public void setNormalizeAngle()
    {
        this.normalizedXRot = normalizeAngle(this.getXRot());
        this.normalizedYRot = normalizeAngle(this.getYRot());
        this.normalizedZRot = normalizeAngle(this.getZRot());
    }


}
