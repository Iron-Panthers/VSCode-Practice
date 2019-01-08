/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.Robot;
import frc.robot.commands.GamepadMove;
import frc.robot.commands.JoystickMove;
import frc.robot.commands.ThrustmasterMove;
import frc.robot.util.*;

/**
 * Add your docs here.
 */
public class Drive extends Subsystem {
  MotorGroup leftMotorGroup;
  MotorGroup rightMotorGroup;
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public Drive(){
      leftMotorGroup = new MotorGroup(Robot.hardware.leftMotor1);
      rightMotorGroup = new MotorGroup(Robot.hardware.rightMotor1);
  }
  public void set(double leftPower, double rightPower) {
      leftMotorGroup.leadTalonSRX.set(ControlMode.PercentOutput, leftPower);
      rightMotorGroup.leadTalonSRX.set(ControlMode.PercentOutput, rightPower);
      leftMotorGroup.followMasters();
      rightMotorGroup.followMasters();
  }
  public void stop() {
      rightMotorGroup.leadTalonSRX.set(ControlMode.PercentOutput, 0);
      leftMotorGroup.leadTalonSRX.set(ControlMode.PercentOutput, 0);
      leftMotorGroup.followMasters();
      rightMotorGroup.followMasters();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(
        (Constants.CONTROLLER == "thrustmaster") ? new ThrustmasterMove() :
        (Constants.CONTROLLER == "gamepad") ? new GamepadMove() :
        new JoystickMove());
  }
}
