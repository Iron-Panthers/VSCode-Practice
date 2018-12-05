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

public class DriveWithJoystick extends Command {
  public DriveWithJoystick() {
	  requires(Robot.drive);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
	  double z = Robot.oi.driveStick.getZ();
	  double x = Robot.oi.driveStick.getX();
	//   if (Math.abs(z) < Constants.THRUST_DEADZONE){
	// 	  z = 0;
	//   }
	//   if (Math.abs(x) < Constants.X_DEADZONE){
	// 	  x = 0;
	//   }
	  
	  //Scales the value after applying the deadzone
	//   z *= (z - Constants.THRUST_DEADZONE) / (1 - Constants.THRUST_DEADZONE);
	//   x = (x - Constants.X_DEADZONE) / (1 - Constants.X_DEADZONE);

	  Robot.drive.move(z + x, z - x);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
	  Robot.drive.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
	  Robot.drive.stop();
  }
}
