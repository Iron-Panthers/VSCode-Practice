package frc.robot.util;

import java.util.HashMap;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.RobotMap;

public class ArcadeMoveHelper {

    public HashMap<String, Double> realMotorPower = new HashMap<>();
    public Joystick stick;

    public boolean reverse = false;
    public double speedMultiplier;

    private double throttle;

    public ArcadeMoveHelper(Joystick stick) {
        this.stick = stick;
    }

    public void reset(){
        speedMultiplier = 0;
        reverse = false;
    }

    public void setMotorPowers() {
        double[] rawMotorPower = calculateMotorPower(this.stick.getX(), this.stick.getY());
        realMotorPower.put("left", rawMotorPower[0]);
        realMotorPower.put("right", rawMotorPower[1]);
    }

    double[] calculateMotorPower(double x, double y) {
        /* Initialization of x and y motor powers */
        x = linearDeadzone(x, Constants.Arcade.DEADZONE);// prevents the robot from moving without user input
        y = linearDeadzone(y, Constants.Arcade.DEADZONE) * (reverse? -1 : 1);
        x = getButton(RobotMap.QUICK_TURN) && x != 0 ? x / Math.abs(x) : x;
        y /= getButton(RobotMap.POWER_SHIFT) && y != 0 ? Math.abs(y) : 1;
        throttle = 1;

        double modifiedY = y * throttle;// using throttle as a speed multiplyer

        /* Limits turning speed based off of forward speed */
        double turningSpeed = 1 - map(Math.abs(modifiedY), 0, 1, 0, 0.4);// slower turning the faster you go
        if (turningSpeed > 1) {
            turningSpeed = 1;
        }
        turningSpeed *= Constants.Arcade.TURNING_SPEED;
        turningSpeed = getButton(RobotMap.QUICK_TURN) ? Constants.Arcade.TURNING_SPEED : turningSpeed;

        /* adds acceleration to the speed */
        if (modifiedY > speedMultiplier) {
            speedMultiplier = modifiedY - speedMultiplier < Constants.ACCELERATION ? modifiedY
                    : speedMultiplier + Constants.ACCELERATION;
        }
        if (modifiedY < speedMultiplier) {
            speedMultiplier = speedMultiplier - modifiedY < Constants.DECELERATION ? modifiedY
                    : speedMultiplier - Constants.DECELERATION;
        }
        speedMultiplier = speedMultiplier > 1 ? 1 : speedMultiplier < -1 ? -1 : speedMultiplier;
        y = Math.copySign(speedMultiplier, y);

        // calculates the motor power based off of the thrustmaster input
        int turnDirection = (Constants.Arcade.INVERT_TURN ? -1 : 1);
        double leftMotorPower = (y - x * turnDirection * turningSpeed) * throttle;
        double rightMotorPower = (y + x * turnDirection * turningSpeed) * throttle;

        // prevents the power from exceding 1
        leftMotorPower /= Math.abs(leftMotorPower) > 1 ? Math.abs(leftMotorPower) : 1;
        rightMotorPower /= Math.abs(rightMotorPower) > 1 ? Math.abs(rightMotorPower) : 1;

        // allows the user to go in reverse quickly
        reverse = buttonClicked(RobotMap.REVERSE) ? !reverse : reverse;
        // returns the motor speeds in a more spicy way
        double[] motorSpeeds = { leftMotorPower * Constants.Arcade.ROBOT_SPEED,
                rightMotorPower * Constants.Arcade.ROBOT_SPEED };
        return motorSpeeds;
    }

    boolean getButton(int button) {
        return stick.getRawButton(button);
    }

    boolean buttonClicked(int button) {
        return stick.getRawButtonPressed(button);
    }

    double linearDeadzone(double value, double deadzone) {
        // prevents unintended robot movement when the joystick is still
        return (Math.abs(value) > Math.abs(deadzone) ? map(Math.abs(value), deadzone, 1, 0.05, 1) : 0)
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