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
import frc.robot.util.ArcadeMoveHelper;
import frc.robot.util.*;

public class JoystickMove extends Command {

  Joystick stick;
  ArcadeMoveHelper arcade;

  public JoystickMove() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.drive);
    stick = Robot.m_oi.stick;
    arcade = new ArcadeMoveHelper(stick);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    arcade.reset();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    arcade.setMotorPowers();/*always keep in front*/
    int direction = (Constants.Arcade.INVERT_DIRECTION ? -1 : 1);
    Robot.drive.set(
        arcade.realMotorPower.get("left") * direction,
        arcade.realMotorPower.get("right") * direction);
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
