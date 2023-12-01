package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="MecanumPIDTest", group="DriveTest")
public class MecanumPIDTest extends LinearOpMode {
    private MecanumDrive wheelController;
    private ElapsedTime runtime = new ElapsedTime();
    private Servo airplaneLauncher;

    public void runOpMode() {
        wheelController = new MecanumDrive(hardwareMap);
        airplaneLauncher = hardwareMap.get(Servo.class, "airplaneLauncher");

        waitForStart();
        runtime.reset();
        while (opModeIsActive()) {
            double y = -gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x;
            double rx = gamepad1.right_stick_x;
            double ry = gamepad1.right_stick_y;


            telemetry.update();
            Double[] speeds = wheelController.update();
            telemetry.addData("frontLeft Spd", speeds[0]);
            telemetry.addData("frontRight Spd", speeds[1]);
            telemetry.addData("backLeft Spd", speeds[2]);
            telemetry.addData("backRight Spd", speeds[3]);

            wheelController.setDirection(x, y, rx);
        }
    }
}
