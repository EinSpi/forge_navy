package com.gmail.yichentang777.tyc_mod.utils;

import net.minecraft.world.phys.Vec3;

public class AircraftLocalRef {

    private Vec3 mainX;
    private Vec3 mainY;
    private Vec3 mainZ;
    private double renderAngle1;
    private double renderAngle2;
    private double renderAngle3;

    public AircraftLocalRef(Vec3 mainX, Vec3 mainY, Vec3 mainZ,
                            double renderAngle1, double renderAngle2, double renderAngle3) {
        this.mainX = mainX;
        this.mainY = mainY;
        this.mainZ = mainZ;
        this.renderAngle1 = renderAngle1;
        this.renderAngle2 = renderAngle2;
        this.renderAngle3 = renderAngle3;
    }

    public AircraftLocalRef(Vec3 mainX, Vec3 mainY, Vec3 mainZ) {
        this.mainX = mainX;
        this.mainY = mainY;
        this.mainZ = mainZ;
        recoverRotationsFromCoordinate();
    }

    public Vec3 getMainX() {
        return mainX;
    }

    public Vec3 getMainY() {
        return mainY;
    }

    public Vec3 getMainZ() {
        return mainZ;
    }

    public double getRenderAngle1() {
        return renderAngle1;
    }

    public double getRenderAngle2() {
        return renderAngle2;
    }

    public double getRenderAngle3() {
        return renderAngle3;
    }

    public void setMainX(Vec3 mainX) {
        this.mainX = mainX;
    }

    public void setMainY(Vec3 mainY) {
        this.mainY = mainY;
    }

    public void setMainZ(Vec3 mainZ) {
        this.mainZ = mainZ;
    }

    public void rotateAround(boolean direction, double aX, double aY, double aZ, double sinSensitivity, double cosSensitivity) {
        double qx = direction ? aX * sinSensitivity : aX * (-sinSensitivity);
        double qy = direction ? aY * sinSensitivity : aY * (-sinSensitivity);
        double qz = direction ? aZ * sinSensitivity : aZ * (-sinSensitivity);
        double qw = cosSensitivity;
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
        double a = (w2 + x2 - z2 - y2);
        double b = (-zw + xy - zw + xy);
        double c = (yw + xz + xz + yw);
        double d = (xy + zw + zw + xy);
        double e = (y2 - z2 + w2 - x2);
        double f = (yz + yz - xw - xw);
        double g = (xz - yw + xz - yw);
        double h = (yz + yz + xw + xw);
        double i = (z2 - y2 - x2 + w2);

        double x = mainX.x;
        double y = mainX.y;
        double z = mainX.z;
        double x_end = a * x + b * y + c * z;
        double y_end = d * x + e * y + f * z;
        double z_end = g * x + h * y + i * z;
        this.mainX = new Vec3(x_end, y_end, z_end).normalize();

        x = mainY.x;
        y = mainY.y;
        z = mainY.z;
        x_end = a * x + b * y + c * z;
        y_end = d * x + e * y + f * z;
        z_end = g * x + h * y + i * z;
        this.mainY = new Vec3(x_end, y_end, z_end).normalize();

        x = mainZ.x;
        y = mainZ.y;
        z = mainZ.z;
        x_end = a * x + b * y + c * z;
        y_end = d * x + e * y + f * z;
        z_end = g * x + h * y + i * z;
        this.mainZ = new Vec3(x_end, y_end, z_end).normalize();


    }

    public void recoverRotationsFromCoordinate() {
        double cosbeta=Math.sqrt((mainZ.z * mainZ.z) + (mainZ.x * mainZ.x));
        double beta = Math.atan2(-mainZ.y, cosbeta);
        if (beta <= 0.017d-(Math.PI/2.0d)) {
            //beta is almost - half pi
            renderAngle1 = -(Math.PI/2.0d);
            renderAngle2 = 0.0d;
            renderAngle3 = -Math.atan2(mainX.z, mainX.x);
        } else if (beta >= 3.124d-(Math.PI/2.0d)) {
            renderAngle1 = Math.PI/2.0d;
            renderAngle2 = 0.0d;
            renderAngle3 = Math.atan2(mainX.z, mainX.x);
        } else {
            renderAngle1 = beta;
            renderAngle2 = Math.atan2(mainZ.x / cosbeta, mainZ.z / cosbeta);
            renderAngle3 = Math.atan2(mainX.y / cosbeta, mainY.y / cosbeta);
        }

    }
}
