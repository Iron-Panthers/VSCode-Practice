package frc.robot.util;

import java.util.HashMap;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.RobotMap;

public class ArcadeMoveHelper extends Controller{

    public ArcadeMoveHelper(Joystick stick) {
        super(stick);
    }

    public void reset(){
        speedMultiplier = 0;
        reverse = false;
    }

    public void setMotorPowers() {
        double[] rawMotorPower = calculateMotorPower(stick.getX(), stick.getY(), 1);
        realMotorPower.put("left", rawMotorPower[0]);
        realMotorPower.put("right", rawMotorPower[1]);
    }
    public void setMotorPowers(double x, double y, double throttle){
        double[] rawMotorPower = calculateMotorPower(x, y, throttle);
        realMotorPower.put("left", rawMotorPower[0]);
        realMotorPower.put("right", rawMotorPower[1]);
    }

    public double[] calculateMotorPower(double x, double y, double throttle) {
        /* Initialization of x and y motor powers */
        HashMap<String, Double> XandY = circularDeadzone(x, y, Constants.Arcade.DEADZONE);
        x = x > Constants.Arcade.DEADZONE ? linearDeadzone(x, Constants.Arcade.DEADZONE) : XandY.get("x");// prevents the robot from moving without user input
        y = (y > Constants.Arcade.DEADZONE ? linearDeadzone(y, Constants.Arcade.DEADZONE) : XandY.get("y")) * (reverse? -1 : 1);
        x = getButton(RobotMap.Arcade.QUICK_TURN) && x != 0 ? x / Math.abs(x) : x;

        double modifiedY = y * throttle;// using throttle as a speed multiplyer

        /* Limits turning speed based off of forward speed */
        double turningSpeed = 1 - map(Math.abs(modifiedY), 0, 1, 0, 0.4);// slower turning the faster you go
        if (turningSpeed > 1) {
            turningSpeed = 1;
        }
        turningSpeed *= Constants.Arcade.TURNING_SPEED;
        turningSpeed = getButton(RobotMap.Arcade.QUICK_TURN) ? Constants.Arcade.TURNING_SPEED : turningSpeed;

        /* adds acceleration to the speed */
        if (modifiedY > speedMultiplier) {
            speedMultiplier = modifiedY < 0 ? speedMultiplier + Constants.DECELERATION : modifiedY - speedMultiplier < Constants.ACCELERATION ? modifiedY
                    : speedMultiplier + Constants.ACCELERATION;
        }
        if (modifiedY < speedMultiplier) {
            speedMultiplier = modifiedY > 0 ? speedMultiplier - Constants.DECELERATION : speedMultiplier - modifiedY < Constants.ACCELERATION ? modifiedY
                    : speedMultiplier - Constants.ACCELERATION;
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
        reverse = buttonClicked(RobotMap.Arcade.REVERSE) ? !reverse : reverse;
        // returns the motor speeds in a more spicy way
        double[] motorSpeeds = { leftMotorPower * Constants.Arcade.ROBOT_SPEED,
                rightMotorPower * Constants.Arcade.ROBOT_SPEED };
        return motorSpeeds;
    }
}