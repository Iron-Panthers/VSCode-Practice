package frc.robot.subsystems;

import java.util.HashMap;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.util.Constants;

public class ThrustMasterHelper {

    public HashMap<String, Double> realMotorPower;

    public ThrustMasterHelper(Joystick stick) {
        var rawMotorPower = calculateMotorPower(stick.getX(), stick.getY(), stick.getZ());
        realMotorPower.put("left", rawMotorPower[0]);
        realMotorPower.put("right", rawMotorPower[1]);
    }


    double[] calculateMotorPower(double x, double y, double throttle) {
        x = linearDeadzone(x, Constants.Thrustmaster.DEADZONE);//prevents the robot from moving without user input
        y = linearDeadzone(y, Constants.Thrustmaster.DEADZONE);

        double turningSpeed = 1 - map(Math.abs(throttle), 0, 1, 0, 0.9);//slower turning the faster you go
        if (turningSpeed > 1) {
            turningSpeed = 1;
        }
        
        //calculates the motor power based off of the thrustmaster input
        double leftMotorPower = (y - x * (Constants.Thrustmaster.INVERT_TURN ? -1 : 1) * turningSpeed) * throttle;
        double rightMotorPower = (y + x * (Constants.Thrustmaster.INVERT_TURN ? -1 : 1) * turningSpeed) * throttle;

        //prevents the power from exceding 1
        leftMotorPower /= leftMotorPower > 1 || leftMotorPower < -1 ? Math.abs(leftMotorPower) : 1;
        rightMotorPower /= rightMotorPower > 1 || rightMotorPower < -1 ? Math.abs(rightMotorPower) : 1;
        
        double[] motorPowers = { leftMotorPower * Constants.Thrustmaster.ROBOT_SPEED, rightMotorPower * Constants.Thrustmaster.ROBOT_SPEED};
        return motorPowers;
    }

    double linearDeadzone(double value, double deadzone) {
        //prevents unintended robot movement when the joystick is still
        return (Math.abs(value) > Math.abs(deadzone) ? map(Math.abs(value), deadzone, 1, 0, 1) : 0)
                * (value >= 0 ? 1 : -1);
    }

    double map(double value, double currentMin, double currentMax, double newMin, double newMax) {
        return (value - currentMin) / (currentMax - currentMin) * (newMax - newMin) + newMin;
    }
}