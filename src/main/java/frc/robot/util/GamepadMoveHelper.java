/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.util;

import java.util.HashMap;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class GamepadMoveHelper extends Controller {

    /*
    Public variables:
        Hashmap<String, Integer> realMotorPowers
        Joystick stick
        boolean reverse
        double speedMultiplier

    */
    public GamepadMoveHelper(Joystick stick){
        super(stick);
    }

  public void reset(){
      speedMultiplier = 0;
      reverse = false;
  }

  public void setMotorPowers() {
      double[] rawMotorPower = calculateMotorPower(stick.getRawAxis(4), stick.getRawAxis(1), 1);
      realMotorPower.put("left", rawMotorPower[0]);
      realMotorPower.put("right", rawMotorPower[1]);
  }

  public double[] calculateMotorPower(double x, double y, double throttle) {
      /* Initialization of x and y motor powers */
      x = linearDeadzone(x, Constants.Gamepad.DEADZONE);// prevents the robot from moving without user input
      y = linearDeadzone(y, Constants.Gamepad.DEADZONE) * (reverse? -1 : 1);
      x = getButton(RobotMap.Gamepad.QUICK_TURN) && x != 0 ? x / Math.abs(x) : x;

      double modifiedY = y * throttle;// using throttle as a speed multiplyer

      /* Limits turning speed based off of forward speed */
      double turningSpeed = 1 - map(Math.abs(modifiedY), 0, 1, 0, 0.4);// slower turning the faster you go
      if (turningSpeed > 1) {
          turningSpeed = 1;
      }
      turningSpeed *= Constants.Gamepad.TURNING_SPEED;
      turningSpeed = getButton(RobotMap.Gamepad.QUICK_TURN) ? Constants.Gamepad.TURNING_SPEED : turningSpeed;

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
      int turnDirection = (Constants.Gamepad.INVERT_TURN ? -1 : 1);
      double leftMotorPower = (y - x * turnDirection * turningSpeed) * throttle;
      double rightMotorPower = (y + x * turnDirection * turningSpeed) * throttle;

      // prevents the power from exceding 1
      leftMotorPower /= Math.abs(leftMotorPower) > 1 ? Math.abs(leftMotorPower) : 1;
      rightMotorPower /= Math.abs(rightMotorPower) > 1 ? Math.abs(rightMotorPower) : 1;

      // allows the user to go in reverse quickly
      reverse = buttonClicked(RobotMap.Gamepad.REVERSE) ? !reverse : reverse;
      // returns the motor speeds in a more spicy way
      double[] motorSpeeds = { leftMotorPower * Constants.Gamepad.ROBOT_SPEED,
              rightMotorPower * Constants.Gamepad.ROBOT_SPEED };
      return motorSpeeds;
  }
}
