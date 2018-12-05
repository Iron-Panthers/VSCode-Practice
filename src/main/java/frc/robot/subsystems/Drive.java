/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.Robot;
import frc.robot.commands.JoystickMove;
import frc.robot.commands.ThrustmasterMove;
import frc.robot.util.Constants;

/**
 * Add your docs here.
 */
public class Drive extends Subsystem {
  TalonSRX leftMotor;
  TalonSRX rightMotor;
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public Drive(){
      leftMotor = Robot.hardware.leftMotor1;
      rightMotor = Robot.hardware.rightMotor1;
  }
  public void set(double leftPower, double rightPower) {
      leftMotor.set(ControlMode.PercentOutput, leftPower * (Constants.invertDirection?-1:1));
      rightMotor.set(ControlMode.PercentOutput, rightPower * (Constants.invertDirection?-1:1));
  }
  public void stop() {
      leftMotor.set(ControlMode.PercentOutput, 0);
      rightMotor.set(ControlMode.PercentOutput, 0);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new ThrustmasterMove());
  }
}
