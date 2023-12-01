package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.util.ElapsedTime;

// PID class made by Maneet. Modified from his version
public class PID {

    private double val;
    private double target;
    private double error;
    private static final double Kp = 0.0004375;
    private static final double Ki = 0.0002;
    private static final double Kd = 0;
    private double last_error;
    private double integralSum = 0;
    private static final double CONTROL_LOOP_TIME_STEP = 0.2;
    private static final double MAX_INTEGRAL_TERM = 800;
    private static final double TARGET_DEAD_ZONE = 30;

    public double updatePID(double Val, double Target, double power){
        this.val = Val;
        this.target = Target;
        error = this.target - this.val;
        this.integralSum += error * CONTROL_LOOP_TIME_STEP;

        if (integralSum > MAX_INTEGRAL_TERM) {
            integralSum = MAX_INTEGRAL_TERM;
        } else if (integralSum < -MAX_INTEGRAL_TERM) {
            integralSum = -MAX_INTEGRAL_TERM;
        }

        if (Math.abs(target) <= TARGET_DEAD_ZONE) {
            return 0;
        } else if (Math.abs(error) < TARGET_DEAD_ZONE){
            return power;
        } else {
            return getNewVelocity();
        }
    }
    private double getNewVelocity(){

        double p = Kp * target;
        double i = Ki * integralSum;
        double d = Kd * ((error - last_error)/CONTROL_LOOP_TIME_STEP);

        double sum = p + i + d;
        last_error = error;
        return sum;
    }
}