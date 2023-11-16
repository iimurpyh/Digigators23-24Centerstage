package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="IndividualEncoderTest", group="DriveTest")
public class IndividualEncoderTest extends LinearOpMode {
    private DcMotorEx wheel = null;
    private ElapsedTime runtime = new ElapsedTime();

    private void pause(long millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception e) {}
    }

    public void runOpMode() {
        wheel = (DcMotorEx) hardwareMap.get(DcMotor.class, "leftFrontWheel");
        wheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        wheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Set the direction motors will move
        wheel.setDirection(DcMotor.Direction.FORWARD);

        waitForStart();
        runtime.reset();

        telemetry.addData("Encoder Value", 0);
        telemetry.update();

        while (opModeIsActive()) {
            double y = -gamepad1.left_stick_y;
            wheel.setPower(y);
            telemetry.addData("Encoder Value", wheel.getCurrentPosition());
            telemetry.update();

        }
    }
}
