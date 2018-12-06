package frc.robot.subsystems;

import java.awt.Button;
import java.util.HashMap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.ButtonType;
import frc.robot.RobotMap;
import frc.robot.util.*;

public class ThrustMasterHelper {

    public HashMap<String, Double> realMotorPower;
    public Joystick stick;

    private double speedMultiplier;

    public ThrustMasterHelper(Joystick stick) {
        this.stick = stick;
        double[] rawMotorPower = calculateMotorPower(stick.getX(), stick.getY(), stick.getZ());
        realMotorPower.put("left", rawMotorPower[0]);
        realMotorPower.put("right", rawMotorPower[1]);
    }


    double[] calculateMotorPower(double x, double y, double throttle) {

        /*Initialization of x and y motor powers*/
        x = linearDeadzone(x, Constants.Thrustmaster.DEADZONE);//prevents the robot from moving without user input
        y = linearDeadzone(y, Constants.Thrustmaster.DEADZONE);


        /*Limits turning speed based off of forward speed*/
        double turningSpeed = 1 - map(Math.abs(throttle * y), 0, 1, 0, 0.75);//slower turning the faster you go
        if (turningSpeed > 1) {turningSpeed = 1;}
        turningSpeed *= Constants.Thrustmaster.TURNING_SPEED;


        /*adds acceleration to the speed*/
        if(getButton(RobotMap.POWER_SHIFT)){throttle = 1;}//when pressing the trigger, go full speed
        speedMultiplier = Math.abs(throttle) > speedMultiplier? speedMultiplier + Constants.Thrustmaster.ACCELERATION : Math.abs(throttle);
        if(speedMultiplier > Math.abs(throttle)){speedMultiplier = Math.abs(throttle);}


        //calculates the motor power based off of the thrustmaster input
        double leftMotorPower = (y - x * (Constants.Thrustmaster.INVERT_TURN ? -1 : 1) * turningSpeed) * speedMultiplier;
        double rightMotorPower = (y + x * (Constants.Thrustmaster.INVERT_TURN ? -1 : 1) * turningSpeed) * speedMultiplier;

        //prevents the power from exceding 1
        leftMotorPower /= leftMotorPower > 1 || leftMotorPower < -1 ? Math.abs(leftMotorPower) : 1;
        rightMotorPower /= rightMotorPower > 1 || rightMotorPower < -1 ? Math.abs(rightMotorPower) : 1;
        
        //returns the motor speeds in a more spicy way
        double[] motorSpeeds = { leftMotorPower * Constants.Thrustmaster.ROBOT_SPEED, rightMotorPower * Constants.Thrustmaster.ROBOT_SPEED};
        return motorSpeeds;
    }

    boolean getButton(int button){
        return stick.getRawButton(button);
    }

    double linearDeadzone(double value, double deadzone) {
        //prevents unintended robot movement when the joystick is still
        return (Math.abs(value) > Math.abs(deadzone) ? map(Math.abs(value), deadzone, 1, 0, 1) : 0)
                * (value >= 0 ? 1 : -1);
    }

    double circularDeadzone(double value, double deadzone){
        //prevents unintended robot movement when the joystick is still
        return 0;
    }

    double map(double value, double currentMin, double currentMax, double newMin, double newMax) {
        return (value - currentMin) / (currentMax - currentMin) * (newMax - newMin) + newMin;
    }
}