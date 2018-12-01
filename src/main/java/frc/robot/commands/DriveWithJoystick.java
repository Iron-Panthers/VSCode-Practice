package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Drive;

public class DriveWithJoystick extends Command {
  public DriveWithJoystick() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.drive);
  }

  private void requires(Drive drive) {
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
    if(Math.abs(y)<0.1){
      y = 0;
    }
    if(Math.abs(x)<0.1){
      x = 0;
    }
    Robot.hardware.leftMotor.set(null, y + x);
    Robot.hardware.rightMotor.set(null, y - x);
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