package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="MecanumPIDTest", group="DriveTest")
public class MecanumPIDTest extends LinearOpMode {
    private MecanumDrive wheelController;
    private ElapsedTime runtime = new ElapsedTime();

    public void runOpMode() {
        wheelController = new MecanumDrive(hardwareMap);

        waitForStart();
        runtime.reset();
        while (opModeIsActive()) {
            double y = -gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x;
            double rx = gamepad1.right_stick_x;
            double ry = gamepad1.right_stick_y;
            telemetry.addData("Joystick Direction:", String.valueOf(x) + "," + String.valueOf(y));
            telemetry.update();
            wheelController.update();
            wheelController.setDirection(x, y, rx);
        }
    }
}
