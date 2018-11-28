/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Drive;
import frc.robot.util.Constants;
//import frc.robot.subsystems.Drive;

public class Move extends Command {
  public Move() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(new Drive());
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double x = Robot.m_oi.stick.getX();
    double y = Robot.m_oi.stick.getY();
    double leftMotor = y + x;
    double rightMotor = y - x;
    if (leftMotor > 1) {
        leftMotor = 1;
    }
    if(rightMotor < -1){
        rightMotor = -1;
    }

    Robot.drive.set(leftMotor, rightMotor);
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
}
