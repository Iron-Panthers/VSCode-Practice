/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.util.Constants;

public class DriveWithJoystick extends Command {
  public double currentStraightAngle;
  public double P = 0.1;
  private double error;

  public DriveWithJoystick() {
    requires(Robot.drive);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {}

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    // error = 0;
    // if (!Robot.oi.stick.isStraight()) {
    //   currentStraightAngle = Robot.hardware.gyroWrapper.getYaw();
    // } else {
    //   error = currentStraightAngle - Robot.hardware.gyroWrapper.getYaw();
    // }
    Robot.hardware.rightMotor.set(ControlMode.PercentOutput, (Robot.oi.stick.findRightPower()) * Constants.POWER_MULT_FOR_ARCADE);
    Robot.hardware.leftMotor.set(ControlMode.PercentOutput, (Robot.oi.stick.findLeftPower()) * Constants.POWER_MULT_FOR_ARCADE);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {}

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {}
}
