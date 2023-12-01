package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

// Represents a mecanum drivetrain with 4 wheels controlled by DcMotors.
public class MecanumDrive {
    public PIDMotor frontLeft;
    public PIDMotor backLeft;
    public PIDMotor frontRight;
    public PIDMotor backRight;

    // Default motor names to look for if none are given.
    // These are probably the only ones that will be used. Not like any OpModes would want another set of 4 motors.
    private static final String NAME_FRONT_LEFT = "leftFrontWheel";
    private static final String NAME_FRONT_RIGHT = "rightFrontWheel";
    private static final String NAME_BACK_LEFT = "leftBackWheel";
    private static final String NAME_BACK_RIGHT = "rightBackWheel";

    public MecanumDrive(PIDMotor frontLeft, PIDMotor frontRight, PIDMotor backLeft, PIDMotor backRight)  {
        this.frontLeft = frontLeft;
        this.frontRight = frontRight;
        this.backLeft = backLeft;
        this.backRight = backRight;
    }

    public MecanumDrive(HardwareMap hardwareMapToGetFrom) {
        // Use defaults
        this(new PIDMotor(hardwareMapToGetFrom.get(DcMotor.class, NAME_FRONT_LEFT)),
            new PIDMotor(hardwareMapToGetFrom.get(DcMotor.class, NAME_FRONT_RIGHT)),
            new PIDMotor(hardwareMapToGetFrom.get(DcMotor.class, NAME_BACK_LEFT)),
            new PIDMotor(hardwareMapToGetFrom.get(DcMotor.class, NAME_BACK_RIGHT)));
    }

    public void setDirection(double x, double y, double rotate) {
        // Denominator is the largest motor power (absolute value) or 1
        // This ensures all the powers maintain the same ratio, but only when
        // at least one is out of the range [-1, 1]
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rotate), 1);
        denominator -= 0.2;

        // Convert x, y, and rotation movement directions into wheel rotation
        // Math from Game Manual 0
        frontLeft.setTarget((y + x + rotate) / denominator);
        backLeft.setTarget((y - x + rotate) / denominator);
        frontRight.setTarget((y - x - rotate) / denominator);
        backRight.setTarget((y + x - rotate) / denominator);
    }

    public Double[] update() {
        return new Double[]{frontLeft.update(), frontRight.update(), backLeft.update(), backRight.update()};
    }
}
