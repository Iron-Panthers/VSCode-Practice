/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.util.Constants;
//import frc.robot.subsystems.Drive;

public class Move extends Command {
  public Move() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.drive);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double x = deadzone(Robot.m_oi.stick.getX(), Constants.DEADZONE);
    double y = deadzone(Robot.m_oi.stick.getY(), Constants.DEADZONE);
    double leftMotorPower = y - x;
    double rightMotorPower = y + x;
    leftMotorPower/=leftMotorPower>1||leftMotorPower<-1?Math.abs(leftMotorPower):1;
    rightMotorPower/=rightMotorPower>1||rightMotorPower<-1?Math.abs(rightMotorPower):1;
    
    Robot.drive.set(leftMotorPower * Constants.ROBOT_SPEED, rightMotorPower * Constants.ROBOT_SPEED);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }

  double deadzone(double value, double deadzone){
    return (Math.abs(value) > Math.abs(deadzone)? map(Math.abs(value), deadzone, 1, 0, 1): 0) * (value >= 0? 1: -1);
  }
  double map(double value, double currentMin, double currentMax, double newMin, double newMax){
    return (value - currentMin)/(currentMax - currentMin) * (newMax - newMin) + newMin;
  }
}
