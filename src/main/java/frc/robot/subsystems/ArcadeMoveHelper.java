package frc.robot.subsystems;

import java.util.HashMap;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.RobotMap;
import frc.robot.util.*;

public class ArcadeMoveHelper {

    public HashMap<String, Double> realMotorPower;
    public Joystick stick;

    private double speedMultiplier;

    public ArcadeMoveHelper(Joystick stick) {
        this.stick = stick;
        double[] rawMotorPower = calculateMotorPower(stick.getX(), stick.getY(), Constants.Arcade.ROBOT_SPEED);
        realMotorPower.put("left", rawMotorPower[0]);
        realMotorPower.put("right", rawMotorPower[1]);
    }

    double[] calculateMotorPower(double x, double y, double throttle) {
        /* Initialization of x and y motor powers */
        x = linearDeadzone(x, Constants.Arcade.DEADZONE);// prevents the robot from moving without user input
        y = linearDeadzone(y, Constants.Arcade.DEADZONE);
        double modifiedY = y * throttle;//using throttle as a speed multiplyer

        /* Limits turning speed based off of forward speed */
        double turningSpeed = 1 - map(Math.abs(throttle * y), 0, 1, 0, 0.8);// slower turning the faster you go
        if (turningSpeed > 1) {
            turningSpeed = 1;
        }
        turningSpeed *= Constants.Thrustmaster.TURNING_SPEED;

        /* adds acceleration to the speed */
        if (getButton(RobotMap.POWER_SHIFT)) {
            throttle = 1;
        } // when pressing the trigger, go full speed
        speedMultiplier = Math.abs(modifiedY) > speedMultiplier ? speedMultiplier + Constants.Arcade.ACCELERATION
                : Math.abs(modifiedY);
        if (speedMultiplier > Math.abs(modifiedY)) {
            speedMultiplier = Math.abs(modifiedY);
        }

        // calculates the motor power based off of the thrustmaster input
        double leftMotorPower = (y - x * (Constants.Arcade.INVERT_TURN ? -1 : 1) * turningSpeed)
                * speedMultiplier;
        double rightMotorPower = (y + x * (Constants.Arcade.INVERT_TURN ? -1 : 1) * turningSpeed)
                * speedMultiplier;

        // prevents the power from exceding 1
        leftMotorPower /= leftMotorPower > 1 || leftMotorPower < -1 ? Math.abs(leftMotorPower) : 1;
        rightMotorPower /= rightMotorPower > 1 || rightMotorPower < -1 ? Math.abs(rightMotorPower) : 1;

        // returns the motor speeds in a more spicy way
        double[] motorSpeeds = { leftMotorPower * Constants.Arcade.ROBOT_SPEED,
                rightMotorPower * Constants.Arcade.ROBOT_SPEED };
        return motorSpeeds;
    }

    boolean getButton(int button) {
        return stick.getRawButton(button);
    }

    double linearDeadzone(double value, double deadzone) {
        // prevents unintended robot movement when the joystick is still
        return (Math.abs(value) > Math.abs(deadzone) ? map(Math.abs(value), deadzone, 1, 0, 1) : 0)
                * (value >= 0 ? 1 : -1);
    }

    HashMap<String, Double> circularDeadzone(double x, double y, double deadzone) {
        // prevents unintended robot movement when the joystick is still
        HashMap<String, Double> bothValues = new HashMap<String, Double>();
        double magnitude = Math.sqrt(x * x + y * y);
        double scaledMagnitude = (magnitude - deadzone) / (1 - deadzone);
        bothValues.put("x", x * scaledMagnitude);
        bothValues.put("y", y * scaledMagnitude);
        return bothValues;
    }

    double map(double value, double currentMin, double currentMax, double newMin, double newMax) {
        return (value - currentMin) / (currentMax - currentMin) * (newMax - newMin) + newMin;
    }
}