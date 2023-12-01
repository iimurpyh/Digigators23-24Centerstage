package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

// Holds a PID and a DcMotor to create a class that can be controlled like a Motor, but
// with PID adjustment. It would be smart to extend Motor but it has 10000 methods we don't use
public class PIDMotor {
    double target = 0;
    // DcMotorEx is for motors controlled by a Lynx Expansion Hub and has some extra methods
    // like getVelocity
    DcMotorEx motor;
    PID velocityPid;

    public PIDMotor(DcMotor motor) {
        this.motor = (DcMotorEx) motor; // Turn DcMotor into DcMotorEx
        this.velocityPid = new PID();

    }

    public void init() {
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor.setDirection(DcMotor.Direction.REVERSE);
    }

    public void setTarget(double target) {
        this.target = target;
    }

    public double update() {
        motor.setPower(target);
        target = velocityPid.updatePID(motor.getVelocity(), target, motor.getPower());
        return target;
    }
}
