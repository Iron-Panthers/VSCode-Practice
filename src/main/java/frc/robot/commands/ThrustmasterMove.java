/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.ThrustMasterHelper;
import frc.robot.util.Constants;
//import frc.robot.subsystems.Drive;

public class ThrustmasterMove extends Command {

  Joystick stick;
  ThrustMasterHelper thrustmaster;

  public ThrustmasterMove() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.drive);
    stick = Robot.m_oi.stick;
    thrustmaster = new ThrustMasterHelper(stick);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.drive.set(
        thrustmaster.realMotorPower.get("left") * (Constants.Thrustmaster.INVERT_DIRECTION ? -1 : 1),
        thrustmaster.realMotorPower.get("right") *(Constants.Thrustmaster.INVERT_DIRECTION ? -1 : 1));
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
