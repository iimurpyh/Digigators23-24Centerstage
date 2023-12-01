package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="AutoEncoderPIDAutonomousTest", group="DriveTest")
public class AutoEncoderPIDAutonomousTest extends LinearOpMode {
    private MecanumDrive wheelController;
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftFrontWheel = null;
    private DcMotor rightFrontWheel = null;
    private DcMotor rightBackWheel = null;
    private DcMotor leftBackWheel = null;

    // 1120 ticks per revolution in an AndyMark 40 motor, 4 inch diameter
    private static final double ENCODER_TICK_TO_IN = 4 * Math.PI * 1120;

    private void moveDirectionIn(double x, double y, double rotate) {
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rotate), 1);
        double leftFrontSpeed = (y + x + rotate) / denominator;
        double rightFrontSpeed = (y - x - rotate) / denominator;
        double leftBackSpeed = (y - x + rotate) / denominator;
        double rightBackSpeed = (y + x - rotate) / denominator;

        leftFrontWheel.setTargetPosition((int) Math.round(leftFrontSpeed/ENCODER_TICK_TO_IN));
        rightFrontWheel.setTargetPosition((int) Math.round(rightFrontSpeed/ENCODER_TICK_TO_IN));
        leftBackWheel.setTargetPosition((int) Math.round(leftBackSpeed/ENCODER_TICK_TO_IN));
        rightBackWheel.setTargetPosition((int) Math.round(rightBackSpeed/ENCODER_TICK_TO_IN));
    }

    public void runOpMode() {
        leftFrontWheel = hardwareMap.get(DcMotor.class, "leftFrontWheel");
        rightFrontWheel = hardwareMap.get(DcMotor.class, "rightFrontWheel");
        leftBackWheel = hardwareMap.get(DcMotor.class, "leftBackWheel");
        rightBackWheel = hardwareMap.get(DcMotor.class, "rightBackWheel");
        //linearExtenderUp = hardwareMap.get(DcMotor.class, "linearExtenderUp");
        leftBackWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBackWheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBackWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBackWheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        waitForStart();
        runtime.reset();

        moveDirectionIn(12, 0, 0);
    }
}
