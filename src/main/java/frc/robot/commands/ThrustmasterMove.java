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

public class ThrustmasterMove extends Command {

  private double turningSpeed = 0;
  private double turningAccel = 0.01;
  private double turningDeccel = 0.01;

  public ThrustmasterMove() {
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
    double x = deadzone(Robot.m_oi.stick.getX(), Constants.Thrustmaster.DEADZONE);
    double y = deadzone(Robot.m_oi.stick.getY(), Constants.Thrustmaster.DEADZONE);
    double throttle = deadzone(Robot.m_oi.stick.getZ(), Constants.Thrustmaster.DEADZONE);

    turningSpeed = 1 - map(Math.abs(y), 0, 1, 0, 0.9);
    if(turningSpeed > 1){turningSpeed = 1;}
    double leftMotorPower = (y - x * (Constants.Thrustmaster.INVERT_TURN ? -1 : 1) * turningSpeed)  * throttle;
    double rightMotorPower = (y + x * (Constants.Thrustmaster.INVERT_TURN ? -1 : 1) * turningSpeed) * throttle;
    
    leftMotorPower /= leftMotorPower > 1 || leftMotorPower < -1 ? Math.abs(leftMotorPower) : 1;
    rightMotorPower /= rightMotorPower > 1 || rightMotorPower < -1 ? Math.abs(rightMotorPower) : 1;

    Robot.drive.set(leftMotorPower * Constants.Thrustmaster.ROBOT_SPEED * (Constants.Thrustmaster.INVERT_DIRECTION?-1:1), 
                    rightMotorPower * Constants.Thrustmaster.ROBOT_SPEED * (Constants.Thrustmaster.INVERT_DIRECTION?-1:1));
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

  double deadzone(double value, double deadzone) {
    return (Math.abs(value) > Math.abs(deadzone) ? map(Math.abs(value), deadzone, 1, 0, 1) : 0) * (value >= 0 ? 1 : -1);
  }

  double map(double value, double currentMin, double currentMax, double newMin, double newMax) {
    return (value - currentMin) / (currentMax - currentMin) * (newMax - newMin) + newMin;
  }
}
