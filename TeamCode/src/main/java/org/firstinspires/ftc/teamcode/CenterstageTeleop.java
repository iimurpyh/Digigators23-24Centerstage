package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="CenterstageTeleop", group="DriveTest")
public class CenterstageTeleop extends LinearOpMode {
    private MecanumDrive wheelController;
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor parallelArm;
    private Servo airplaneLauncher;
    private Servo claw;

    public void runOpMode() {
        wheelController = new MecanumDrive(hardwareMap);
        wheelController.frontLeft.motor.setDirection(DcMotor.Direction.FORWARD);
        wheelController.frontRight.motor.setDirection(DcMotor.Direction.REVERSE);
        wheelController.backLeft.motor.setDirection(DcMotor.Direction.REVERSE);
        wheelController.backRight.motor.setDirection(DcMotor.Direction.REVERSE);
        airplaneLauncher = hardwareMap.get(Servo.class, "airplaneLauncher");
        claw = hardwareMap.get(Servo.class, "claw");
        parallelArm = hardwareMap.get(DcMotor.class, "parallelArm");
        parallelArm.setDirection(DcMotor.Direction.FORWARD);
        airplaneLauncher.setPosition(0);
        claw.setPosition(0);

        waitForStart();
        runtime.reset();
        while (opModeIsActive()) {
            double y = -gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x;
            double rx = gamepad1.right_stick_x;
            //double ry = gamepad1.right_stick_y;

            // TODO: switch these to gamepad2, they'll be controlled by the second player
            if (gamepad1.left_bumper) {
                airplaneLauncher.setPosition(0.5);
            } else {
                airplaneLauncher.setPosition(0);
            }

            if (gamepad1.left_trigger >= 0.8) {
                claw.setPosition(0.5);
            } else {
                claw.setPosition(0);
            }

            if (gamepad1.a) {
                parallelArm.setPower(0.3);
            }

            if (gamepad1.x){
                parallelArm.setPower(-0.3);
            }

            telemetry.update();
            wheelController.update();
            wheelController.setDirection(x, y, rx);
        }
    }
}
